package com.github.m4rciooliveira.delegate;

import com.github.m4rciooliveira.constants.VariableName;
import com.github.m4rciooliveira.domain.Aprovacao;
import com.github.m4rciooliveira.util.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.m4rciooliveira.util.ConverterUtil.strToBigDecimal;


@Slf4j
public class CalculaConcessaoCreditoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        log.info("Iniciando a execução do {}", this.getClass().getSimpleName());

        Aprovacao aprovacao = (Aprovacao) delegateExecution.getVariable(VariableName.APROVACAO_DOMAIN);

        calculaConcessao(aprovacao);

    }

    //Simula uma concessão de crédito obtendo o valor da parcela com base na margem da renda em cima do valor solicitado com juros
    //Calculo simples adicionando 120% em cima do valor do pedio
    private void calculaConcessao(Aprovacao aprovacao) {

        var valorSolicitado = strToBigDecimal(aprovacao.getValorSolicitado());

        var renda = strToBigDecimal(aprovacao.getRenda());

        var juros = valorSolicitado.multiply(BigDecimal.valueOf(1.2));

        var valorSolicitadoComJuros = valorSolicitado.add(juros);

        log.info("Valor solicitado com juros = {}", valorSolicitadoComJuros);

        var valorParcela = valorSolicitadoComJuros.divide(BigDecimal.valueOf(48), 2, RoundingMode.HALF_UP); //48 -> MESES

        log.info("Valor Parcela = {}", valorParcela);

        var margem = renda.multiply(BigDecimal.valueOf(0.3));

        log.info("Valor Margem = {}", margem);

        if (valorParcela.compareTo(margem) > 0) {
            aprovacao.setValorParcela(ConverterUtil.bigDecimalToStr(BigDecimal.ZERO));
            aprovacao.setValorEmprestimo(ConverterUtil.bigDecimalToStr(BigDecimal.ZERO));
        } else {
            aprovacao.setValorParcela(ConverterUtil.bigDecimalToStr(valorParcela));
            aprovacao.setValorEmprestimo(ConverterUtil.bigDecimalToStr(valorSolicitadoComJuros));
        }

    }


}
