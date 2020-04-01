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

    @Bean("flowDBtoFile")
    public Flow flowDBtoFile(@Qualifier("stepReaderUsers") Step step) {
        return new FlowBuilder<SimpleFlow>("flowDBtoFile")
                .start(step)
                .build();
    }
}
