package com.example.demo.batch.item.Writer;

import com.example.demo.core.streams.configuration.FlatFileConfiguration;
import com.example.demo.core.streams.enums.FlatFileOption;
import com.example.demo.core.streams.enums.StreamName;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.rmi.registry.Registry;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserItemWriterFile extends StepExecutionListenerSupport implements ItemWriter<Registry> {

    @NonNull
    private FlatFileConfiguration<Registry> configuration;
    @Value("${file.directory.out}")
    private String path;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.configuration.initialize(FlatFileOption.WRITER, StreamName.USER_DELIMITED, path);
    }

    @Override
    public void write(List<? extends Registry> items) throws Exception {
        this.configuration.getWriter().write(items);
    }
}
