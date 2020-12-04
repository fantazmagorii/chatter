package com.example.chatter.repository;

import com.example.chatter.model.MessageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class MessageTypeConverter implements AttributeConverter<MessageType, String> {
    @Override
    public String convertToDatabaseColumn(MessageType attribute) {
        return attribute.name();
    }

    @Override
    public MessageType convertToEntityAttribute(String dbData) {
        return MessageType.valueOf(dbData);
    }
}
