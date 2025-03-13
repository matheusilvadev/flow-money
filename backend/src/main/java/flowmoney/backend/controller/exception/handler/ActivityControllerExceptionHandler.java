package flowmoney.backend.controller.exception.handler;

import flowmoney.backend.controller.exception.handler.body.ExceptionResponseBody;
import flowmoney.backend.domain.exception.DomainException;
import flowmoney.backend.repository.activity.exception.PersistenceException;
import flowmoney.backend.service.exception.ServiceException;
import flowmoney.backend.utils.InstantUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("flowmoney.backend.controller.activity")
public class ActivityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    protected ResponseEntity<ExceptionResponseBody> handleDomainException(
            final DomainException anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {PersistenceException.class})
    protected ResponseEntity<ExceptionResponseBody> handleDomainException(
            final PersistenceException anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<ExceptionResponseBody> handleDomainException(
            final ServiceException anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ExceptionResponseBody> handleDomainException(
            final Exception anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

}
