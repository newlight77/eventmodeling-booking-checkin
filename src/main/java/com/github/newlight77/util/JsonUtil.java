package com.github.newlight77.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

    public static String toJson(final Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalStateException("Caught an exception while converting an object to json", e);
        }
    }

    public static String toJson(final List<Object> list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (IOException ioe) {
            throw new IllegalStateException("Error convert " + list + " to json string", ioe);
        }
    }

    public static <T> T fromJson(String json, final Class<T> resourceClass) {
        try {
            return MAPPER.readValue(json, resourceClass);
        } catch (IOException e) {
            throw new IllegalStateException("Caught an exception while converting a json back to object", e);
        }
    }

    public static <T> T fromJson(final String input, final TypeReference<T> expectedType) {
        try {
            return MAPPER.readValue(input, expectedType);
        } catch (IOException ioe) {
            String msg =
                    "Error convert from json {" + input + "} " + "to list of objects with expecting type " + expectedType.toString();
            throw new IllegalStateException(msg, ioe);
        }
    }

    public static <T> List<T> toListOfObjects(final String json) {
        return fromJson(json, new TypeReference<List<T>>() {});
    }

//    public static <T> T fromJson(final String data, final Type type) {
//        try {
//            JavaType javaType = MAPPER.getTypeFactory().constructType(type);
//            return MAPPER.readValue(data, javaType);
//        } catch (IOException ioe) {
//            final String msg = "Error convert from json {" + data + "} to object " + type.toString();
//            throw new IllegalStateException(msg, ioe);
//        }
//    }
}
