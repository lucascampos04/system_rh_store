package com.rh.rhze.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuncionariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeidentifier;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "salario")
    private float salary;

    @Column(name = "cargo")
    private  String office;

    @Column(name = "data_contratacao")
    private LocalDateTime datehiring;

    @Column(name = "horas_mes")
    private int hoursMount;

    @Column(name = "horas_extras")
    private int overtime;

    @PrePersist
    public void prePersist(){
        if (datehiring == null){
            datehiring = LocalDateTime.now();
        }

        if (office != null && office.equals("caixa")){
            hoursMount = 220;

            boolean isOvertime = hoursMount > 220;

            if (isOvertime){
                overtime = 8;
                float newSalary = getSalary() + 100;
                salary = newSalary;
            }
        }
    }






















}
