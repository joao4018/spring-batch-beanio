package com.example.demo.batch.item.Writer;


import com.example.demo.core.model.User;
import com.example.demo.core.streams.configuration.FlatFileConfiguration;
import com.example.demo.core.streams.enums.FlatFileOption;
import com.example.demo.core.streams.enums.StreamName;
import com.example.demo.core.streams.mapping.user.UserHeader;
import com.example.demo.core.streams.mapping.user.UserRegistry;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@StepScope
public class UserItemWriter extends StepExecutionListenerSupport implements ItemWriter<User> {

    @NonNull
    private FlatFileConfiguration configuration;
    @Value("${file.directory.out}")
    private String path;
    private UserRegistry userRegistry;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().putString("MY_VALUE","asadsa");
        this.configuration.initialize(FlatFileOption.WRITER, StreamName.USER_CSV, path);
    }

    @Override
    public void write(List<? extends User> list) throws Exception {

        userRegistry = new UserRegistry
                .UserRegistryBuilder()
                .header(new UserHeader
                        .UserHeaderBuilder()
                        .dateGenerate(new Date())
                        .registryAmount(
                                list.size()
                        ).build())
                .body((List<User>) list)
                .build();

        this.configuration.getWriter().write(this.userRegistry.getUsers());

        this.configuration.close();
    }

}
