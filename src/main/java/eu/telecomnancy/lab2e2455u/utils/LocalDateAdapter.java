package eu.telecomnancy.lab2e2455u.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    final DDMMYYYYConverter converter;

    public LocalDateAdapter() {
        converter = new DDMMYYYYConverter();
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(converter.toString(localDate));
    }

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return converter.fromString(jsonElement.getAsJsonPrimitive().getAsString());
    }
}
