package ecommerce.repository;

public interface UsuarioRepository {
	
	public void procurarUsuarioPorLogin();
	
	public void cadastrarUsuario( );
	
	public void atualizarUsuario();
	
	public void deletarUsuario();
	
	public void comprar();
	
	public void visualizarCompras();
}
