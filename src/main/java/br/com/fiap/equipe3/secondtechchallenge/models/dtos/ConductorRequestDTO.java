package br.com.fiap.equipe3.secondtechchallenge.models.dtos;

import br.com.fiap.equipe3.secondtechchallenge.models.documents.Conductor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

@Data
public class ConductorRequestDTO {

    @NotBlank(message = "Name must be defined.")
    private String name;

    @CPF(message = "CPF must be valid (example of CPF: 123.456.789-09).")
    private String cpf;

    @NotNull(message = "E-mail must not be null (example of e-mail: john.doe@email.com).")
    @Email(message = "E-mail must be valid(example of e-mail: john.doe@email.com).")
    private String email;

    @NotNull(message = "Cellphone number must not be null (example of cellphone: 11943214321).")
    @Length(min = 11, max = 11) // (11) 9 4321-9876 -> 11943219876
    private String cellphone;

    private String[] vehicles;

    public Conductor toDocument() {
        Conductor doc = new Conductor();
        BeanUtils.copyProperties(this, doc);
        return doc;
    }
}
