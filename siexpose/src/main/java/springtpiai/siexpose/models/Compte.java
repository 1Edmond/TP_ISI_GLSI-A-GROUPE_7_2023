package springtpiai.siexpose.models;

import jakarta.persistence.*;
import springtpiai.siexpose.allEnums.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Comptes")
public class Compte {

    @Id
    String numCompte;
    float solde;

    int idClient;

    LocalDateTime dateCreation;

    TypeCompte typeCompte;


}
