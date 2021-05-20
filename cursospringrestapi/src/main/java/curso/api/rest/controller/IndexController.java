package curso.api.rest.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

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

import com.google.gson.Gson;

import curso.api.rest.model.Usuario;
import curso.api.rest.model.UsuarioDTO;
import curso.api.rest.repository.UsuarioRepository;


@RestController
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}/codigovenda/{venda}")
	public ResponseEntity<Usuario> relatorio(
			                                 @PathVariable(value = "id") Long id,
	                                         @PathVariable(value = "venda") Long venda) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> init(@PathVariable(value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
	
		return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario.get()), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Usuario>> usuario() {
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();	
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) throws Exception {
		
		for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao ++) {
			usuario.getTelefones().get(posicao).setUsuario(usuario);
		}
		
		// Consumindo API publica externa
		
		URL url = new URL("https://viacep.com.br/ws/"+usuario.getCep()+"/json/"); // temos a URL
		URLConnection connection = url.openConnection(); // abrimos a conexao
		InputStream is = connection.getInputStream();  // trazendo os dados
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8")); // obtendo os dados 
		
		String cep = "";
		StringBuilder jsonCep = new StringBuilder(); 
		
		while(( cep = br.readLine()) != null) { // tem linha? então coloca o valor na variável CEP
			jsonCep.append(cep);
		}
		
		System.out.println(jsonCep.toString());
		
		Usuario userAux = new Gson().fromJson(jsonCep.toString(), Usuario.class); // irá pegar todos os campos e converter
		
		// seta os dados no usuário
		usuario.setLogradouro(userAux.getLogradouro());
		usuario.setComplemento(userAux.getComplemento());
		usuario.setBairro(userAux.getBairro());
		usuario.setLocalidade(userAux.getLocalidade());
		usuario.setUf(userAux.getUf());
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);	
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/vendausuario")
	public ResponseEntity<Usuario> cadastrarvenda(@RequestBody Usuario usuario) {	
		Usuario usuarioSalvo = usuarioRepository.save(usuario);	
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}

	@PutMapping(value = "/")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		
		for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao ++) {
			usuario.getTelefones().get(posicao).setUsuario(usuario);
		}
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);	
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
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
