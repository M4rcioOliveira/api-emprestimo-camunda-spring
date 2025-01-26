package com.github.m4rciooliveira.controller.dto;


import com.github.m4rciooliveira.domain.enums.TipoEmprestimo;

import java.io.Serializable;
import java.math.BigDecimal;

public record LoanRequestDTO(
        String cpf,
        TipoEmprestimo tipoEmprestimo,
        BigDecimal renda,
        BigDecimal valor
) implements Serializable {
}
