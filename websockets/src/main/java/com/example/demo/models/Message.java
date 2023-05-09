package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    @Id
    private String id;
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;

}
