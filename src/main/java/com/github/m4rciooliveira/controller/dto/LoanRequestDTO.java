package com.github.m4rciooliveira.controller.dto;


import com.github.m4rciooliveira.domain.enums.TipoEmprestimo;

import java.io.Serializable;

public record LoanRequestDTO(
        String cpf,
        TipoEmprestimo tipoEmprestimo,
        String renda,
        String valor
) implements Serializable {
}
