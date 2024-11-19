module projeto_final_bloco_01 {
	
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	exports ecommerce.model.compra to com.fasterxml.jackson.databind;
	exports ecommerce.model.pagamento to com.fasterxml.jackson.databind;
	exports ecommerce.model.produto to com.fasterxml.jackson.databind;
	exports ecommerce.model.usuario to com.fasterxml.jackson.databind;
}