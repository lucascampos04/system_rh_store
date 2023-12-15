package com.rh.rhze.model.dto;

import com.rh.rhze.model.entity.FuncionariosEntity;
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
    public FuncionariosDto(Long employeeidentifier, String name, float salary, String office, LocalDateTime datehiring, int hoursMount) {
        this.employeeidentifier = employeeidentifier;
        this.name = name;
        this.salary = salary;
        this.office = office;
        this.datehiring = datehiring;
        this.hoursMount = hoursMount;
    }

    public static FuncionariosDto fromEntity(FuncionariosEntity entity){
        return new FuncionariosDto(
                entity.getEmployeeidentifier(),
                entity.getName(),
                entity.getSalary(),
                entity.getOffice(),
                entity.getDatehiring(),
                entity.getHoursMount()
        );
    }
}