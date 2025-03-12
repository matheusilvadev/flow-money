package flowmoney.backend.service.activity.dtos.mapper;

import flowmoney.backend.domain.activity.Activity;
import flowmoney.backend.service.activity.dtos.InsertActivityOutputDTO;

import java.util.function.Function;

public class ActivityToInsertActivityOutputMapper implements Function<Activity, InsertActivityOutputDTO> {

    public static ActivityToInsertActivityOutputMapper build(){
        return new ActivityToInsertActivityOutputMapper();
    }

    @Override
    public InsertActivityOutputDTO apply(Activity activity) {
        return new InsertActivityOutputDTO(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType().getValue(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }
}
