package com.paraizo.spring_boot_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Dados para atualização de um usuário existente")
public record UserUpdateDTO(

        @Schema(example = "Nome de Usuário Alterado")
        @Size(min = 3, max = 255)
        String nomeUsuario,

        @Schema(example = "USR0069")
        @Size(max = 20)
        String matriculaUsuario,

        @Schema(example = "1990-05-04")
        LocalDate dataNascimento,

        @Schema(example = "novo_email@email.com")
        @Email(message = "Formato de e-mail inválido")
        String email,

        UserTypeDTO type
) {}