package springtpiai.siexpose.records;

import springtpiai.siexpose.models.Compte;

public record VirementCompteResponse(Compte emetteur, Compte recepteur, float solde) {
}
