package curso.api.rest.controller.v1;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import curso.api.rest.controller.v1.dto.UsuarioRequestDTO;
import curso.api.rest.restClient.CepClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.dto.UsuarioDTO;
import curso.api.rest.repository.UsuarioRepository;


@RestController
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CepClient cepClient;

	@GetMapping(value = "/{id}/codigovenda/{venda}")
	public ResponseEntity<Usuario> relatorio(
			                                 @PathVariable(value = "id") Long id,
	                                         @PathVariable(value = "venda") Long venda) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> init(@PathVariable(value = "id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		ModelMapper mapper = new ModelMapper();
		return ResponseEntity.ok(mapper.map(usuario, UsuarioDTO.class));
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Usuario>> usuario() {
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();	
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRequestDTO usuario) throws IOException {
		for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao++) {
			usuario.getTelefones().get(posicao).setUsuario(usuario);
		}
		ModelMapper mapper = new ModelMapper();
		Usuario usuarioSalvo = usuarioRepository.save(cepClient.buscaCep(mapper.map(usuario, Usuario.class)));
		return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
	}

	@PutMapping(value = "/")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) throws IOException {
		for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao ++) {
			usuario.getTelefones().get(posicao).setUsuario(usuario);
		}
		Usuario usuarioSalvo = usuarioRepository.save(cepClient.buscaCep(usuario));
		return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}")
	public ResponseEntity<Usuario> atualizarvenda(
			@PathVariable(value = "iduser") Long iduser,
            @PathVariable(value = "idvenda") Long idvenda) {		
		return new ResponseEntity("Venda atualizada", HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable(value = "id") Long id) {
		usuarioRepository.deleteById(id);
		return "OK";
	}
	
}
