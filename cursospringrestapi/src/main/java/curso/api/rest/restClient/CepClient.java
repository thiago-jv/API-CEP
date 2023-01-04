package curso.api.rest.restClient;

import com.google.gson.Gson;
import curso.api.rest.model.Usuario;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Component
public class CepClient {

    public Usuario buscaCep(Usuario usuario) throws IOException {
        URL url = new URL("https://viacep.com.br/ws/"+usuario.getCep()+"/json/"); // temos a URL
        URLConnection connection = url.openConnection(); // abrimos a conexao
        InputStream is = connection.getInputStream();  // trazendo os dados
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); // obtendo os dados

        String cep = "";
        StringBuilder jsonCep = new StringBuilder();

        while(( cep = br.readLine()) != null) { // tem linha? então coloca o valor na variável CEP
            jsonCep.append(cep);
        }
        Usuario userAux = new Gson().fromJson(jsonCep.toString(), Usuario.class); // irá pegar todos os campos e converter

        usuario.setLogradouro(userAux.getLogradouro());
        usuario.setComplemento(userAux.getComplemento());
        usuario.setBairro(userAux.getBairro());
        usuario.setLocalidade(userAux.getLocalidade());
        usuario.setUf(userAux.getUf());

        return usuario;

    }
}
