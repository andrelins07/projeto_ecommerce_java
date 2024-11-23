package ecommerce.repository;

import java.util.List;

import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.model.usuario.cliente.DadosAtualizacaoCliente;
import ecommerce.model.usuario.funcionario.DadosAtualizacaoFuncionario;
import ecommerce.model.usuario.funcionario.Funcionario;
import ecommerce.model.compra.Compra;

public interface UsuarioRepository {
	
	public Usuario buscarUsuario(String login, int senha);
	
	public List<Usuario> listarTodosUsuarios();
	
	public void cadastrarUsuario(Usuario usuario);
		
	public void deletarUsuario(Usuario usuario);
	
	public void efetivarCompra(Compra compra, Cliente cliente);	
	
	public void atualizarCliente(Cliente cliente, DadosAtualizacaoCliente dadosAtualizacao);
	
	public void atualizarFuncionario(Funcionario funcionario, DadosAtualizacaoFuncionario dadosAtualizacao);
}
