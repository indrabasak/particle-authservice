package com.divstar.particle.authservice.rest.languageservice.converters;

import com.divstar.particle.authservice.rest.tos.Language;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * This class is a custom {@link JsonDeserializer}-derived deserializer for
 * {@link Language}-objects.
 * <p>
 * It tells jackson2 how to convert the value of a given JSON-property
 * ("language") to a {@link Language}-object.
 *
 * @author divstar
 */
public class LanguageDeserializer extends JsonDeserializer<Language> {

    /**
     * This method deserializes a given excerpt of the original JSON-string
     * (usually just the string containing the value
     * (e.g. "en" or "de")) to a corresponding {@link Language}-object.
     *
     * @see JsonDeserializer#deserialize(JsonParser, DeserializationContext)
     */
    @Override
    public Language deserialize(final JsonParser p,
            final DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.readValueAsTree();
        final String languageCode = node.asText();

        if (languageCode != null) {
            Language language = new Language();
            language.setLanguageCode(languageCode);
            return language;
        }

        return null;
    }
}
