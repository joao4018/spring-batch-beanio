package com.example.demo.batch.item.Writer;


import com.example.demo.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;


@Component
@RequiredArgsConstructor
public class UserItemWriterDB {


    private final EntityManagerFactory emf;

    @Bean("jpaUserItemWriter")
    public JpaItemWriter<User> writer() {
        JpaItemWriter<User> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

}
