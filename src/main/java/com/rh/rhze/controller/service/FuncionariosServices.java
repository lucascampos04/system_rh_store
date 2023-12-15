package com.rh.rhze.controller.service;

import com.rh.rhze.model.dto.FuncionariosDto;
import com.rh.rhze.model.entity.FuncionariosEntity;
import com.rh.rhze.model.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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
    public FuncionariosDto insertFuncionario(FuncionariosDto funcionariosDto){
        try {
            FuncionariosEntity funcionariosEntity = new FuncionariosEntity();
            funcionariosEntity.setName(funcionariosDto.getName());
            funcionariosEntity.setOffice(funcionariosDto.getOffice());
            funcionariosEntity.setSalary(funcionariosDto.getSalary());
            funcionariosEntity.setCpf(funcionariosDto.getCpf());
            funcionariosEntity.setEmail(funcionariosDto.getEmail());
            funcionariosEntity.setTelephone(funcionariosDto.getTelephone());

            if ("caixa".equals(funcionariosDto.getOffice())){
                funcionariosEntity.setHoursMount(220);

                int overtimeHrs = 1;

                if (overtimeHrs > 0){
                    funcionariosEntity.setOvertime(overtimeHrs);
                    funcionariosEntity.setHoursMount(funcionariosEntity.getHoursMount() + overtimeHrs);
                    float newSalary = funcionariosDto.getSalary() + (overtimeHrs * 100);
                    funcionariosEntity.setSalary(newSalary);
                }
            }

            if (funcionariosDto.getCpf() != null && funcionariosRepository.existsByCpf(funcionariosDto.getCpf())){
                throw new RuntimeException("CPF JÁ CADASTRADO!!!!");
            }

            if (funcionariosRepository.existsByEmail(funcionariosDto.getEmail())){
                throw new RuntimeException("EMAIL JÁ CADASTRADO!!!!");
            }

            if (funcionariosRepository.existsByTelephone(funcionariosDto.getTelephone())){
                throw new RuntimeException("TELEFONE JÁ CADASTRADO!!!!");
            }

            FuncionariosEntity save = funcionariosRepository.save(funcionariosEntity);
            return FuncionariosDto.fromEntity(save);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }



}
