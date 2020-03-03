package com.example.demo.batch.job.step;

import com.example.demo.core.model.entity.Autobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    private final EntityManager em;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = entityManager;
    }
    private EntityManager entityManager;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            TypedQuery<Autobot> query =
                    em.createQuery("SELECT c FROM Autobot c", Autobot.class);
            List<Autobot> results = query.getResultList();
            results.forEach(autobot -> {
                log.info("Found <" + autobot.toString() + "> in the database.");
            } );
        }
    }
}