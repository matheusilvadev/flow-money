package flowmoney.backend.service.auth.dtos.mapper;

import flowmoney.backend.controller.authentication.dto.LoginRequestDTO;
import flowmoney.backend.service.auth.dtos.LoginServiceInputDTO;

import java.util.function.Function;

public class LoginRequestToLoginServiceInputMapper implements Function<LoginRequestDTO, LoginServiceInputDTO> {
    public static LoginRequestToLoginServiceInputMapper build(){
        return new LoginRequestToLoginServiceInputMapper();
    }

    @Override
    public LoginServiceInputDTO apply(LoginRequestDTO input) {
        return new LoginServiceInputDTO(input.email(), input.password());
    }
}
