package com.example.demo.batch.item.Processor;

import com.example.demo.core.model.User;
import com.example.demo.core.streams.mapping.Registry;
import com.example.demo.core.streams.mapping.user.UserBody;
import com.example.demo.core.streams.mapping.user.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@StepScope
public class RegistryToUserProcessor implements ItemProcessor<Registry, User> {

    private int count = 0;

    @Override
    public  User process(Registry registry) throws Exception {
        return new User(((UserBody) registry).getName(), ((UserBody) registry).getLastname(),  ((UserBody) registry).getBirthday());
    }
}



