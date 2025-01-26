package com.github.m4rciooliveira.controller.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public record LoanRequestDTO(
        String cpf,
        BigDecimal renda,
        BigDecimal valor
) implements Serializable {
}
