package com.rh.rhze.model.repository;

import com.rh.rhze.model.entity.FuncionariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionariosRepository extends JpaRepository<FuncionariosEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByTelephone(String telephone);
}
