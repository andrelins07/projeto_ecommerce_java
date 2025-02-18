package ecommerce.model.usuario;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
	
	CLIENTE("CLIENTE"),
	FUNCIONARIO("FUNCIONARIO");
	
	private String value;
	
	
	Role(String value) {
		this.value = value;
	}

	
	public String getValue() {
		return value;
	}	


	@JsonCreator
    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + value);
    }
}
