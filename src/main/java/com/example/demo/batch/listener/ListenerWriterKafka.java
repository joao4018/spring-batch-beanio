package com.example.demo.batch.listener;

import com.example.demo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class ListenerWriterKafka {

//    private static final Logger log = LoggerFactory.getLogger(UserJob.class);

    private final UserService userService;

    @Value("${topic.batch-kafka-users}")
    private String batchKafka;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Bean("listenerWriterKafkaProducer")
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
                            forEach(user ->
                            {
                                log.info("Mensagem -> {}", user);
                                kafkaTemplate.send(batchKafka, String.valueOf(user));
                            });
                }
            }
        };
    }
}
