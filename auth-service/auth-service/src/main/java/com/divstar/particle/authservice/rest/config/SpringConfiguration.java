package com.divstar.particle.authservice.rest.config;

import com.divstar.particle.authservice.rest.languageservice.LanguageService;
import com.divstar.particle.authservice.rest.mapper.RegisterableAccountDeserializer;
import com.divstar.particle.authservice.rest.tos.RegisterableAccount;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class SpringConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(
            final LanguageService service) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyDeserializer(
                    DeserializationConfig config, BeanDescription beanDesc,
                    JsonDeserializer<?> deserializer) {
                if (beanDesc.getBeanClass() == RegisterableAccount.class) {
                    return new RegisterableAccountDeserializer(deserializer,
                            service);
                }

                return deserializer;
            }
        });

        builder.modules(module);

        return builder;
    }
}
