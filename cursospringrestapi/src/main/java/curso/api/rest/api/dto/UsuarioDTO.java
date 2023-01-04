package curso.api.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String nome;

}
