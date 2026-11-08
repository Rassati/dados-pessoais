package br.senac.tads.dsw.dados_pessoais.dto;

public class LoginDto {
	private String username;
	private String senha;

	public LoginDto() {}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
