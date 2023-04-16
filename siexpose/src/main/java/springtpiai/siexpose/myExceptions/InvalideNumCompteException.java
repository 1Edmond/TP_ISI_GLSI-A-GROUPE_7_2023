package springtpiai.siexpose.myExceptions;

public class InvalideNumCompteException extends  Exception {


    public  InvalideNumCompteException(String numCompte) {
        super("Le numéro de compte " +  numCompte + " entré n'existe pas");
    }
}
