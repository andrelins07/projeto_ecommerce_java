package ecommerce.model.pagamento;

import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.Cliente;

public class PagamentoCredito extends Pagamento {

	private Cliente cliente;

	private static final String PAGAMENTO = "CREDITO";

	public PagamentoCredito(float valor, Cliente cliente) {
		super(valor, PAGAMENTO);
		this.cliente = cliente;
	}

	@Override
	public void processarPagamento() {

		if (this.getValor() > cliente.getCreditoCarteira()) {
			throw new RegraDeNegocioException("Saldo na carteira é insuficiente!");
		}
		System.out.println("Forma de pagamento: Crédito\n" +  "Valor da compra: R$ " + getValor());
	}

}
