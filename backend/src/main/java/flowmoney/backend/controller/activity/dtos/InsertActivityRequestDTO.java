package flowmoney.backend.controller.activity.dtos;

import java.time.Instant;

public record InsertActivityRequestDTO(String description,
                                       Instant date,
                                       String type,
                                       float value) {
}
