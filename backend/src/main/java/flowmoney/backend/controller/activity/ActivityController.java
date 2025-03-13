package flowmoney.backend.controller.activity;

import flowmoney.backend.controller.activity.dtos.CalculateBalanceResponseDTO;
import flowmoney.backend.controller.activity.dtos.InsertActivityRequestDTO;
import flowmoney.backend.controller.activity.dtos.InsertActivityResponseDTO;
import flowmoney.backend.controller.activity.dtos.ListActivitiesResponseDTO;
import flowmoney.backend.controller.activity.dtos.mapper.InsertActivityOutputServiceToInsertActivityResponseMapper;
import flowmoney.backend.controller.activity.dtos.mapper.InsertActivityRequestToInsertActivityServiceMapper;
import flowmoney.backend.controller.activity.dtos.mapper.ListActivitiesToListActivitiesResponseMapper;
import flowmoney.backend.repository.activity.ActivityJpaGateway;
import flowmoney.backend.repository.activity.jpa.ActivityJpaRepository;
import flowmoney.backend.service.activity.implementation.ActivityServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityJpaRepository repository;

    @GetMapping
    public ResponseEntity<ListActivitiesResponseDTO> listActivities(){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        final var aList = aService.listActivities();

        final var aResponse = ListActivitiesToListActivitiesResponseMapper
                .build()
                .apply(aList);

        return ResponseEntity.ok().body(aResponse);

    }

    @PostMapping
    public ResponseEntity<InsertActivityResponseDTO> insertActivity(@RequestBody InsertActivityRequestDTO input){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        final var aServiceInput = InsertActivityRequestToInsertActivityServiceMapper.build()
                .apply(input);
        final var aServiceResponse = aService.insertActivity(aServiceInput);

        final var aResponse = InsertActivityOutputServiceToInsertActivityResponseMapper.build()
                .apply(aServiceResponse);

        return ResponseEntity.ok().body(aResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActivityById(@PathVariable("id") final String anId){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        aService.removeActivity(anId);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/balance")
    public ResponseEntity<CalculateBalanceResponseDTO> calculateBalance(){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        final var aServiceResponse = aService.calculateBalance();
        final var aResponse = new CalculateBalanceResponseDTO(aServiceResponse);

        return ResponseEntity.ok().body(aResponse);

    }

}
