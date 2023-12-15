package com.rh.rhze.controller.funcionarios;

import com.rh.rhze.controller.service.FuncionariosServices;
import com.rh.rhze.infra.exception.ValidacaoExcpetion;
import com.rh.rhze.model.dto.FuncionariosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/rh/funcionarios")
@RestController
public class FuncionariosController {

    @Autowired
    private FuncionariosServices funcionariosServices;
    @PostMapping("/rh/inserir/funcionario")
    public ResponseEntity<String> inserirFuncionario(@RequestBody FuncionariosDto dto){
        try {
            FuncionariosDto funcionariosDto = funcionariosServices.insertFuncionario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(funcionariosDto));
        } catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
           return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }
    @GetMapping("/rh/show/funcionarios")
    public List<FuncionariosDto> getAllFuncionarios(){
        try{
            return funcionariosServices.listAllFuncionarios();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/rh/deleted/full/nulls/funcionarios")
    public ResponseEntity<String> deleteFuncionario(){
        try {
            funcionariosServices.deletedFuncionarioID();
            return ResponseEntity.noContent().build(); // status 204
        } catch (Exception e){
            throw new ValidacaoExcpetion("TODOS OS FUNCIONARIOS COM CAMPOS NULOS APAGADOS");
        }
    }
}
