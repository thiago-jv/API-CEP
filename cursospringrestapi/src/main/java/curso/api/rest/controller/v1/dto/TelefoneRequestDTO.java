package curso.api.rest.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TelefoneRequestDTO {


	@ApiModelProperty(value = "numero", example = "92991919191", required = true)
	private String numero;
	
	@JsonIgnore
	private UsuarioRequestDTO usuario;
	
	public TelefoneRequestDTO() {
	}
	
}
