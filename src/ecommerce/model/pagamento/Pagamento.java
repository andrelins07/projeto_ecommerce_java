package ecommerce.model.pagamento;

public abstract class Pagamento {
    
	private float valor;
	private String formaPagamento;

    public Pagamento(float valor, String pagamento) {
        this.valor = valor;
        this.formaPagamento = pagamento;
    }

    public float getValor() {
        return valor;
    }
    
    public void setValor(float valor) {
    	this.valor = valor;
    }
    
    public String getPagamento() {
    	return formaPagamento;
    }

    public abstract void processarPagamento();
}

