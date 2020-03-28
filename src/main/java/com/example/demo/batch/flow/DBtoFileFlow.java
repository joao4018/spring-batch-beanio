package com.example.demo.batch.flow;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBtoFileFlow {

    @Bean("flow2")
    public Flow flow2(@Qualifier("stepReaderUsers") Step stepReaderUsers) {
        return new FlowBuilder<SimpleFlow>("flow2")
                .start(stepReaderUsers)
                .build();
    }
}
