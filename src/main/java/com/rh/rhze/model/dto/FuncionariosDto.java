package com.rh.rhze.model.dto;

import com.rh.rhze.model.entity.FuncionariosEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.rh.rhze.model.entity.FuncionariosEntity}
 */

@Data
public class FuncionariosDto implements Serializable {
    private Long employeeidentifier;
    private String name;
    private float salary;
    private String office;
    private LocalDateTime datehiring;
    private int hoursMount;
    private String cpf;
    private String telephone;
    private String email;
    private FuncionariosEntity.Status status;
    private String password;


    public FuncionariosDto(Long employeeidentifier,
                           String name,
                           float salary,
                           String office,
                           LocalDateTime datehiring,
                           int hoursMount,
                           String cpf,
                           String telephone,
                           String email,
                           FuncionariosEntity.Status status,
                           String password) {
        this.employeeidentifier = employeeidentifier;
        this.name = name;
        this.salary = salary;
        this.office = office;
        this.datehiring = datehiring;
        this.hoursMount = hoursMount;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
        this.password = password;
    }

    public static FuncionariosDto fromEntity(FuncionariosEntity entity){
        return new FuncionariosDto(
                entity.getEmployeeidentifier(),
                entity.getName(),
                entity.getSalary(),
                entity.getOffice(),
                entity.getDatehiring(),
                entity.getHoursMount(),
                entity.getCpf(),
                entity.getTelephone(),
                entity.getEmail(),
                entity.getStatus(),
                entity.getPassword()
        );
    }

    @Override
    public String toString() {
        return "FuncionariosDto{\n" +
                "employeeidentifier=" + employeeidentifier + "\n"+
                "name='" + name + '\'' + '\n' +
                "salary=" + salary + '\n' +
                "office='" + office + '\'' + '\n' +
                "datehiring=" + datehiring + '\n' +
                "hoursMount=" + hoursMount + '\n' +
                "cpf='" + cpf + '\'' + '\n' +
                "telephone='" + telephone + '\'' + '\n' +
                "email='" + email + '\'' + '\n' +
                "status='" + status + '\'' + '\n' +
                "password='" + password + '\'' + '\n' +
                '}';
    }
}