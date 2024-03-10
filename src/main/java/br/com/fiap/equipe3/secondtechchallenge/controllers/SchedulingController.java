package br.com.fiap.equipe3.secondtechchallenge.controllers;

import br.com.fiap.equipe3.secondtechchallenge.models.Scheduling;
import br.com.fiap.equipe3.secondtechchallenge.models.SchedulingStatusDTO;
import br.com.fiap.equipe3.secondtechchallenge.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedulings")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @PostMapping
    public ResponseEntity<Scheduling> save(@RequestBody Scheduling scheduling) {
        Scheduling savedScheduling = this.schedulingService.save(scheduling);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduling);
    }

    @GetMapping
    public ResponseEntity<List<Scheduling>> findAll() {
        var schedulingList = this.schedulingService.findAll();

        return ResponseEntity.ok(schedulingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scheduling> findBy(@PathVariable String id) {
        Scheduling foundedScheduling = this.schedulingService.findById(id);

        return ResponseEntity.ok(foundedScheduling);
    }

    @GetMapping("/{plate}/status")
    public ResponseEntity<SchedulingStatusDTO> checkSchedulingStatusByPlate(@PathVariable String plate) {
        SchedulingStatusDTO schedulingStatusDTO = this.schedulingService.findSchedulingStatusByPlate(plate);

        return ResponseEntity.ok(schedulingStatusDTO);
    }
}
