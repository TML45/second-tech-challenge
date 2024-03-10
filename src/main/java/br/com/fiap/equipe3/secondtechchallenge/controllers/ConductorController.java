package br.com.fiap.equipe3.secondtechchallenge.controllers;

import br.com.fiap.equipe3.secondtechchallenge.models.documents.Conductor;
import br.com.fiap.equipe3.secondtechchallenge.models.dtos.ConductorRequestDTO;
import br.com.fiap.equipe3.secondtechchallenge.models.dtos.ConductorResponseDTO;
import br.com.fiap.equipe3.secondtechchallenge.services.ConductorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductors")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @PostMapping
    public ResponseEntity<ConductorResponseDTO> save(@Valid @RequestBody ConductorRequestDTO conductorRequestDTO) {
        Conductor savedConductor = this.conductorService.save(conductorRequestDTO.toDocument());

        ConductorResponseDTO conductorResponseDTO = new ConductorResponseDTO(savedConductor);

        return ResponseEntity.status(HttpStatus.CREATED).body(conductorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConductorResponseDTO>> findAll() {
        var conductorsList = this.conductorService.findAll();

        var conductorResponseDTOList = conductorsList.stream().map(ConductorResponseDTO::new).toList();

        return ResponseEntity.ok(conductorResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> findBy(@PathVariable String id) {
        Conductor foundedConductor = this.conductorService.findById(id);

        return ResponseEntity.ok(new ConductorResponseDTO(foundedConductor));
    }
}
