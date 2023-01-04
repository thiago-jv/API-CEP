package curso.api.rest.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TelefoneRequestPostDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "numero", example = "92991919191", required = true)
	private String numero;
	
	@JsonIgnore
	private UsuarioRequestPostDTO usuario;

	public TelefoneRequestPostDTO(String numero, UsuarioRequestPostDTO usuario) {
		this.numero = numero;
		this.usuario = usuario;
	}
}
