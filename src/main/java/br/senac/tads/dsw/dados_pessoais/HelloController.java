package br.senac.tads.dsw.dados_pessoais;

import tools.jackson.core.JacksonException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public Mensagem hello() {
		return new Mensagem("Felipe Rassati Stephano", "Olá, mundo! Meu primeiro endpoint Spring Boot");
	}

	@GetMapping(value = "/hello-manual", produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloManual() throws JacksonException {
		Mensagem mensagem = new Mensagem("Felipe Rassati Stephano", "JSON gerado manualmente com ObjectMapper");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(mensagem);
	}
}

