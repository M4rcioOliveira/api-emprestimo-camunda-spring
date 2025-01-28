package com.github.m4rciooliveira.wrapper;

import com.github.m4rciooliveira.util.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ConverterUtilWrapper {

    public String bigDecimalToStr(BigDecimal value) {
        return ConverterUtil.bigDecimalToStr(value);
    }

    public BigDecimal strToBigDecimal(String value) {
        return ConverterUtil.strToBigDecimal(value);
    }

}
