package flowmoney.backend.controller.activity.dtos.mapper;

import flowmoney.backend.controller.activity.dtos.InsertActivityRequestDTO;
import flowmoney.backend.service.activity.dtos.InsertActivityInputDTO;

import java.util.function.Function;

public class InsertActivityRequestToInsertActivityServiceMapper implements Function<InsertActivityRequestDTO, InsertActivityInputDTO> {

    public static InsertActivityRequestToInsertActivityServiceMapper build(){
        return new InsertActivityRequestToInsertActivityServiceMapper();
    }

    @Override
    public InsertActivityInputDTO apply(final InsertActivityRequestDTO input) {

        return new InsertActivityInputDTO(input.date(), input.description(),input.type(), input.value());

    }

}
