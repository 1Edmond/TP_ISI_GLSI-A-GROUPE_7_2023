package springtpiai.siexpose.myExceptions;

public class SoldeInsufisantException extends  Exception{

    public  SoldeInsufisantException(String numCompte){
        super("Le solde du compte " + numCompte + " est insuffisant pour effectuer l'opération");
    }
}
