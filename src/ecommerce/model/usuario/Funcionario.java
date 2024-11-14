package ecommerce.model.usuario;

import java.time.LocalDate;

public class Funcionario extends Usuario {
	
	private String cargo;
	private float salario;
	private final String ROLE = "funcionario";
	
	
	public Funcionario(String nome, LocalDate dataNascimento, String login, int senha, String cargo, float salario) {
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
	
	
	@Override
	public void visualizar() {
		System.out.printf("""
				\n****************************************************************
				Funcionario:
				****************************************************************\n""");
		super.visualizar();
		System.out.printf("Cargo: %s | Salario: %.2f\n\n", this.cargo, this.salario);
	}

}
