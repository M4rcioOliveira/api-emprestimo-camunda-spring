package com.github.m4rciooliveira.controller;

import com.github.m4rciooliveira.constants.ProcessName;
import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.controller.dto.LoanRequestDTO;
import com.github.m4rciooliveira.domain.Aprovacao;
import com.github.m4rciooliveira.util.FormatterUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.UUID;

@RestController
@RequestMapping("/v1/loan")
@Tag(name = "Loan", description = "API para solicitação de empréstimo.")
public class LoanController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final RuntimeService runtimeService;

    public LoanController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping
    @Operation(
            summary = "Solicitar Empréstimo.",
            description = "Sensibiliza o Camunda pra registrar uma nova instância de solicitação de empréstimo.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Solitação realizada.",
                            content = @Content(schema = @Schema(implementation = LoanRequestDTO.class))),
            }
    )
    public ResponseEntity<Void> solicitar(@RequestBody LoanRequestDTO dto) {

        Aprovacao aprovacao = Aprovacao.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .cpf(FormatterUtil.cpfMask(dto.cpf()))
                .valorSolicitado(dto.valor())
                .renda(dto.renda())
                .tipoEmprestimo(dto.tipoEmprestimo())
                .build();

        Map<String, Object> variables = new HashMap<>();
        variables.put(VariableName.APROVACAO_DOMAIN, aprovacao);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ProcessName.LOAN, variables);

        log.info("Execução do processo de {} iniciado com o ID {}", ProcessName.LOAN, processInstance.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
