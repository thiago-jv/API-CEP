package curso.api.rest.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class UsuarioRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "login", example = "thiagohenrique", required = true)
	private String login;

	@ApiModelProperty(value = "senha", example = "1234", required = true)
	private String senha;

	@ApiModelProperty(value = "nome", example = "thiago", required = true)
	private String nome;

	@ApiModelProperty(value = "cep", example = "69093118", required = true)
	private String cep;

	private List<TelefoneRequestDTO> telefones;

}
