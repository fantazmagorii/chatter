package com.example.chatter.model;

import com.example.chatter.validation.ValidPayload;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ValidPayload
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "message_type")
    private MessageType messageType;

    @NotBlank
    private String payload;

    @Column(name = "created_at")
    private Date createdAt;
}
