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

    @Bean("thread")
    public Flow thread(@Qualifier("flowDBtoFile") Flow step1,
                      @Qualifier("flowFiletoDB") Flow step2) {
        return new FlowBuilder<SimpleFlow>("thread")
                .split(taskExecutor())
                .add(step1, step2)
                .build();
    }
}
