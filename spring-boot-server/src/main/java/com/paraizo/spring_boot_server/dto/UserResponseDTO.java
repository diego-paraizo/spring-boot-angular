package com.paraizo.spring_boot_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Dados de retorno do usuário")
public record UserResponseDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Nome Usuário")
        String nomeUsuario,

        @Schema(example = "USR0069")
        String matriculaUsuario,

        @Schema(example = "1990-05-04")
        LocalDate dataNascimento,

        @Schema(example = "usuario@email.com")
        String email,

        @Schema(description = "Descrição amigável da origem", example = "Externo")
        String origemDescricao,

        @Schema(description = "Sigla da origem", example = "E")
        String origemCodigo
) {}