package com.github.m4rciooliveira.delegate;

import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.domain.Aprovacao;
import com.github.m4rciooliveira.domain.enums.StatusAprovacao;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class ReprovaCreditoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        log.info("Iniciando a execução do {}", this.getClass().getSimpleName());

        Aprovacao aprovacao = (Aprovacao) delegateExecution.getVariable(VariableName.APROVACAO_DOMAIN);

        aprovacao.setStatusAprovacao(StatusAprovacao.REPROVADO);

        Map<String, Object> variables = new HashMap<>();
        variables.put(VariableName.APROVACAO_DOMAIN, aprovacao);

        delegateExecution.setVariables(variables);

    }


}
