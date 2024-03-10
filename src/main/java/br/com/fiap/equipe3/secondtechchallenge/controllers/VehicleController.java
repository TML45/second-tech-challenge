package br.com.fiap.equipe3.secondtechchallenge.controllers;

import br.com.fiap.equipe3.secondtechchallenge.models.Vehicle;
import br.com.fiap.equipe3.secondtechchallenge.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> save(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = this.vehicleService.save(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        var vehicleList = this.vehicleService.findAll();

        return ResponseEntity.ok(vehicleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findBy(@PathVariable String id) {
        Vehicle foundedVehicle = this.vehicleService.findById(id);

        return ResponseEntity.ok(foundedVehicle);
    }
}
