package flowmoney.backend.service.activity.dtos.mapper;

import flowmoney.backend.domain.activity.Activity;
import flowmoney.backend.domain.activity.Type;
import flowmoney.backend.service.activity.dtos.InsertActivityInputDTO;
import flowmoney.backend.service.exception.ServiceException;

import java.util.function.Function;

public class InsertActivityInputToActivityMapper implements Function<InsertActivityInputDTO, Activity> {

    public static InsertActivityInputToActivityMapper build(){
        return new InsertActivityInputToActivityMapper();
    }

    @Override
    public Activity apply(InsertActivityInputDTO input) {
        if (input.type().trim().toUpperCase().equals(Type.REVENUE.toString())){
            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.REVENUE);
        } else if (input.type().trim().toUpperCase().equals(Type.EXPENSE.toString())){
            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    Type.EXPENSE);
        } else {
            throw new ServiceException("Invalid activity TYPE");
        }
    }
}
