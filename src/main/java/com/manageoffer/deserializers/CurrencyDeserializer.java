package com.manageoffer.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.manageoffer.model.Currency;

import java.io.IOException;
 
public class CurrencyDeserializer extends JsonDeserializer<Currency> {
    @Override
    public Currency deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Currency type = Currency.fromCurrency(jp.getValueAsString());
        if (type != null) {
            return type;
        }
        throw new JsonMappingException("invalid value for type, must be 'GBP', 'USD', 'EUR' or 'TRY'");
    }
}