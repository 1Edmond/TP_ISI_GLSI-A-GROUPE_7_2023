package springtpiai.siexpose.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springtpiai.siexpose.myExceptions.InvalideNumCompteException;
import springtpiai.siexpose.myExceptions.InvalideNumeroClientException;
import springtpiai.siexpose.myExceptions.SoldeInsufisantException;
import springtpiai.siexpose.myExceptions.SoldeNegativeException;

@ControllerAdvice
public class MyExceptionsHandler {
    @ExceptionHandler(InvalideNumCompteException.class)
    public ResponseEntity<String> handlerInvalideNumCompteException(InvalideNumCompteException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SoldeInsufisantException.class)
    public ResponseEntity<String> handlerSoldeInsufisantException(SoldeInsufisantException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalideNumeroClientException.class)
    public ResponseEntity<String> handlerInvalideNumeroClientException(InvalideNumeroClientException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SoldeNegativeException.class)
    public ResponseEntity<String> handlerSoldeNegativeException(SoldeNegativeException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
