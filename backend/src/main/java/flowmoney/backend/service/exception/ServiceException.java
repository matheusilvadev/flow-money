package flowmoney.backend.service.exception;

public class ServiceException extends RuntimeException{
    public ServiceException(final String aMessage){
        super(aMessage);
    }
}
