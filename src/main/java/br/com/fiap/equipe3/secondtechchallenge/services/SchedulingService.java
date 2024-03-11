package br.com.fiap.equipe3.secondtechchallenge.services;

import br.com.fiap.equipe3.secondtechchallenge.models.Scheduling;
import br.com.fiap.equipe3.secondtechchallenge.models.SchedulingStatusDTO;

import java.util.List;

public interface SchedulingService {
    Scheduling save(Scheduling scheduling);

    List<Scheduling> findAll();

    Scheduling findById(String id);

    Scheduling update(Scheduling scheduling);

    SchedulingStatusDTO findSchedulingStatusByPlate(String plate);
}
