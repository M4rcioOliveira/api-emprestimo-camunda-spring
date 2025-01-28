package com.github.m4rciooliveira.util;

public final class FormatterUtil {

    private FormatterUtil() {
    }

    public static String cpfMask(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

}
