package com.paraizo.spring_boot_server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criação de um novo usuário com o tipo usuário")
public record UserTypeDTO(

        @Schema(description = "Sigla da origem (M, F, E...)", example = "E")
        @NotNull(message = "A origem é obrigatória")
        String origem

) {}
