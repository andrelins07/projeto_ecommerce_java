package ecommerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.compra.Compra;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.util.Arquivos;
import ecommerce.util.ManipularJson;

public class CompraService {

	private static final ArrayList<Compra> compras = ManipularJson.carregarJson(Arquivos.COMPRAS, Compra.class);

	public CompraService() {
		Compra.setTotalCompras(compras.size() + 1);
	}

	public void registrarCompra(Compra compra) {

		compras.add(compra);
	}
	
	public ArrayList<Compra> carregarComprasCliente(Cliente cliente) {

		ArrayList<Compra> comprasCliente = new ArrayList<Compra>();

		compras.forEach(compra -> {

			if (compra.getCpfCliente().equals(cliente.getCpf())) {
				comprasCliente.add(compra);
			}
		});

		Compra.setTotalCompras(compras.size() + 1);

		return comprasCliente;
	}
	
	public List<Compra> listarComprasCliente(Cliente cliente) {
		
		if (cliente.visualizarCompras().isEmpty()) {
			throw new RegraDeNegocioException("Nenhuma compra realizada!");
		}
		
		return Collections.unmodifiableList(cliente.getHistoricoCompra());
	}

}
