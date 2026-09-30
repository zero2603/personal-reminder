package com.example.personalreminder.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RepeatEnum {
    MONTHLY("monthly"),
    ANNUAL("annual");

    private final String name;

    RepeatEnum(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static RepeatEnum fromName(String value) {
        for (RepeatEnum repeat : values()) {
            if (repeat.name.equalsIgnoreCase(value) || repeat.name().equalsIgnoreCase(value)) {
                return repeat;
            }
        }
        throw new IllegalArgumentException("Unknown repeat value: " + value);
    }
}
