package flowmoney.backend.service.activity.dtos;

import java.time.Instant;

public record InsertActivityInputDTO(Instant date,
                                     String description,
                                     String type,
                                     float value) {
}
