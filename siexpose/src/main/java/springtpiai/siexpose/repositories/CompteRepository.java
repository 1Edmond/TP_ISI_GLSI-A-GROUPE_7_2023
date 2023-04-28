package springtpiai.siexpose.repositories;

import springtpiai.siexpose.allEnums.TypeCompte;
import springtpiai.siexpose.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository  extends JpaRepository<Compte, String> {
    Optional<List<Compte>> findCompteBySolde(float solde);
    Optional<List<Compte>> findCompteByIdClient(Integer clientId);
    Optional<List<Compte>> findCompteByTypeCompte(TypeCompte typeCompte);
    Optional<List<Compte>> findCompteByDateCreation(LocalDateTime dateCreation);

}
