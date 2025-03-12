package flowmoney.backend.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(final String aMessage){
        super(aMessage);
    }
}
