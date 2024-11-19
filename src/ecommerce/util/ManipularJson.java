package ecommerce.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ecommerce.exception.RegraDeNegocioException;

public class ManipularJson {

	public static <T>ArrayList<T> carregarJson(String caminho, Class<T> T) {

		ObjectMapper mapper = criarObjectMapper();

		File file = new File(caminho);

		List<T> produtosDto = new ArrayList<T>();

		try {
			produtosDto = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, T));
		} catch (IOException e) {
			throw new RegraDeNegocioException("Não foi possível converter o JSON para o objetivo " + T.getName()); 
		}
		
		ArrayList<T> produtos = new ArrayList<>(produtosDto);

		return produtos;
	}

	public static <T> void salvarAtualizacoes(String caminho, List<T> lista) {

		ObjectMapper mapper = criarObjectMapper();

		File file = new File(caminho);

		try {
			mapper.writeValue(file, lista);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static ObjectMapper criarObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		return mapper;
	}
}
