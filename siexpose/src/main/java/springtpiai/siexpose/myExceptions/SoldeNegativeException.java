package springtpiai.siexpose.myExceptions;

public class SoldeNegativeException extends  Exception {

    public SoldeNegativeException() {
        super("Le montant ne doit pas être négative ");
    }
}
