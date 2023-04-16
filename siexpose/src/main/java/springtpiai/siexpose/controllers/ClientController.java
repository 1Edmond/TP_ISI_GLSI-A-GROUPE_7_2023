package springtpiai.siexpose.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtpiai.siexpose.allEnums.Sexe;
import springtpiai.siexpose.models.Client;
import springtpiai.siexpose.models.Client;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.services.ClientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("")
    public ResponseEntity<List<Client>> getClients(){
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.valueOf(200));
    }
    @GetMapping("{id}")
    public Optional<Client> getClient(@PathVariable  Integer id){
        return  clientService.getOneClient(id);
    }
    @PostMapping("")
    public Client addClient(@RequestBody Client client){
        return  clientService.addClient(client);
    }
    @DeleteMapping("{numClient}")
    public void deleteClient(@PathVariable Integer numClient) throws InvalideNumeroClientException {
        clientService.deleteClient(numClient);
    }
    @PutMapping("{numClient}")
    public  Client UpdateClient(@PathVariable Integer numClient ,@RequestBody Client client) throws InvalideNumeroClientException {
        return clientService.updateClient(numClient, client);
    }
    @GetMapping("nom/{nom}")
    public Optional<List<Client>> getClientsByNom(@PathVariable String nom){
        return clientService.getClientByNom(nom);
    }
    @GetMapping("prenom/{prenom}")
    public Optional<List<Client>> getClientsByPrenom(@PathVariable String prenom){
        return clientService.getClientByPrenom(prenom);
    }
    @GetMapping("adresse/{adresse}")
    public Optional<List<Client>> getClientsByAdresse(@PathVariable String adresse){
        return clientService.getClientByAdresse(adresse);
    }
    @GetMapping("telephone/{telephone}")
    public Optional<List<Client>> getClientsByTelephone(@PathVariable String telephone){
        return clientService.getClientByTelephone(telephone);
    }
    @GetMapping("mail/{mail}")
    public Optional<List<Client>> getClientsByMail(@PathVariable String mail){
        return clientService.getClientByMail(mail);
    }
    @GetMapping("nationalite/{nationalite}")
    public Optional<List<Client>> getClientsByNationalite(@PathVariable String nationalite){
        return clientService.getClientByNationalite(nationalite);
    }
    @GetMapping("sexe/{sexe}")
    public Optional<List<Client>> getClientsBySexe(@PathVariable Sexe sexe){
        return clientService.getClientBySexe(sexe);
    }
    @GetMapping("dateNaissance/{dateNaissance}")
    public Optional<List<Client>> getClientsByDateNaissance(@PathVariable LocalDate dateNaissance){
        return clientService.getClientByDateNaissance(dateNaissance);
    }

}
