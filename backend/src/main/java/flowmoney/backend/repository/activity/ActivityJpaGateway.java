package flowmoney.backend.repository.activity;

import flowmoney.backend.domain.activity.Activity;
import flowmoney.backend.domain.gateway.ActivityGateway;
import flowmoney.backend.repository.activity.exception.PersistenceException;
import flowmoney.backend.repository.activity.jpa.ActivityJpaEntity;
import flowmoney.backend.repository.activity.jpa.ActivityJpaRepository;
import org.hibernate.dialect.lock.OptimisticEntityLockException;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityJpaGateway implements ActivityGateway {

    private ActivityJpaRepository repository;

    private ActivityJpaGateway(final ActivityJpaRepository aRepository ){
        this.repository = aRepository;
    }

    public static ActivityJpaGateway build(final ActivityJpaRepository aRepository){
        return new ActivityJpaGateway(aRepository);
    }

    @Override
    public void create(Activity anActivity) {
        final var anActivityEntity = ActivityJpaEntity.from(anActivity);

        try{
            this.repository.save(anActivityEntity);
        }catch (IllegalArgumentException | OptimisticEntityLockException e){
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try{
            this.repository.deleteById(id);
        } catch (IllegalArgumentException e){
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Activity> findAll() {
        final var aList = this.repository.findAll();

        return aList.stream()
                .map(activityJpaEntity -> activityJpaEntity.toModel())
                .collect(Collectors.toList());
    }
}
