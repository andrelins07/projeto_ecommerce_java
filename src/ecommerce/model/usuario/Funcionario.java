package ecommerce.model.usuario;

import java.time.LocalDate;

public class Funcionario extends Usuario {
	
	private String cargo;
	private float salario;
	private final String ROLE = "funcionario";
	
	
	public Funcionario(String nome, LocalDate dataNascimento, String login, String senha, String cargo, float salario) {
		super(nome, dataNascimento, login, senha);
		this.cargo = cargo;
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public float getSalario() {
		return salario;
	}


	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getROLE() {
		return ROLE;
	}
	

}
