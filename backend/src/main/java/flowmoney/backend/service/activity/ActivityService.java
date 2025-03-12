package flowmoney.backend.service.activity;

import flowmoney.backend.service.activity.dtos.InsertActivityInputDTO;
import flowmoney.backend.service.activity.dtos.InsertActivityOutputDTO;
import flowmoney.backend.service.activity.dtos.ListActivitiesOutputDTO;

import java.util.List;

public interface ActivityService {

    InsertActivityOutputDTO insertActivity(final InsertActivityInputDTO input);
    void removeActivity(final String anId);
    List<ListActivitiesOutputDTO> listActivities();
    float calculateBalance();
}
