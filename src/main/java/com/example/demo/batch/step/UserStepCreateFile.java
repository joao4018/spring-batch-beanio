package com.example.demo.batch.step;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class UserStepCreateFile extends StepExecutionListenerSupport {

    @NonNull
    private StepBuilderFactory stepBuilderFactory;


    @Bean("stepReaderUsers")
    public Step stepReaderUsers(@Qualifier("jpaUserItemReader") ItemReader reader,
                                @Qualifier("userItemWriter") ItemWriter writer,
                                @Qualifier("listenerCleanDB") StepExecutionListener listener
    ) {
        return this.stepBuilderFactory.get("STEP_READER_USERS_IN_DATABASE")
                .listener(listener)
                .chunk(1000)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
