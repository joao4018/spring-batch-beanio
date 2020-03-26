package com.example.demo.batch.item.Processor;

import com.example.demo.core.model.User;
import com.example.demo.core.streams.mapping.Registry;
import com.example.demo.core.streams.mapping.user.UserBody;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class RegistryToUserProcessor implements ItemProcessor<Registry, User> {

    @Override
    public User process(Registry registry) {
        final String name = ((UserBody) registry).getName();
        final String lastName = ((UserBody) registry).getLastname();
        final Date Birthday = ((UserBody) registry).getBirthday();

        return new User(name, lastName, Birthday);
    }
}



