package curso.api.rest.controller.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioRequestPutDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id", example = "1", required = true)
	private Long id;

	@ApiModelProperty(value = "login", example = "thiagohenrique", required = true)
	private String login;

	@ApiModelProperty(value = "senha", example = "1234", required = true)
	private String senha;

	@ApiModelProperty(value = "nome", example = "thiago", required = true)
	private String nome;

	@ApiModelProperty(value = "cep", example = "69093118", required = true)
	private String cep;

	@ApiModelProperty(value = "logradouro", example = "Rua Guarupava", required = true)
	private String logradouro;

	@ApiModelProperty(value = "bairro", example = "Monte das orquideas", required = true)
	private String bairro;

	@ApiModelProperty(value = "localidade", example = "Manaus", required = true)
	private String localidade;

	@ApiModelProperty(value = "uf", example = "AM", required = true)
	private String uf;

	@ApiModelProperty(value = "complemento", example = ".....", required = true)
	private String complemento;

	private List<TelefoneRequestPutDTO> telefones;

}
