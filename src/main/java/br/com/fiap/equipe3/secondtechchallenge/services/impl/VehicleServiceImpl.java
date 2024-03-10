package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.NotFoundException;
import br.com.fiap.equipe3.secondtechchallenge.models.Conductor;
import br.com.fiap.equipe3.secondtechchallenge.models.Vehicle;
import br.com.fiap.equipe3.secondtechchallenge.repository.ConductorsRepository;
import br.com.fiap.equipe3.secondtechchallenge.repository.VehicleRepository;
import br.com.fiap.equipe3.secondtechchallenge.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ConductorsRepository conductorsRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        Conductor conductorToSetInVehicle = null;

        Conductor conductorReceived = vehicle.getConductor();

        if (conductorReceived != null) {
            if (conductorReceived.getId() != null) {
                Optional<Conductor> conductorStored = this.conductorsRepository.findById(conductorReceived.getId());
                if (conductorStored.isPresent()) {
                    conductorToSetInVehicle = conductorStored.get();
                }
            } else {
                conductorToSetInVehicle = this.conductorsRepository.save(conductorReceived);
            }
        }

        vehicle.setConductor(conductorToSetInVehicle);

        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(String id) {
        return this.vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conductor not found."));
    }
}
