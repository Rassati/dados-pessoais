package br.senac.tads.dsw.dados_pessoais;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PessoaService {
	private AtomicInteger contador = new AtomicInteger(0);
	private Map<String, Pessoa> mapPessoas = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		mapPessoas.put("fulano", new Pessoa(contador.incrementAndGet(), "fulano", "Fulano da Silva", "fulano@email.com", "(11) 99999-1234", LocalDate.parse("2000-10-20")));
		mapPessoas.put("ciclano", new Pessoa(contador.incrementAndGet(), "ciclano", "Ciclano de Souza", "ciclano@email.com", "(11) 98888-1234", LocalDate.parse("1999-03-28")));
		mapPessoas.put("beltrana", new Pessoa(contador.incrementAndGet(), "beltrana", "Beltrana Luz", "beltrana@email.com", "(11) 97777-1234", LocalDate.parse("2001-09-16")));

	}

	public List<Pessoa> obterPessoas() {
		return new ArrayList<>(mapPessoas.values());

	}

	public Pessoa incluirNovaPessoa(Pessoa pessoa) {
		pessoa.setId(contador.incrementAndGet());
		mapPessoas.put(pessoa.getUsername(), pessoa);
		return pessoa;
	}

	public Optional<Pessoa> obterPessoa (String username) {
		return Optional.ofNullable(mapPessoas.get(username));
	}
}
