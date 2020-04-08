package com.example.demo.batch.listener;

import com.example.demo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ListenerCleanDB implements StepExecutionListener {

    private final UserService userService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
         log.info("StepExecutionListener  ---- before  ");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        userService.deleteAll();
         log.info("StepExecutionListener  ---- after  ");
        return null;
    }
}