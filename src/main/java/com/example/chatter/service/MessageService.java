package com.example.chatter.service;

import com.example.chatter.exception.IllegalMessageTypeException;
import com.example.chatter.model.Message;
import com.example.chatter.model.MessageType;
import com.example.chatter.repository.MessageRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class MessageService {

    private final MessageRepository messageRepository;

    public void createMessage(String messageType, Message message){
        message.setMessageType(getMessageType(messageType.toUpperCase()));
        message.setCreatedAt(new Date());
        messageRepository.save(message);
    }

    private MessageType getMessageType(String messageType){
        try{
           return MessageType.valueOf(messageType);
        } catch (IllegalArgumentException e){
            throw new IllegalMessageTypeException();
        }
    }
}
