package com.paraizo.spring_boot_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = User.TABLE_NAME)
@Schema(description = "Entidade que representa um Usuário no sistema")
public class User {

    public static final String TABLE_NAME = "USUARIO";
    private static final String COLUMN_ID = "ID_USU";
    private static final String COLUMN_NAME = "NOME_USU";
    private static final String COLUMN_REGISTRATION_NUMBER = "MATR_USU";
    private static final String COLUMN_BIRTH_DATE = "DATA_NASC";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_ORIGIN = "ORIGEM";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false, unique = true)
    @Schema(description = "Identificador único do usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = COLUMN_NAME, nullable = false, unique = true, length = 255)
    @Schema(description = "Nome completo do usuário", example = "Diego Paraizo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nomeUsuario;

    @Column(name = COLUMN_REGISTRATION_NUMBER, nullable = false, length = 20)
    @Schema(description = "Matrícula funcional ou registro do usuário", example = "DFP12345")
    private String matriculaUsuario;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = COLUMN_BIRTH_DATE, nullable = false, length = 10)
    @Schema(description = "Data de nascimento no formato ISO", example = "1990-05-04")
    private LocalDate dataNascimento;

    @Column(name = COLUMN_EMAIL, nullable = false, length = 255)
    @Schema(description = "E-mail de contato", example = "diego@email.com")
    private String email;

    @ManyToOne
    @JoinColumn(name = COLUMN_ORIGIN, referencedColumnName = COLUMN_ORIGIN)
    @Schema(description = "Tipo de vínculo/origem do usuário")
    private UserType type;

    public User(String nomeUsuario, String matriculaUsuario, LocalDate dataNascimento, String email, String origem, UserType type) {
        this.nomeUsuario = nomeUsuario;
        this.matriculaUsuario = matriculaUsuario;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.type = type;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(String matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
