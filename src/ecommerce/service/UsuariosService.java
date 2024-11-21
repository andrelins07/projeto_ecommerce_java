package ecommerce.service;

import java.util.ArrayList;

import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.usuario.Cliente;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.util.Arquivos;
import ecommerce.util.DadosEcommerce;
import ecommerce.util.ManipularJson;

public class UsuariosService {

	private static ArrayList<Usuario> usuarios = ManipularJson.carregarJson(Arquivos.USUARIOS, Usuario.class);;
	private Usuario usuario;
	
	
	public Usuario buscarUsuario(String login, int senha) {
		
		for (Usuario user : usuarios) {
			if (user.getLogin().equals(login) && user.getSenha() == senha) {
				this.usuario = user;
				break;
			}
		}
		if(usuario == null)
			throw new RegraDeNegocioException("Usuario não encontrado. Verifique seu login e senha");
		
		
		if(usuario.getRole().equals(Role.CLIENTE.getValue())) {
			
			Cliente cliente = (Cliente) usuario;
			//Puxar compras do Compra Service
			cliente.setHistoricoCompra(DadosEcommerce.carregarComprasCliente(usuario));
			
			usuario = cliente;
		}
		return usuario;
	
	}
	
	public void cadastrarUsuario(Usuario usuario) {
		
		if(usuarios.contains(usuario))
			throw new RegraDeNegocioException("Usuario já cadastrado");
		
		usuarios.add(usuario);		
	}
	
	public void atualizarUsuario(Usuario usuario) {
		
	
	}
	
	public void deletarUsuario() {
		// TODO Auto-generated method stub
		
	}

	
	public void comprar() {
		// TODO Auto-generated method stub
		
	}

	
	public void visualizarCompras() {
		// TODO Auto-generated method stub
		
	}

}
