package ecommerce.model.pagamento;

public class PagamentoDebito extends Pagamento {

	private static final float DESCONTO_DEBITO = 0.10f;
	private static final String PAGAMENTO = "DEBITO";

	
	public PagamentoDebito(float valor) {
		super(valor, PAGAMENTO);
	}

	@Override
	public void processarPagamento() {
		
		float valorDesconto = this.getValor() * DESCONTO_DEBITO;
		this.setValor(this.getValor() * (1 - DESCONTO_DEBITO));

		System.out.println("Forma de pagamento: Debito\n" +  
		"Valor da compra: R$" + this.getValor()+ " | Desconto aplicado: R$" + valorDesconto);
	}
	
}
