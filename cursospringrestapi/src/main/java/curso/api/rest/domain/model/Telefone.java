package curso.api.rest.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@ToString
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "numero")
	private String numero;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	private Usuario usuario;

	
}
