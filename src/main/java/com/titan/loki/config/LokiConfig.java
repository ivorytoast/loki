package com.titan.loki.config;

import com.titan.loki.Loki;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Log
@Configuration
public class LokiConfig {

    @Bean
    public CommandLineRunner lokiRunner(TaskExecutor executor) {
        log.info("Starting Loki");
        return args -> executor.execute(new Loki());
    }

}
