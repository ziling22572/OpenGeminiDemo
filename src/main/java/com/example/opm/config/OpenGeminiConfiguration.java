package com.example.opm.config;

import io.opengemini.client.api.Address;
import io.opengemini.client.okhttp.Configuration;
import io.opengemini.client.okhttp.ConnectionPoolConfig;
import io.opengemini.client.okhttp.OpenGeminiOkhttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@org.springframework.context.annotation.Configuration
public class OpenGeminiConfiguration {


    @Bean(destroyMethod = "close")
    public OpenGeminiOkhttpClient openGeminiOkhttpClient(@Value("${opengemini.host:127.0.0.1}") String host,
                                                         @Value("${opengemini.port:8086}") Integer port) {
        Configuration configuration = Configuration.builder()
                .addresses(Collections.singletonList(new Address(host, port)))
                .connectTimeout(Duration.ofSeconds(3))
                .connectionPoolConfig(ConnectionPoolConfig.of(10, 10, TimeUnit.SECONDS))
                .timeout(Duration.ofSeconds(20))
                .build();
        return new OpenGeminiOkhttpClient(configuration);
    }
}
