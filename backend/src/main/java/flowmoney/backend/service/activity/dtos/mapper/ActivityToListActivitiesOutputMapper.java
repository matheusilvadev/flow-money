package flowmoney.backend.service.activity.dtos.mapper;

import flowmoney.backend.domain.activity.Activity;
import flowmoney.backend.service.activity.dtos.ListActivitiesOutputDTO;

import java.util.function.Function;

public class ActivityToListActivitiesOutputMapper implements Function<Activity, ListActivitiesOutputDTO> {

    public static ActivityToListActivitiesOutputMapper build(){
        return new ActivityToListActivitiesOutputMapper();
    }

    @Override
    public ListActivitiesOutputDTO apply(final Activity activity) {
        return new ListActivitiesOutputDTO(activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType().getValue(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }
}
