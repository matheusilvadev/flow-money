package flowmoney.backend.controller.activity.dtos.mapper;

import flowmoney.backend.controller.activity.dtos.InsertActivityResponseDTO;
import flowmoney.backend.service.activity.dtos.InsertActivityOutputDTO;

import java.util.function.Function;

public class InsertActivityOutputServiceToInsertActivityResponseMapper implements Function<InsertActivityOutputDTO, InsertActivityResponseDTO> {

    public static InsertActivityOutputServiceToInsertActivityResponseMapper build(){
        return new InsertActivityOutputServiceToInsertActivityResponseMapper();
    }

    @Override
    public InsertActivityResponseDTO apply(final InsertActivityOutputDTO input) {
        return new InsertActivityResponseDTO(
                input.id(),
                input.date(),
                input.description(),
                input.value(),
                input.type(),
                input.createdAt(),
                input.updatedAt());
    }

}
