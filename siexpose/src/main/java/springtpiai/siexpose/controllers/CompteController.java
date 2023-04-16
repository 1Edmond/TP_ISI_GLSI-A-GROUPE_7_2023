package springtpiai.siexpose.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtpiai.siexpose.myExceptions.InvalideNumCompteException;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.myExceptions.SoldeInsufisantException;
import springtpiai.siexpose.allEnums.TypeCompte;
import springtpiai.siexpose.models.Compte;
import springtpiai.siexpose.records.CompteOperationRequest;
import springtpiai.siexpose.records.VirementCompteRequest;
import springtpiai.siexpose.records.VirementCompteResponse;
import springtpiai.siexpose.services.CompteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {
    @Autowired
    CompteService compteService;
    @GetMapping("")
    public List<Compte> getComptes(){
        return  compteService.getAllComptes();
    }
    @PostMapping("")
    public Compte addCompte(@RequestBody Compte compte) throws InvalideNumeroClientException {
        return  compteService.addCompte(compte);
    }
    @DeleteMapping("{numCompte}")
    public void deleteCompte(@PathVariable String numCompte){
        compteService.deleteCompte(numCompte);
    }
    @PutMapping("{numCompte}")
    public Compte UpdateCompte(@RequestBody Compte compte, @PathVariable String numCompte) throws InvalideNumCompteException {
        return compteService.updateCompte(numCompte,compte);
    }
    @GetMapping("{numCompte}")
    public Optional<Compte> getCompteByNumCompte(@PathVariable String numCompte){
        return  compteService.getOneCompte(numCompte);
    }
    @GetMapping("solde/{solde}")
    public Optional<List<Compte>> getComptesBySolde(@PathVariable float solde){
        return  compteService.getCompteBySolde(solde);
    }
    @GetMapping("dateCreation/{dateCreation}")
    public Optional<List<Compte>> getComptesByDateCreation(@PathVariable LocalDateTime dateCreation){
        return  compteService.getCompteByDateCreation(dateCreation);
    }
    @GetMapping("client/{idClient}")
    public Optional<List<Compte>> getComptesByClient(@PathVariable Integer idClient){
        return  compteService.getCompteByClient(idClient);
    }
    @GetMapping("typeCompte/{typeCompte}")
    public Optional<List<Compte>> getComptesByTypeCompte(@PathVariable TypeCompte typeCompte){
        return  compteService.getCompteByTypeCompte(typeCompte);
    }
    @PostMapping("debiter")
    public Compte debiterCompte(@RequestBody CompteOperationRequest request) throws InvalideNumCompteException {
        return  compteService.debiterCompte(request.numCompte(),request.solde());
    }
    @PostMapping("crediter")
    public Compte crediterCompte(@RequestBody CompteOperationRequest request) throws InvalideNumCompteException, SoldeInsufisantException {
        return  compteService.crediterCompte(request.numCompte(),request.solde());
    }
    @PostMapping("virement")
    public ResponseEntity<VirementCompteResponse> virement(@RequestBody VirementCompteRequest request) throws InvalideNumCompteException, SoldeInsufisantException {
        var rep = compteService.faireVirement(request.numCompteEmetteur(),request.numCompteRecepteur(),request.solde());
        return new ResponseEntity(rep, HttpStatus.valueOf(200));
    }

}
