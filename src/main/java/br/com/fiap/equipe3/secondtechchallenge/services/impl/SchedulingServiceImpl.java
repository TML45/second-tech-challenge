package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.NotFoundException;
import br.com.fiap.equipe3.secondtechchallenge.models.Scheduling;
import br.com.fiap.equipe3.secondtechchallenge.models.SchedulingStatusDTO;
import br.com.fiap.equipe3.secondtechchallenge.models.enums.SchedulingStatus;
import br.com.fiap.equipe3.secondtechchallenge.repository.SchedulingRepository;
import br.com.fiap.equipe3.secondtechchallenge.repository.VehicleRepository;
import br.com.fiap.equipe3.secondtechchallenge.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Scheduling save(Scheduling scheduling) {
        this.vehicleRepository.findById(scheduling.getVehiclePlate())
                .orElseThrow(() -> new NotFoundException("Vehicle not found."));

        return this.schedulingRepository.save(scheduling);
    }

    @Override
    public List<Scheduling> findAll() {
        return this.schedulingRepository.findAll();
    }

    @Override
    public Scheduling findById(String id) {
        return this.schedulingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Scheduling not found."));
    }

    @Override
    public Scheduling update(Scheduling scheduling) {
        if (!this.schedulingRepository.existsById(scheduling.getId())) {
            throw new NotFoundException("Scheduling not found.");
        }

        return this.schedulingRepository.save(scheduling);
    }

    @Override
    public SchedulingStatusDTO findSchedulingStatusByPlate(String plate) {
        Scheduling scheduling = this.getLatestSchedulingBasedOnStartDateByPlate(plate);

        if (scheduling == null) return new SchedulingStatusDTO(SchedulingStatus.NOT_SCHEDULED, null);

        LocalDateTime startDate = scheduling.getStartDate();
        Integer contractedHours = scheduling.getContractedHours();
        LocalDateTime endDate = startDate.plusHours(contractedHours);

        boolean isExpired = LocalDateTime.now().isAfter(endDate);
        SchedulingStatus status = isExpired ? SchedulingStatus.EXPIRED : SchedulingStatus.OK;
        return new SchedulingStatusDTO(status, endDate);
    }

    private Scheduling getLatestSchedulingBasedOnStartDateByPlate(String plate) {
        Criteria criteria = Criteria.where("vehiclePlate").is(plate);
        Query query = new Query(criteria).with(Sort.by(Sort.Direction.DESC, "startDate")).limit(1);

        return this.mongoTemplate.findOne(query, Scheduling.class);
    }
}
