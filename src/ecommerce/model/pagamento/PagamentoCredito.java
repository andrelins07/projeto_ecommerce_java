package ecommerce.model.pagamento;

import ecommerce.exception.RegraDeNegocioException;

public class PagamentoCredito extends Pagamento {

	private float carteiraCliente;

	private static final String PAGAMENTO = "CREDITO";

	public PagamentoCredito(float valor, float carteiraCliente) {
		super(valor, PAGAMENTO);
		this.carteiraCliente = carteiraCliente;
	}

	@Override
	public void processarPagamento() {

		if (this.getValor() > carteiraCliente) {
			throw new RegraDeNegocioException("Saldo na carteira é insuficiente!\nValor na carteira: "
					+ carteiraCliente + " | Valor da compra: " + this.getValor());
		}
		System.out.println("Forma de pagamento: Crédito\n" +  "Valor da compra: R$ " + getValor());
	}

}
