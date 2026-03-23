package com.paraizo.spring_boot_server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = UserType.TABLE_NAME)
@Schema(description = "Entidade que define o perfil ou origem do usuário (Magistrado, Externo, etc.)")
public class UserType {

    public static final String TABLE_NAME = "TIPO_USUARIO";
    private static final String COLUMN_ID = "ID_TIPOUSUARIO";
    private static final String COLUMN_ORIGIN = "ORIGEM";
    private static final String COLUMN_DESCRIPTION = "DESCR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false, unique = true)
    @Schema(description = "ID interno do tipo de usuário", example = "7", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = COLUMN_ORIGIN, nullable = false, unique = true, length = 1)
    @Schema(description = "Caractere identificador da origem", example = "E", allowableValues = {"M", "F", "T", "A", "P", "C", "E"})
    private String origem;

    @Column(name = COLUMN_DESCRIPTION, nullable = false, length = 255)
    @Schema(description = "Descrição detalhada do tipo de vínculo", example = "Externo")
    private String descricao;

    public UserType() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
