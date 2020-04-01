package com.example.demo.batch.item.Processor;

import com.example.demo.core.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpperCaseUserProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) {
        final String firstName = user.getName().toUpperCase();
        final String lastName = user.getLastname().toUpperCase();

        final User transformed = new User(firstName, lastName, user.getBirthday());
        return transformed;
    }
}



