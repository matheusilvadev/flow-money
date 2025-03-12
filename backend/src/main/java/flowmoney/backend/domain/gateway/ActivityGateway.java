package flowmoney.backend.domain.gateway;

import flowmoney.backend.domain.activity.Activity;

import java.util.List;

public interface ActivityGateway {

    void create(final Activity anActivity);
    void delete(final String id);
    List<Activity> findAll();
}
