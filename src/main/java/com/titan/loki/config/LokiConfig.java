package com.titan.loki.config;

import com.titan.loki.service.LokiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LokiConfig {

    @Bean
    public LokiService lokiService() {
        return new LokiService();
    }

}
