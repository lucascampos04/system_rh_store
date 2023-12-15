package com.rh.rhze.controller.funcionarios;

import com.rh.rhze.controller.service.FuncionariosServices;
import com.rh.rhze.model.dto.FuncionariosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/rh/funcionarios")
@RestController
public class FuncionariosController {

    @Autowired
    private FuncionariosServices funcionariosServices;

    @GetMapping("/rh/show/funcionarios")
    public List<FuncionariosDto> getAllFuncionarios(){
        try{
            return funcionariosServices.listAllFuncionarios();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
