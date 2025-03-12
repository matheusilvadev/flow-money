package flowmoney.backend.service.activity.implementation;

import flowmoney.backend.domain.activity.Type;
import flowmoney.backend.domain.gateway.ActivityGateway;
import flowmoney.backend.service.activity.ActivityService;
import flowmoney.backend.service.activity.dtos.InsertActivityInputDTO;
import flowmoney.backend.service.activity.dtos.InsertActivityOutputDTO;
import flowmoney.backend.service.activity.dtos.ListActivitiesOutputDTO;
import flowmoney.backend.service.activity.dtos.mapper.ActivityToInsertActivityOutputMapper;
import flowmoney.backend.service.activity.dtos.mapper.ActivityToListActivitiesOutputMapper;
import flowmoney.backend.service.activity.dtos.mapper.InsertActivityInputToActivityMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceIMP implements ActivityService {

    private ActivityGateway gateway;

    private ActivityServiceIMP(final ActivityGateway aGateway){
        this.gateway = aGateway;
    }

    private static ActivityServiceIMP build(final ActivityGateway aGateway){
        return new ActivityServiceIMP(aGateway);
    }

    //Implementation of the method for inserting a new activity
    @Override
    public InsertActivityOutputDTO insertActivity(InsertActivityInputDTO input) {
        final var anActivity = InsertActivityInputToActivityMapper.build().apply(input);
        this.gateway.create(anActivity);

        return ActivityToInsertActivityOutputMapper.build().apply(anActivity);
    }

    //Implementation of the activity removal method by id
    @Override
    public void removeActivity(String anId) {
        this.gateway.delete(anId);
    }

    //Implementation of the method of listing existing activities
    @Override
    public List<ListActivitiesOutputDTO> listActivities() {

        final var aList = this.gateway.findAll();

        return aList.stream()
                .map(a -> ActivityToListActivitiesOutputMapper.build()
                        .apply(a))
                .collect(Collectors.toList());
    }

    //Implementation of the method to calculate the
    //               value from the sum of revenues and subtraction of expenses
    @Override
    public float calculateBalance() {
        final var aList = this.gateway.findAll();

        if (aList == null || aList.size() == 0){
            return 0;
        }

        return (float) aList.stream()
                .mapToDouble(
                        a -> a.getType() == Type.REVENUE
                        ? a.getValue() : -a.getValue())
                .sum();
    }
}
