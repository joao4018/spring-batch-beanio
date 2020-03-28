package com.example.demo.batch.flow;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class FlowThread {

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean("flow3")
    public Flow flow3(@Qualifier("flow1") Flow stepReaderUsers,
                      @Qualifier("flow2") Flow xup) {
        return new FlowBuilder<SimpleFlow>("flow3")
                .split(taskExecutor())
                .add(stepReaderUsers, xup)
                .build();
    }
}
