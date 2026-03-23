package com.paraizo.spring_boot_server.exception;

public class UserNotFoundException extends UserException {
    private final Long id;

    public UserNotFoundException(Long id) {
        super(null);
        this.id = id;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("Não foi possível encontrar um usuário com o ID fornecido. [")
                .append(id)
                .append("]!");
        return sb.toString();
    }
}
