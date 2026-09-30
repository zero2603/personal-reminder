package com.example.personalreminder.converter;

import com.example.personalreminder.constant.RepeatEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RepeatEnumConverter implements AttributeConverter<RepeatEnum, String> {
    @Override
    public String convertToDatabaseColumn(RepeatEnum repeat) {
        return repeat == null ? null : repeat.getName();
    }

    @Override
    public RepeatEnum convertToEntityAttribute(String value) {
        return value == null ? null : RepeatEnum.fromName(value);
    }
}
