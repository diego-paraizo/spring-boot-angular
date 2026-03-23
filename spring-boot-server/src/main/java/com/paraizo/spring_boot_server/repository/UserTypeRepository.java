package com.paraizo.spring_boot_server.repository;

import com.paraizo.spring_boot_server.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByOrigem(String origem);
}
