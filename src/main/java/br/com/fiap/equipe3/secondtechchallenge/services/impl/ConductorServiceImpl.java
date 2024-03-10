package br.com.fiap.equipe3.secondtechchallenge.services.impl;

import br.com.fiap.equipe3.secondtechchallenge.controllers.exception.NotFoundException;
import br.com.fiap.equipe3.secondtechchallenge.models.documents.Conductor;
import br.com.fiap.equipe3.secondtechchallenge.repository.ConductorRepository;
import br.com.fiap.equipe3.secondtechchallenge.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public Conductor save(Conductor conductor) {
        return this.conductorRepository.save(conductor);
    }

    @Override
    public List<Conductor> findAll() {
        return this.conductorRepository.findAll();
    }

    @Override
    public Conductor findById(String id) {
        return this.conductorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conductor not found."));
    }
}
