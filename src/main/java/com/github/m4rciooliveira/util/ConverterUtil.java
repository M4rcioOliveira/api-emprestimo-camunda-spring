package com.github.m4rciooliveira.util;

import com.github.m4rciooliveira.exception.ConverterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.github.m4rciooliveira.constants.MsgException.MSG_CONVERTER_STR_TO_BIGDECIMAL_EXCEPTION;

@Component
public final class ConverterUtil {

    private ConverterUtil() {
    }

    public static String bigDecimalToStr(BigDecimal value) {
        return value.toString();
    }

    public static BigDecimal strToBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new ConverterException(MSG_CONVERTER_STR_TO_BIGDECIMAL_EXCEPTION, e);
        }
    }

}
