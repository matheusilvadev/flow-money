package flowmoney.backend.controller.authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRequestDTO(
        @NotBlank(message = "TOKEN should not be blank")
        String token) {
}
