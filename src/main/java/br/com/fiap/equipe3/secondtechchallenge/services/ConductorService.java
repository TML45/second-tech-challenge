package br.com.fiap.equipe3.secondtechchallenge.services;

import br.com.fiap.equipe3.secondtechchallenge.models.documents.Conductor;

import java.util.List;

public interface ConductorService {
    Conductor save(Conductor conductor);

    List<Conductor> findAll();

    Conductor findById(String id);
}
