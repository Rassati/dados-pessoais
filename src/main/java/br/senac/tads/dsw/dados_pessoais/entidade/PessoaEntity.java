package br.senac.tads.dsw.dados_pessoais.entidade;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_pessoas")
public class PessoaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false,unique = true,length = 64)
	private String username;

	@Column(nullable = false,length = 100)
	private String nome;

	@Column(nullable = false,length = 100)
	private String email;

	@Column(length = 20)
	private String telefone;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(length = 255)
	private String senha;

	@ManyToMany(
		cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		fetch = FetchType.EAGER
	)
	@JoinTable(
		name = "tb_pessoas_conhecimentos",
		joinColumns = @JoinColumn(name = "pessoa_id"),
		inverseJoinColumns = @JoinColumn(name = "conhecimento_id")
	)
	private Set<ConhecimentoEntity> conhecimentos = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<ConhecimentoEntity> getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(Set<ConhecimentoEntity> conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

	public PessoaEntity() {
	}
}
