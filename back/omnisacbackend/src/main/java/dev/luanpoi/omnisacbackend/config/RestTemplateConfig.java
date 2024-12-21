package dev.luanpoi.omnisacbackend.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    static final int TIMEOUT = 30000;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       return builder.requestFactory(() -> {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(TIMEOUT);
            factory.setReadTimeout(TIMEOUT);
            return factory;
        }).build();
    }

}
