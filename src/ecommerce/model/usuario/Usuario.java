package ecommerce.model.usuario;

import java.time.LocalDate;

public abstract class Usuario {

	private String nome;
	private LocalDate dataNascimento;
	private String login;
	private int senha;


	public Usuario(String nome, LocalDate dataNascimento, String login, int senha) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public void visualizar() {

		System.out.printf("""
				Nome: %s | Data de Nascimento: %s | Login: %s\n""", this.nome, this.dataNascimento.toString(), this.login);
	}
}
