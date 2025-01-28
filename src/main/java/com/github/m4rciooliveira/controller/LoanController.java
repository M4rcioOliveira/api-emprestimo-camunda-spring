package com.github.m4rciooliveira.controller;

import com.github.m4rciooliveira.constants.ProcessName;
import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.controller.dto.LoanRequestDTO;
import com.github.m4rciooliveira.domain.Aprovacao;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/loan")
public class LoanController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final RuntimeService runtimeService;

    public LoanController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping
    public ResponseEntity<Void> solicitar(@RequestBody LoanRequestDTO dto) {

        Aprovacao aprovacao =  Aprovacao.builder()
                .cpf(dto.cpf())
                .valorSolicitado(dto.valor())
                .renda(dto.renda())
                .tipoEmprestimo(dto.tipoEmprestimo())
                .build();

        Map<String, Object> variables = new HashMap<>();
        variables.put(VariableName.APROVACAO_DOMAIN, aprovacao);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ProcessName.VERIFICA_SCORE_CREDITO, variables);

        log.info("Execução do processo de {} iniciado com o ID {}", ProcessName.VERIFICA_SCORE_CREDITO, processInstance.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
