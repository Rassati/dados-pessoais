package br.senac.tads.dsw.dados_pessoais.entidade;

import jakarta.persistence.*;

@Entity
@Table( name = "tb_conhecimentos")
public class ConhecimentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false,unique = true,length = 100)
	private String nome;

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

	public ConhecimentoEntity() {
	}
}
