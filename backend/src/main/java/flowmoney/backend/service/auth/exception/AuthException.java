package flowmoney.backend.service.auth.exception;

public class AuthException extends RuntimeException {
    public AuthException(final String aMessage){
        super(aMessage);
    }
}
