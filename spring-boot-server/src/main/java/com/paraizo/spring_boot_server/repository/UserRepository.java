package com.paraizo.spring_boot_server.repository;

import com.paraizo.spring_boot_server.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE User u SET u.nomeUsuario = :#{#user.nomeUsuario}, " +
            "u.matriculaUsuario = :#{#user.matriculaUsuario}, " +
            "u.dataNascimento = :#{#user.dataNascimento}, " +
            "u.email = :#{#user.email}, " +
            "u.type = :#{#user.type} " +
            " WHERE u.id = :id")
    void updateById(@Param("id") Long id, @Param("user") User user);

    void deleteById(Long id);

    @Query(value = "SELECT * FROM BUSCAR_USUARIOS(:origem)", nativeQuery = true)
    List<User> buscarOrigemPorProcedure(String origem);

    @Query(value = "SELECT * FROM LISTAR_USUARIOS()", nativeQuery = true)
    List<User> buscarUsuariosPorProcedure();

}
