package curso.api.rest.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TelefoneResponseDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	private String numero;
	@JsonIgnore
	private UsuarioResponseDTO usuario;
	
}
