package springtpiai.siexpose.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import springtpiai.siexpose.allEnums.Sexe;
import jakarta.transaction.Transactional;
import springtpiai.siexpose.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.repositorys.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
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
        return clientRepository.save(client);
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

}
