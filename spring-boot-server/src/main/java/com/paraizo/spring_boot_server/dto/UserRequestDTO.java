package com.paraizo.spring_boot_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Dados para criação de um novo usuário")
public record UserRequestDTO(

        @Schema(example = "Nome Usuário")
        @NotBlank(message = "O nome é obrigatório")
        String nomeUsuario,

        @Schema(example = "USR0069")
        @NotBlank(message = "A matrícula é obrigatória")
        @Size(max = 20)
        String matriculaUsuario,

        @Schema(example = "1990-05-04")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @Schema(example = "usuario@email.com")
        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatória")
        String email,

        @NotNull(message = "A origem é obrigatória")
        UserTypeDTO type
) {}