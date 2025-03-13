package flowmoney.backend.controller.activity.dtos.mapper;

import flowmoney.backend.controller.activity.dtos.ActivityDTO;
import flowmoney.backend.controller.activity.dtos.ListActivitiesResponseDTO;
import flowmoney.backend.service.activity.dtos.ListActivitiesOutputDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListActivitiesToListActivitiesResponseMapper implements Function<List<ListActivitiesOutputDTO>, ListActivitiesResponseDTO> {

    public static ListActivitiesToListActivitiesResponseMapper build(){
        return new ListActivitiesToListActivitiesResponseMapper();
    }

    @Override
    public ListActivitiesResponseDTO apply(List<ListActivitiesOutputDTO> input) {
        final var aList = input.stream()
                .map(a -> new ActivityDTO(
                        a.id(),
                        a.date(),
                        a.description(),
                        a.type(),
                        a.value()
                ))
                .collect(Collectors.toList());

        return new ListActivitiesResponseDTO(aList);
    }

}
