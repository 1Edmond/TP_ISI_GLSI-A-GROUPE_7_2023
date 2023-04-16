package springtpiai.siexpose.myExceptions;


public class InvalideNumeroClientException extends  Exception {

    public InvalideNumeroClientException(Integer id){
        super("Le client " + id + " n 'existe pas");
    }

}
