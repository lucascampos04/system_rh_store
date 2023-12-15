package com.rh.rhze.controller.service;

import com.rh.rhze.model.dto.FuncionariosDto;
import com.rh.rhze.model.entity.FuncionariosEntity;
import com.rh.rhze.model.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionariosServices {
    @Autowired
    FuncionariosRepository funcionariosRepository;

    public List<FuncionariosDto> listAllFuncionarios(){
        try {
            return funcionariosRepository.findAll().stream()
                    .map(FuncionariosDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new RuntimeException("Erro ao exibir todos os usuarios");
        }
    }

}
