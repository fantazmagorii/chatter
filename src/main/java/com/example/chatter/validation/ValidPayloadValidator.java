package com.example.chatter.validation;

import com.example.chatter.model.Message;
import com.example.chatter.model.MessageType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class ValidPayloadValidator implements ConstraintValidator<ValidPayload, Message>{
    @Override
    public void initialize(ValidPayload constraintAnnotation){}

    @Override
    public boolean isValid(Message value, ConstraintValidatorContext context){
        if(value.getPayload()==null){
            return false;
        }
        return value.getMessageType() == MessageType.SEND_TEXT ?
                value.getPayload().length()<=160:
                Pattern.matches("\\D{2,10}$", value.getPayload());
    }
}
