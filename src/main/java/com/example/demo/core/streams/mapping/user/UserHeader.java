package com.example.demo.core.streams.mapping.user;

import com.example.demo.core.streams.mapping.Registry;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

import java.util.Date;

@Record
@Fields({@Field(length = 6, name = "identificador de registro", rid = true, literal = "HEADER")})
public class UserHeader implements Registry {

    @Field(length = 8, name = "data geracao do arquivo", format = "ddMMyyyy")
    private Date dateGenerate;
    @Field(length = 6, align = Align.RIGHT, padding = '0', type = Integer.class)
    private Integer registryAmount;

    public UserHeader() {
    }

    private UserHeader(Date dateGenerate, Integer registryAmount) {
        this.dateGenerate = dateGenerate;
        this.registryAmount = registryAmount;
    }

    public static class UserHeaderBuilder {
        private Date dateGenerate;
        private Integer registryAmount;

        public UserHeaderBuilder() {
        }

        public UserHeaderBuilder dateGenerate(Date dateGenerate) {
            this.dateGenerate = dateGenerate;
            return this;
        }

        public UserHeaderBuilder registryAmount(Integer registryAmount) {
            this.registryAmount = registryAmount;
            return this;
        }

        public UserHeader build() {
            return new UserHeader(dateGenerate, registryAmount);
        }
    }

    public Date getDateGenerate() {
        return dateGenerate;
    }

    public Integer getRegistryAmount() {
        return registryAmount;
    }
}
