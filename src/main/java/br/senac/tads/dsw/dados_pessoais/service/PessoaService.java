package br.senac.tads.dsw.dados_pessoais.service;

import br.senac.tads.dsw.dados_pessoais.dto.PessoaAlteracaoDto;
import br.senac.tads.dsw.dados_pessoais.dto.PessoaDto;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

	List<PessoaDto> obterPessoas();

	PessoaDto incluirNovaPessoa(PessoaDto pessoa);

	Optional<PessoaDto> obterPessoa(String username);

	PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao);

	void removerPessoa(String username);

	List<PessoaDto> buscarPessoas(String nome);
}
