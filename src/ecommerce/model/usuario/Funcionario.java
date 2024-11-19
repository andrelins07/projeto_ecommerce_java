package ecommerce.model.usuario;

public class Funcionario extends Usuario {
	
	private String cargo;
	private float salario;
	
	
	public Funcionario() {
		
	}
	
	public Funcionario(String nome, int idade, String cpf, String login, int senha, String cargo, float salario) {
		super(nome, idade, cpf, login, senha, Role.FUNCIONARIO.getValue());
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
	public void atualizar(String login, int senha, String cargo, float salario) {
		
		if(!getLogin().equals(login)) {
			setLogin(login);
		}
		if(getSenha() != senha) {
			setSenha(senha);
		}
		if(!this.cargo.equals(cargo)) {
			this.cargo = cargo;
		}
		if(this.salario != salario) {
			this.salario = salario;
		}
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

	@Override
	public String toString() {
		return "Nome = " + getNome() + " | Idade = " + getIdade() + " | Login = " + getLogin() + " | Senha = " + getSenha() + 
				"\nCargo: " + this.cargo + " | Salario: " + getSalario();
	}
	
}
