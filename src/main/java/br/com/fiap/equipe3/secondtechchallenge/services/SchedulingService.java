package br.com.fiap.equipe3.secondtechchallenge.services;

import br.com.fiap.equipe3.secondtechchallenge.models.Scheduling;
import br.com.fiap.equipe3.secondtechchallenge.models.Vehicle;

import java.util.List;

public interface SchedulingService {
    Scheduling save(Scheduling scheduling);

    List<Scheduling> findAll();

    Scheduling findById(String id);
}
