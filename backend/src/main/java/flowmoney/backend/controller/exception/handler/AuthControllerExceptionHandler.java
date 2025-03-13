package flowmoney.backend.controller.exception.handler;

import flowmoney.backend.controller.exception.handler.body.ExceptionResponseBody;
import flowmoney.backend.utils.InstantUtils;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;

@ControllerAdvice("flowmoney.backend.controller.activity.authentication")
public class AuthControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {LoginException.class})
    protected ResponseEntity<ExceptionResponseBody> handleLoginException(
            final LoginException anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {AuthException.class})
    protected ResponseEntity<ExceptionResponseBody> handleAuthException(
            final AuthException anException,
            final HttpServletRequest aRequest){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
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
