package com.github.m4rciooliveira.delegate;

import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.domain.Aprovacao;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Slf4j
public class CalculaConcessaoCreditoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        log.info("Iniciando a execução do {}", this.getClass().getSimpleName());

        Aprovacao aprovacao = (Aprovacao) delegateExecution.getVariable(VariableName.APROVACAO_DOMAIN);

        var valorParcela = calculaValorParcela(aprovacao.getValorSolicitado(), aprovacao.getRenda());

        aprovacao.setValorParcela(valorParcela);

    }

    //Simula uma concessão de crédito obtendo o valor da parcela com base na margem da renda em cima do valor solicitado com juros
    private BigDecimal calculaValorParcela(BigDecimal valorSolicitado, BigDecimal renda) {

        var juros = valorSolicitado.multiply(BigDecimal.valueOf(1.2));

        var valorSolicitadoComJuros = valorSolicitado.add(juros);

        log.info("Valor solicitado com juros = {}", valorSolicitadoComJuros);

        var valorParcela = valorSolicitadoComJuros.divide(BigDecimal.valueOf(48), 2, RoundingMode.HALF_UP); //48 -> MESES

        log.info("Valor Parcela = {}", valorParcela);

        var margem = renda.multiply(BigDecimal.valueOf(0.3));

        log.info("Valor Margem = {}", margem);

        if (valorParcela.compareTo(margem) > 0) {
            return BigDecimal.ZERO;
        }

        return valorParcela;

    }


}
