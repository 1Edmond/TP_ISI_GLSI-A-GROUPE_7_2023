package springtpiai.siexpose.services;

import springtpiai.siexpose.models.Client;
import springtpiai.siexpose.myExceptions.InvalideNumCompteException;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.myExceptions.SoldeInsufisantException;
import springtpiai.siexpose.allEnums.TypeCompte;
import springtpiai.siexpose.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtpiai.siexpose.myExceptions.SoldeNegativeException;
import springtpiai.siexpose.records.VirementCompteResponse;
import springtpiai.siexpose.repositories.ClientRepository;
import springtpiai.siexpose.repositories.CompteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    ClientRepository clientRepository;
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }
    public Optional<Compte> getOneCompte(String numCompte){
        return  compteRepository.findById(numCompte);
    }
    public Compte addCompte(Compte compte) throws InvalideNumeroClientException {
        compte.setNumCompte(generateNumCompte(compte.getIdClient()));
        compte.setSolde(0);
        compte.setDateCreation(LocalDateTime.now());
        return compteRepository.save(compte);
    }
    private String generateNumCompte(Integer clientId) throws InvalideNumeroClientException {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(optionalClient.isEmpty())
            throw  new InvalideNumeroClientException(clientId);
        var client = optionalClient.get();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ&~#@";
        var temp = new Random().nextInt(characters.length());
        StringBuilder numCompteBuilder = new StringBuilder();
        while (numCompteBuilder.length() <= 5){
            numCompteBuilder.append(characters.charAt(temp));
            temp = new Random().nextInt(characters.length());
        }
        String numCompte = numCompteBuilder.toString();
        numCompte += compteRepository.count()
                + client.getNom().substring(0,1)
                + client.getPrenom().charAt(0)
                + String.valueOf(client.getDateNaissance().getYear()).substring(2,4);
        Optional<Compte> optionalCompte = getOneCompte(numCompte);
        if(optionalCompte.isPresent())
          return generateNumCompte(clientId);
        return numCompte;
    }
    public Compte updateCompte(String numCompte, Compte compte) throws InvalideNumCompteException {
        Optional<Compte> optionalCompte = getOneCompte(numCompte);
        if(optionalCompte.isEmpty())
            throw new InvalideNumCompteException(numCompte);
        var oldCompte = optionalCompte.get();

        /*  if(compte.getSolde() < 0)
            throw new SoldeNegativeException(); */
        if(compte.getTypeCompte() != null && compte.getTypeCompte() != oldCompte.getTypeCompte())
            oldCompte.setTypeCompte(compte.getTypeCompte());
        //compte.setNumCompte(numCompte);
        return compteRepository.save(oldCompte);
    }
    public void deleteCompte(String numCompte){
        compteRepository.deleteById(numCompte);
    }
    public Compte debiterCompte(String numCompte, float solde) throws InvalideNumCompteException, SoldeNegativeException, SoldeInsufisantException {
        Optional<Compte> optionalCompte = getOneCompte(numCompte);
        if(optionalCompte.isEmpty())
            throw  new InvalideNumCompteException(numCompte);
        Compte cpt = optionalCompte.get();
        if(solde < 0)
            throw  new SoldeNegativeException();
        if(cpt.getSolde() >= solde){
            cpt.setSolde(cpt.getSolde() - solde);
            compteRepository.save(cpt);
            return cpt;
        }
        else
            throw new SoldeInsufisantException(numCompte);


    }
    public Compte crediterCompte(String numCompte, float solde) throws InvalideNumCompteException, SoldeNegativeException {
        Optional<Compte> optionalCompte = getOneCompte(numCompte);
        if(optionalCompte.isEmpty())
            throw  new InvalideNumCompteException(numCompte);
        if(solde < 0 )
            throw new SoldeNegativeException();
        Compte cpt = optionalCompte.get();
        cpt.setSolde(cpt.getSolde() + solde);
        compteRepository.save(cpt);
        return cpt;
    }
    public VirementCompteResponse faireVirement(String numCompteEmetteur, String numCompteRecepteur, float solde) throws SoldeInsufisantException, InvalideNumCompteException, SoldeNegativeException {
        var emt = this.crediterCompte(numCompteEmetteur, solde);
        var rcp = this.debiterCompte(numCompteRecepteur, solde);
        return  new VirementCompteResponse(emt, rcp, solde);
    }
    public Optional<List<Compte>> getCompteBySolde(float solde){
        return  compteRepository.findCompteBySolde(solde);
    }
    public Optional<List<Compte>> getCompteByClient(Integer clientId){
        return  compteRepository.findCompteByIdClient(clientId);
    }
    public Optional<List<Compte>> getCompteByDateCreation(LocalDateTime dateCreation){
        return  compteRepository.findCompteByDateCreation(dateCreation);
    }
    public Optional<List<Compte>> getCompteByTypeCompte(TypeCompte typeCompte){
        return  compteRepository.findCompteByTypeCompte(typeCompte);
    }
}
