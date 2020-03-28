package com.example.demo.batch.step;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserStepWriterDB extends StepExecutionListenerSupport {

    @NonNull
    private StepBuilderFactory stepBuilderFactory;

    @Bean("stepWriterUsersDB")
    public Step stepWriteUsersDB(@Qualifier("jpaUserItemReader") ItemReader reader,
                                 @Qualifier("jpaUserItemWriter") ItemWriter writer,
                                 @Qualifier("userItemProcessor") ItemProcessor processor) {
        return this.stepBuilderFactory.get("STEP_WRITER_USERS_IN_DATABASE")
                .chunk(1000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
