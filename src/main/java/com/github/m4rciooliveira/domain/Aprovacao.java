package com.github.m4rciooliveira.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.m4rciooliveira.domain.enums.StatusAprovacao;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aprovacao implements Serializable {

    private String cpf;
    private BigDecimal renda;
    private Integer score;
    private BigDecimal valorSolicitado;
    private BigDecimal valorParcela;
    private StatusAprovacao statusAprovacao;

}
