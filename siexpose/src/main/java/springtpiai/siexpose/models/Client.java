package springtpiai.siexpose.models;

import jakarta.persistence.*;
import springtpiai.siexpose.allEnums.Sexe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
@Table(name = "Clients")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nom, prenom, adresse, telephone, mail, nationalite;
    Sexe sexe;
    LocalDate dateNaissance;
    @OneToMany(targetEntity = Compte.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="idClient",referencedColumnName = "id")
    List<Compte> Comptes;

}
