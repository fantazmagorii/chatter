package com.example.chatter;

import com.example.chatter.contoller.MessageController;
import com.example.chatter.exception.ApiExceptionHandler;
import com.example.chatter.model.Message;
import com.example.chatter.repository.MessageRepository;
import com.example.chatter.service.MessageService;
import com.example.chatter.validation.ValidPayloadValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolationException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {MessageController.class, MessageService.class, MessageRepository.class, ValidPayloadValidator.class, ApiExceptionHandler.class})
@WebMvcTest
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    void testSendValidType() throws Exception {
        ResultActions resultActions = sendRequest("send_text", "{\"payload\":\" 123 Hello! \"}");
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void testSendNotValidType() throws Exception {
        ResultActions resultActions = sendRequest("invalid_type", "{}");
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void testSendNotValidPayload() throws Exception {
        doThrow(ConstraintViolationException.class)
                .when(messageRepository).save(isA(Message.class));
        ResultActions resultActions = sendRequest("send_emotion", "{\"payload\":\" :)1\"}");
        resultActions.andExpect(status().isPreconditionFailed());
    }

    private ResultActions sendRequest(String type, String payload) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.post("/messages/{type}", type)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
    }
}