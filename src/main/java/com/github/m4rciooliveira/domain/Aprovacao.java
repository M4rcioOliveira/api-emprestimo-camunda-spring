package com.github.m4rciooliveira.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.m4rciooliveira.domain.enums.StatusAprovacao;
import com.github.m4rciooliveira.domain.enums.TipoEmprestimo;
import lombok.*;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aprovacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String cpf;
    private String renda;
    private Integer score;
    private TipoEmprestimo tipoEmprestimo;
    private String valorSolicitado;
    private String valorEmprestimo;
    private String valorParcela;
    private StatusAprovacao statusAprovacao;
    private String aprovador;

}
