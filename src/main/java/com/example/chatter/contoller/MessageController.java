package com.example.chatter.contoller;

import com.example.chatter.model.Message;
import com.example.chatter.service.MessageService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages/{type}")
    public ResponseEntity<String> sendMessage(@PathVariable String type, @RequestBody Message message){
        messageService.createMessage(type,message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
