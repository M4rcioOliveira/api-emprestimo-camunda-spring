package com.github.m4rciooliveira.config;

import com.github.m4rciooliveira.wrapper.ConverterUtilWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfig {

    @Bean
    public ConverterUtilWrapper converterUtilWrapper() {
        return new ConverterUtilWrapper();
    }

}
