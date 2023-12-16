package com.rh.rhze.controller.service;

import com.rh.rhze.infra.exception.ValidacaoExcpetion;
import com.rh.rhze.model.dto.FuncionariosDto;
import com.rh.rhze.model.entity.FuncionariosEntity;
import com.rh.rhze.model.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionariosServices {
    @Autowired
    FuncionariosRepository funcionariosRepository;

    /**
     * Obtém uma lista de todos os funcionários
     *
     * @return Lista de DTOs de funcionários
     * @throws RuntimeException se ocorrer um erro ao recuperar a lista de funcionarios
     *
     * */
    public List<FuncionariosDto> listAllFuncionarios(){
        try {
            return funcionariosRepository.findAll().stream()
                    .map(FuncionariosDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new RuntimeException("Erro ao exibir todos os usuarios");
        }
    }

    /**
     * Insere um novo funcionario com base nos dados fornecidos
     *
     * @param funcionariosDto DTO contendo os dados do novo funcionário
     * @return DTO do funcionário recém-inserido
     * @throws ValidacaoExcpetion se houver problemas de validação ou ocorrer um erro durante a inserção
     *
     * */
    public FuncionariosDto insertFuncionario(FuncionariosDto funcionariosDto){
        try {

            String senhaAleatoria = UUID.randomUUID().toString();

            FuncionariosEntity funcionariosEntity = new FuncionariosEntity();
            funcionariosEntity.setPassword(new BCryptPasswordEncoder().encode(senhaAleatoria));
            funcionariosEntity.setName(funcionariosDto.getName());
            funcionariosEntity.setOffice(funcionariosDto.getOffice());
            funcionariosEntity.setSalary(funcionariosDto.getSalary());
            funcionariosEntity.setCpf(funcionariosDto.getCpf());
            funcionariosEntity.setEmail(funcionariosDto.getEmail());
            funcionariosEntity.setTelephone(funcionariosDto.getTelephone());
            funcionariosEntity.setStatus(funcionariosDto.getStatus());
            funcionariosEntity.setBirthdate(funcionariosDto.getBirthdate());

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

            if (funcionariosDto.getStatus() == null){
                throw new ValidacaoExcpetion("O STATUS NÃO PODE SER NULL");
            }

            if (funcionariosDto.getCpf() != null && funcionariosRepository.existsByCpf(funcionariosDto.getCpf())){
                throw new ValidacaoExcpetion("CPF JÁ CADASTRADO!!!!");
            }

            if (funcionariosRepository.existsByEmail(funcionariosDto.getEmail())){
                throw new ValidacaoExcpetion("EMAIL JÁ CADASTRADO!!!!");
            }

            if (funcionariosRepository.existsByTelephone(funcionariosDto.getTelephone())){
                throw new ValidacaoExcpetion("TELEFONE JÁ CADASTRADO!!!!");
            }

            FuncionariosEntity save = funcionariosRepository.save(funcionariosEntity);
            return FuncionariosDto.fromEntity(save);
        } catch (Exception e){
            throw new ValidacaoExcpetion(e.getMessage());
        }
    }


    /**
     * Exclui todos os funcionários que tem o campo de 'cpf' null
     *
     * @throws ValidacaoExcpetion se ocorrer um erro durante a exclusão
     *
     * */
    public void deletedFuncionarioID(){
        try {
            List<FuncionariosEntity> funcionariosEntityList = funcionariosRepository.findByCpfIsNull();

            for (FuncionariosEntity funcionariosEntity: funcionariosEntityList) {
                funcionariosRepository.deleteById(funcionariosEntity.getEmployeeidentifier());
            }
        } catch (Exception e){
            throw new ValidacaoExcpetion("Erro ao excluir o Funcionario com o ID : ");
        }
    }



}
