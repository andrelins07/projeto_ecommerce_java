package ecommerce.controller;

import java.time.LocalDate;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.pagamento.*;
import ecommerce.model.produto.Produto;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.model.usuario.cliente.DadosAtualizacaoCliente;
import ecommerce.model.usuario.funcionario.DadosAtualizacaoFuncionario;
import ecommerce.model.usuario.funcionario.Funcionario;
import ecommerce.model.compra.Compra;
import ecommerce.service.CompraService;
import ecommerce.service.ProdutoService;
import ecommerce.service.UsuarioService;
import ecommerce.util.*;

public class UsuarioController {

	private UsuarioService usuarioService = new UsuarioService();
	private ProdutoService produtoService = new ProdutoService();
	private CompraService compraService = new CompraService();
	private Usuario usuario;

	public UsuarioController(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void visualizarUsuario() {
		usuario.visualizar();
	}
	
	public void atualizarUsuario() {

		usuario.visualizar();
		
		if (usuario.getRole().equals(Role.CLIENTE.getValue())) {
			atualizarCliente((Cliente) usuario);
		} else {
			atualizarFuncionario((Funcionario) usuario);
		}
		
		usuario.visualizar();

		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
	}

	public void deletarUsuario() {

		usuario.visualizar();

		System.out.println(Cores.TEXT_RED + "\nTem certeza que deseja excluir o usuario?\n" + Cores.TEXT_RESET);

		int opcao = Leitura.lerInteiro("Digite '1 - SIM' ou '2 - NAO': ");

		switch (opcao) {
		case 1 -> {
			usuarioService.deletarUsuario(usuario);
			System.out.println(Cores.TEXT_GREEN + "\nUsuario excluido com sucesso!");
		}
		case 2 -> System.out.println("Operacao cancelada!");

		default -> System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
		}
	}

	public void comprar() {

		if (!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}
		
		Produto produtoEscolhido = escolherProduto();
		
		int quantidade = Leitura.lerInteiro("Digite a QUANTIDADE desejada: ");
		
		produtoService.validarEstoque(produtoEscolhido, quantidade);
			
		pagar((Cliente) usuario, produtoEscolhido, quantidade);
		

		produtoEscolhido.setEstoque(quantidade * -1);

		System.out.println(Cores.TEXT_GREEN + "Compra realizada com sucesso!");
	}

	public Produto escolherProduto() {

		String nomeProduto = Leitura.lerString("Digite o NOME do produto: ");

		List<Produto> produtosFiltrados = produtoService.filtrarProdutos(nomeProduto);
		
		produtosFiltrados.forEach(produto->produto.visualizarProduto());

		int codigo = Leitura.lerInteiro("Digite o CÓDIGO do produto desejado: ");
	
		Produto produtoEscolhido = produtoService.selecionarProdutoDoFiltro(codigo);

		if (produtoEscolhido.isRestricaoIdade() && usuario.getIdade() < 18) {
			throw new RegraDeNegocioException("Esse produto é para maiores de 18!");
		}

		return produtoEscolhido;
	}

	public void visualizarCompras() {

		if (!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}
	
		compraService.listarComprasCliente((Cliente) usuario).forEach(System.out::println);
	}
	
	public void imprimirUsuarios() {

		if (!usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para funcionarios!");
		}
		usuarioService.listarTodosUsuarios().forEach(user -> user.visualizar());
	}

	private void atualizarCliente(Cliente cliente) {

		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String endereco = Leitura.lerString("Alterando o endereco: ");
		
		DadosAtualizacaoCliente dadosAtualizados = new DadosAtualizacaoCliente(login, senha, endereco);

		usuarioService.atualizarCliente(cliente, dadosAtualizados);	
	}

	private void atualizarFuncionario(Funcionario funcionario) {

		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String cargo = Leitura.lerString("Alterando cargo: ");
		float salario = Leitura.lerFloat("Alterando salario: ");
		
		DadosAtualizacaoFuncionario dadosAtualizados = new DadosAtualizacaoFuncionario(login, senha, cargo, salario);
		
		usuarioService.atualizarFuncionario(funcionario, dadosAtualizados);
	}

	public void adicionarSaldoCarteira() {

		if (!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}

		Cliente cliente = (Cliente) usuario;

		System.out.println("Saldo atual: " + cliente.getCreditoCarteira());

		cliente.adicionarSaldoCarteira(Leitura.lerFloat("Digite o valor que deseja adicionar: "));

		System.out.println(Cores.TEXT_GREEN + "Saldo adicionado com sucesso!" + Cores.TEXT_RESET);

		System.out.println("Novo Saldo: " + cliente.getCreditoCarteira());
	}

	public void pagar(Cliente cliente, Produto produto, int quantidade) {

		int formaPagamento = Leitura.lerInteiro("Qual a forma de pagamento ?\n1 - Credito | 2 - Débito");

		Pagamento pagamento;
		
		float precoTotal = produto.getPreco() * quantidade;
		
		switch (formaPagamento) {

		case 1 -> pagamento = new PagamentoCredito(precoTotal, cliente.getCreditoCarteira());
		case 2 -> pagamento = new PagamentoDebito(precoTotal);
		default -> throw new RegraDeNegocioException("Opcao invalida!");
		}

		pagamento.processarPagamento();
		
		Compra novaCompra = new Compra(produto.getNome(), pagamento.getValor(), quantidade, LocalDate.now(), pagamento.getPagamento(), cliente.getCpf());

		usuarioService.efetivarCompra(novaCompra, cliente);
		compraService.registrarCompra(novaCompra);	
	}
}
