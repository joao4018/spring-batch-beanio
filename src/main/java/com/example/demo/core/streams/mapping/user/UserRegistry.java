package com.example.demo.core.streams.mapping.user;

import com.example.demo.core.model.User;
import com.example.demo.core.streams.mapping.Registry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRegistry implements Registry {

    private List<Registry> users;
    private UserRegistryBuilder userRegistryBuilder;

    private UserRegistry(List<Registry> users, UserRegistryBuilder userRegistryBuilder) {
        this.users = users;
        this.userRegistryBuilder = userRegistryBuilder;
    }

    public static class UserRegistryBuilder {
        private List<Registry> registries = new ArrayList<>();

        public UserRegistryBuilder() {
        }

        public UserRegistryBuilder header(UserHeader header) {
            registries.add(0, header);
            return this;
        }

        public UserRegistryBuilder body(List<User> users) throws Exception {
            users.forEach(user -> {
                registries.add(new UserBody
                        .UserBodyBuilder()
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .birthday(user.getBirthday())
                        .build());
            });

            // se o header nao estiver preenchido.
            if (registries.get(0) == null && !(registries.get(0) instanceof UserHeader)) {
                registries.add(0, new UserHeader
                        .UserHeaderBuilder()
                        .dateGenerate(new Date())
                        .registryAmount(registries.size()).build());
            }
            return this;
        }

        public UserRegistry build() {
            return new UserRegistry(registries, this);
        }
    }
    @Override
    public List<Registry> getUsers() {
        return users;
    }

    public UserRegistryBuilder getUserRegistryBuilder() {
        return userRegistryBuilder;
    }
}
