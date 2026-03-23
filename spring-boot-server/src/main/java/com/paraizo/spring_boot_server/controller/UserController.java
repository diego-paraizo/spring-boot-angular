package com.paraizo.spring_boot_server.controller;

import com.paraizo.spring_boot_server.dto.UserRequestDTO;
import com.paraizo.spring_boot_server.dto.UserResponseDTO;
import com.paraizo.spring_boot_server.dto.UserUpdateDTO;
import com.paraizo.spring_boot_server.model.User;

import com.paraizo.spring_boot_server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
@Tag(name = "Usuários", description = "API para gestão de usuários e integração com Procedures")
public class UserController {

    public static final String BASE_URL = "/api/users";

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cria um novo usuário via DTO")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO dto) {
        return userService.createUserFromDto(dto);
    }

    @GetMapping(value = "/{id}", headers = {HttpHeaders.CONTENT_TYPE})
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Busca usuário por ID", description = "Retorna os detalhes de um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não localizado")
    })
    public ResponseEntity<UserResponseDTO> findByUserId(@PathVariable Long id) {
        UserResponseDTO response = userService.findByUser(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista contendo todos os usuários cadastrados.")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> lista = userService.findByUsersAll();
        return ResponseEntity.ok(lista);
    }

    @Transactional
    @Operation(summary = "Atualiza parcialmente um usuário", description = "Atualiza os campos permitidos de um usuário baseado no ID.")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        UserResponseDTO updatedUser = userService.updateFromDto(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Exclui um usuário",
            description = "Remove o registro do usuário permanentemente do banco de dados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUserId(@Parameter(description = "ID do usuário a ser removido") @PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Grupo Procedures
    @Operation(summary = "Listar por Origem (Procedure)", description = "Busca todos usando a procedure BUSCAR_USUARIOS")
    @GetMapping("/procedure/origem")
    public ResponseEntity<List<UserResponseDTO>> getPorProcedure(@RequestParam String origem) {
        return ResponseEntity.ok(userService.buscarPorProcedure(origem));
    }

    @Operation(summary = "Listar Todos (Procedure)", description = "Busca todos usando a procedure LISTAR_USUARIOS")
    @GetMapping("/procedure/todos")
    public ResponseEntity<List<UserResponseDTO>> buscarTodosProcedure() {
        return ResponseEntity.ok(userService.buscarTodosViaProcedure());
    }

}
