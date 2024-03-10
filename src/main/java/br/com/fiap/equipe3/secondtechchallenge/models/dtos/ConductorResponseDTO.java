package br.com.fiap.equipe3.secondtechchallenge.models.dtos;

import br.com.fiap.equipe3.secondtechchallenge.models.documents.Conductor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ConductorResponseDTO {
    private String id;

    private String name;
    private String cpf;
    private String email;
    private String cellphone;

    private String[] vehicles;

    public ConductorResponseDTO(Conductor conductorDoc) {
        BeanUtils.copyProperties(conductorDoc, this);
    }
}
