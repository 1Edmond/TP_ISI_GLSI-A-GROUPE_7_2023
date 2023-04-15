package springtpiai.siexpose.repositorys;

import springtpiai.siexpose.allEnums.Sexe;
import springtpiai.siexpose.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<List<Client>> findClientBySexe(Sexe sexe);
    Optional<List<Client>> findClientByNom(String nom);
    Optional<List<Client>> findClientByPrenom(String prenom);
    Optional<List<Client>> findClientByAdresse(String adresse);
    Optional<List<Client>> findClientByTelephone(String telephone);
    Optional<List<Client>> findClientByMail(String mail);
    Optional<List<Client>> findClientByNationalite(String nationalite);
    Optional<List<Client>> findClientByDateNaissance(LocalDate dateNaissance);

}
