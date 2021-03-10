package br.com.project.springionic.dto;

import br.com.project.springionic.validations.ClienteUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ClienteUpdate
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatoria")
    @Length(min = 5, max = 170, message = "Tamanho entre 5 e 80")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatoria")
    @Email(message = "Email obrigatoria")
    private String email;
}
