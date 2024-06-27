package com.hhplus.lectures.domain.entity.Lectures.type.converter;

import com.hhplus.lectures.common.type.RegistStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Converter(autoApply = true)
public class RegistStatusConverter implements AttributeConverter<RegistStatus, String> {

    @Override
    public String convertToDatabaseColumn(RegistStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public RegistStatus convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(RegistStatus.class).stream()
                      .filter(e -> e.getCode().equals(dbData))
                      .findAny()
                      .orElseThrow(NoSuchElementException::new);
    }
}
