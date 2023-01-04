package curso.api.rest.controller.v1;

import java.io.IOException;
import java.util.List;

import curso.api.rest.controller.v1.dto.UsuarioRequestPostDTO;
import curso.api.rest.controller.v1.dto.UsuarioRequestPutDTO;
import curso.api.rest.controller.v1.dto.UsuarioResponseDTO;
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
import curso.api.rest.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CepClient cepClient;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> init(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioRepository.getOne(id);
        ModelMapper mapper = new ModelMapper();
        return ResponseEntity.ok(mapper.map(usuario, UsuarioResponseDTO.class));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Usuario>> usuario() {
        List<Usuario> list = usuarioRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestPostDTO usuario) throws IOException {
        for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao++) {
            usuario.getTelefones().get(posicao).setUsuario(usuario);
        }
        ModelMapper mapper = new ModelMapper();
        Usuario usuarioSalvo = usuarioRepository.save(cepClient.buscaCep(mapper.map(usuario, Usuario.class)));
        return new ResponseEntity<>(mapper.map(usuarioSalvo, UsuarioResponseDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody UsuarioRequestPutDTO usuario) throws IOException {
        for (int posicao = 0; posicao < usuario.getTelefones().size(); posicao++) {
            usuario.getTelefones().get(posicao).setUsuario(usuario);
        }
        ModelMapper mapper = new ModelMapper();
        Usuario usuarioSalvo = usuarioRepository.save(cepClient.buscaCep(mapper.map(usuario, Usuario.class)));
        return new ResponseEntity<>(mapper.map(usuarioSalvo, UsuarioResponseDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String delete(@PathVariable(value = "id") Long id) {
        usuarioRepository.deleteById(id);
        return "OK";
    }

}
