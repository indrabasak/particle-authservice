package com.divstar.particle.authservice.rest.mapper;

import com.divstar.particle.authservice.rest.languageservice.LanguageService;
import com.divstar.particle.authservice.rest.tos.RegisterableAccount;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class RegisterableAccountDeserializer
        extends StdDeserializer<RegisterableAccount>
        implements ResolvableDeserializer {

    private final JsonDeserializer<?> defaultDeserializer;

    private LanguageService languageService;

    public RegisterableAccountDeserializer(
            JsonDeserializer<?> defaultDeserializer,
            LanguageService languageService) {
        super(RegisterableAccount.class);
        this.defaultDeserializer = defaultDeserializer;
        this.languageService = languageService;
    }

    @Override
    public RegisterableAccount deserialize(JsonParser parser,
            DeserializationContext context) throws IOException {
        RegisterableAccount account =
                (RegisterableAccount) defaultDeserializer.deserialize(parser,
                        context);

        if (account.getLanguage() != null && account.getLanguage().getLanguageCode() != null) {
            account.setLanguage(
                    languageService.getLanguage(
                            account.getLanguage().getLanguageCode()));
        } else {
            account.setLanguage(languageService.getLanguage("en"));
        }

        return account;
    }

    @Override
    public void resolve(
            DeserializationContext context) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(context);
    }
}
