package com.github.m4rciooliveira.delegate;

import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.controller.dto.LoanRequestDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class VerificaScoreCreditoDelegate implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        LoanRequestDTO dto = (LoanRequestDTO) delegateExecution.getVariable(VariableName.LOAN_REQUEST_DTO);

        var cpfs = List.of("40697903060", "81761679082"); //CPF ALEATÓRIO GERADO NO 4devs

        if (cpfs.contains(dto.cpf())) {
            log.info("Seu CPF {} está aprovado para seguir com a concessão de crédito", dto.cpf());
        } else {
            log.info("Infelizmente nesse momento não foi concedido uma margem de crédito para seu CPF {}", dto.cpf());
        }


    }

}
