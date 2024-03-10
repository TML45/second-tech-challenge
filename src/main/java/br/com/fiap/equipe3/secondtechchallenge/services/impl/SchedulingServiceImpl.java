package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.NotFoundException;
import br.com.fiap.equipe3.secondtechchallenge.models.Conductor;
import br.com.fiap.equipe3.secondtechchallenge.models.Scheduling;
import br.com.fiap.equipe3.secondtechchallenge.repository.ConductorsRepository;
import br.com.fiap.equipe3.secondtechchallenge.repository.SchedulingRepository;
import br.com.fiap.equipe3.secondtechchallenge.repository.VehicleRepository;
import br.com.fiap.equipe3.secondtechchallenge.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

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
}
