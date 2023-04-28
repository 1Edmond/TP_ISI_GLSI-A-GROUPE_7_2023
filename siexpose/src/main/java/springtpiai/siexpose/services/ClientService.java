package springtpiai.siexpose.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import springtpiai.siexpose.allEnums.Sexe;
import springtpiai.siexpose.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtpiai.siexpose.models.Compte;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.repositories.ClientRepository;
import springtpiai.siexpose.repositories.CompteRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompteRepository compteRepository;
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Optional<Client> getOneClient(Integer id){
        return  clientRepository.findById(id);
    }
    public  Client addClient(Client client){
        return clientRepository.save(client);
    }
    public void deleteClient(Integer id) throws InvalideNumeroClientException {
        Optional<Client> optionalClient = getOneClient(id);
        if(optionalClient.isEmpty())
            throw  new InvalideNumeroClientException(id);
         clientRepository.deleteById(id);
    }
    public Client updateClient(Integer id, Client client) throws InvalideNumeroClientException {
        Optional<Client> optionalClient = getOneClient(id);
        if(optionalClient.isEmpty())
            throw  new InvalideNumeroClientException(id);
        var oldClient = optionalClient.get();
        if(client.getDateNaissance() != null && client.getDateNaissance() != oldClient.getDateNaissance())
          oldClient.setDateNaissance(client.getDateNaissance());
        if(client.getPrenom() != null && !client.getPrenom().equals(oldClient.getPrenom()))
          oldClient.setPrenom(client.getPrenom());
        if(client.getNom() != null && !client.getNom().equals(oldClient.getNom()))
          oldClient.setNom(client.getNom());
        if(client.getMail() != null && !client.getMail().equals(oldClient.getMail()))
          oldClient.setMail(client.getMail());
        if(client.getNationalite() != null && !client.getNationalite().equals(oldClient.getNationalite()))
          oldClient.setNationalite(client.getNationalite());
        if(client.getAdresse() != null && !client.getAdresse().equals(oldClient.getAdresse()))
          oldClient.setAdresse(client.getAdresse());
        if(client.getTelephone() != null && !client.getTelephone().equals(oldClient.getTelephone()))
          oldClient.setTelephone(client.getTelephone());
        if(client.getSexe() != null && !client.getSexe().equals(oldClient.getSexe()))
          oldClient.setSexe(client.getSexe());
        return clientRepository.save(oldClient);
    }

    public Optional<List<Client>> getClientBySexe(Sexe sexe){
        return  clientRepository.findClientBySexe(sexe);
    }
    public Optional<List<Client>> getClientByNom(String nom){
        return  clientRepository.findClientByNom(nom);
    }
    public Optional<List<Client>> getClientByPrenom(String prenom){
        return  clientRepository.findClientByPrenom(prenom);
    }
    public Optional<List<Client>> getClientByAdresse(String adresse){
        return  clientRepository.findClientByAdresse(adresse);
    }
    public Optional<List<Client>> getClientByTelephone(String telephone){
        return  clientRepository.findClientByTelephone(telephone);
    }
    public Optional<List<Client>> getClientByMail(String mail){
        return  clientRepository.findClientByMail(mail);
    }
    public Optional<List<Client>> getClientByNationalite(String nationalite){
        return  clientRepository.findClientByNationalite(nationalite);
    }
    public Optional<List<Client>> getClientByDateNaissance(LocalDate dateNaissance){
        return  clientRepository.findClientByDateNaissance(dateNaissance);
    }

    public Optional<List<Compte>> getClientCompte(Integer clientId){
        return compteRepository.findCompteByIdClient(clientId);
    }

}
