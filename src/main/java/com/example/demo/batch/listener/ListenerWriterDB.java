package com.example.demo.batch.listener;

import com.example.demo.batch.job.UserJob;
import com.example.demo.core.repository.UserRepository;
import com.example.demo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class ListenerWriterDB {

//    private static final Logger log = LoggerFactory.getLogger(UserJob.class);

    private final UserService userService;


    @Bean("listenerWriterJob")
    public JobExecutionListener listener() {
        return new JobExecutionListener() {


            @Override
            public void beforeJob(JobExecution jobExecution) {

            }


            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    log.info("!!! JOB FINISHED! Time to verify the results");
                    userService.findByAll().
                            forEach(user -> log.info("Found <" + user + "> in the database."));
                }
            }
        };
    }
}
