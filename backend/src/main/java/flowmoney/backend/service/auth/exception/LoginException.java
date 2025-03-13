package flowmoney.backend.service.auth.exception;

public class LoginException extends RuntimeException{
    public LoginException(final String aMessage){
        super(aMessage);
    }
}
