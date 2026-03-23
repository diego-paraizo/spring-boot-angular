package com.paraizo.spring_boot_server.service;

import com.paraizo.spring_boot_server.dto.UserRequestDTO;
import com.paraizo.spring_boot_server.dto.UserResponseDTO;
import com.paraizo.spring_boot_server.dto.UserUpdateDTO;
import com.paraizo.spring_boot_server.exception.UserNotFoundException;
import com.paraizo.spring_boot_server.model.User;
import com.paraizo.spring_boot_server.model.UserType;
import com.paraizo.spring_boot_server.repository.UserRepository;
import com.paraizo.spring_boot_server.repository.UserTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public UserService(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Transactional
    public UserResponseDTO createUserFromDto(UserRequestDTO dto) {
        UserType tipoEncontrado = userTypeRepository.findByOrigem(dto.type().origem())
                .orElseThrow(() -> new RuntimeException("Origem '" + dto.type().origem() + "' não cadastrada no banco!"));

        User user = new User();
        user.setNomeUsuario(dto.nomeUsuario());
        user.setMatriculaUsuario(dto.matriculaUsuario());
        user.setDataNascimento(dto.dataNascimento());
        user.setEmail(dto.email());
        user.setType(tipoEncontrado);

        User createdUser = userRepository.save(user);
        return toResponseDTO(createdUser);
    }

    @Transactional
    public UserResponseDTO updateFromDto(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.nomeUsuario() != null) user.setNomeUsuario(dto.nomeUsuario());
        if (dto.matriculaUsuario() != null) user.setMatriculaUsuario(dto.matriculaUsuario());
        if (dto.dataNascimento() != null) user.setDataNascimento(dto.dataNascimento());
        if (dto.email() != null) user.setEmail(dto.email());

        if (dto.type().origem() != null) {
            UserType novoTipo = userTypeRepository.findByOrigem(dto.type().origem())
                    .orElseThrow(() -> new RuntimeException("Nova origem inválida"));
            user.setType(novoTipo);
        }

        User updatedUser = userRepository.save(user);
        return toResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }


    // === CONSULTAS VIA JPA (JAVA) ===

    public UserResponseDTO findByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return toResponseDTO(user);
    }

    public List<UserResponseDTO> findByUsersAll() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // === CONSULTAS VIA PROCEDURE (BANCO) ===

    public List<UserResponseDTO> buscarPorProcedure(String origem) {
        List<User> resultado = userRepository.buscarOrigemPorProcedure(origem);
        return resultado.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<UserResponseDTO> buscarTodosViaProcedure() {
        List<User> resultado = userRepository.buscarUsuariosPorProcedure();
        return resultado.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // === MÉTODO CONVERSOR (O TRADUTOR) ===

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNomeUsuario(),
                user.getMatriculaUsuario(),
                user.getDataNascimento(),
                user.getEmail(),
                user.getType() != null ? user.getType().getDescricao() : "N/A",
                user.getType() != null ? user.getType().getOrigem() : null
        );
    }
}
