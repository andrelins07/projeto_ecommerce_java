package ecommerce.model.usuario;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)

@JsonSubTypes({ 
	@JsonSubTypes.Type(value = Funcionario.class, name = "FUNCIONARIO"), 
	@JsonSubTypes.Type(value = Cliente.class, name = "CLIENTE")})

public abstract class Usuario {

	private String nome;
	private int idade;
	private String cpf;
	private String login;
	private int senha;
	private String role;

	public Usuario(String nome, int idade, String cpf, String login, int senha, String role) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.role = role;
	}
	
	public Usuario() {
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public void visualizar() {
		System.out.printf("Nome: %s | Idade: %d | Login: %s | CPF: %s | Tipo Usuario: %s\n", this.nome, this.idade, this.login, this.cpf,
				this.role);

	}
}
