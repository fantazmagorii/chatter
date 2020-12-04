package com.example.chatter;

import com.example.chatter.model.Message;
import com.example.chatter.model.MessageType;
import com.example.chatter.validation.ValidPayloadValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.validation.ConstraintValidatorContext;

class ValidPayloadValidatorTest {

    private static ValidPayloadValidator validPayloadValidator;
    private static Message message;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeAll
    public static void setUp(){
        validPayloadValidator = new ValidPayloadValidator();
        message = new Message();
    }

    @Test
    void testSendTextValid (){
        message.setMessageType(MessageType.SEND_TEXT);
        message.setPayload("Hello 123!");
        Assertions.assertTrue(validPayloadValidator.isValid(message, context));
    }

    @Test
    void testSendTextNotValidTooLong (){
        message.setMessageType(MessageType.SEND_TEXT);
        message.setPayload("aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa ");
        Assertions.assertFalse(validPayloadValidator.isValid(message, context));
    }

    @Test
    void testSendEmotionValid(){
        message.setMessageType(MessageType.SEND_EMOTION);
        message.setPayload(":) smile");
        Assertions.assertTrue(validPayloadValidator.isValid(message, context));
    }

    @Test
    void testSendEmotionNotValidTooShort(){
        testSendEmotionInvalid(":");
    }

    @Test
    void testSendEmotionNotValidTooLong(){
        testSendEmotionInvalid(":):):):):):");
    }

    @Test
    void testSendEmotionNotValidDigits(){
        testSendEmotionInvalid(":)1");
    }

    @Test
    void testPayloadNotAvailable(){
        testSendEmotionInvalid(null);
    }

   private void testSendEmotionInvalid(String payload){
        message.setMessageType(MessageType.SEND_EMOTION);
        message.setPayload(payload);
        Assertions.assertFalse(validPayloadValidator.isValid(message, context));
   }


}
