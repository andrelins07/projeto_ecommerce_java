package ecommerce.database;

import java.util.ArrayList;

import ecommerce.model.usuario.Usuario;
import ecommerce.repository.UsuarioRepository;
import ecommerce.util.Arquivos;
import ecommerce.util.ManipularJson;

public class UsuariosDatabase implements UsuarioRepository{

	private static ArrayList<Usuario> usuarios = ManipularJson.carregarJson(Arquivos.USUARIOS, Usuario.class);;

	
	@Override
	public Usuario buscarUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visualizarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cadastrarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comprar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visualizarCompras() {
		// TODO Auto-generated method stub
		
	}

}
