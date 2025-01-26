package com.github.m4rciooliveira.delegate;

import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.controller.dto.LoanRequestDTO;
import com.github.m4rciooliveira.domain.Aprovacao;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;


@Slf4j
public class ConsultaScoreCreditoDelegate implements JavaDelegate {

    private static final Integer SCORE_MIN = 700;
    private static final Integer SCORE_BOUND = 301;

    private static final Random RANDOM = new Random();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        log.info("Iniciando a execução do {}", this.getClass().getSimpleName());

        LoanRequestDTO dto = (LoanRequestDTO) delegateExecution.getVariable(VariableName.LOAN_REQUEST_DTO);

        log.info("CPF {} ", dto.cpf());

        var cpfs = Set.of("40697903060", "81761679082");

        var score = 0;

        //Lógica usada para substituir uma api ou algum outro provedor de score.
        if (cpfs.contains(dto.cpf())) {
            score = SCORE_MIN + RANDOM.nextInt(SCORE_BOUND); //Se o CPF está na lista, pontuação ente 700 e 1000.
        } else {
            score = RANDOM.nextInt(SCORE_MIN);  //Se não estiver, pontuação até 699.
        }

        log.info("SCORE {}", score);

        var aprovacao = Aprovacao.builder()
                .cpf(dto.cpf())
                .renda(dto.renda())
                .score(score)
                .build();

        Map<String, Object> variables = new HashMap<>();
        variables.put(VariableName.APROVACAO_DOMAIN, aprovacao);

        delegateExecution.setVariables(variables);
    }


}
