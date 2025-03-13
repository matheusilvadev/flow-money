package flowmoney.backend.controller.authentication;

import flowmoney.backend.controller.authentication.dto.LoginRequestDTO;
import flowmoney.backend.controller.authentication.dto.LoginResponseDTO;
import flowmoney.backend.controller.authentication.dto.ValidateRequestDTO;
import flowmoney.backend.controller.authentication.dto.ValidateResponseDTO;
import flowmoney.backend.service.auth.AuthService;
import flowmoney.backend.service.auth.dtos.mapper.LoginRequestToLoginServiceInputMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid final LoginRequestDTO input){
        final var aServiceInput = LoginRequestToLoginServiceInputMapper.build().apply(input);
        final var aToken = service.login(aServiceInput).token();

        final var aResponse = new LoginResponseDTO(aToken);

        return ResponseEntity.ok(aResponse);

    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDTO> validate(@RequestBody @Valid final ValidateRequestDTO input){
        final var aServiceOutput = this.service.validateToken(input.token());
        //Is not correct insert this rule here
        var isValid = false;

        if (!aServiceOutput.isBlank()){
            isValid = true;
        }

        return ResponseEntity.ok().body(new ValidateResponseDTO(isValid));

    }

}
