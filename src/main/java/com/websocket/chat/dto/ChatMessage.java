package com.websocket.chat.dto;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.websocket.chat.util.StringUtils;


import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Entity
@Table(name = "message")
public class ChatMessage implements Serializable{
   
	private static final long serialVersionUID = -1360166869052717912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="sender")
    private String from;
    private String text;
    private String recipient;
    private String time;
 
    public ChatMessage() {
        
    }
    
    public ChatMessage(String from, String text, String recipient) {
        this.from = from;
        this.text = text;
        this.recipient = recipient;
        this.time = StringUtils.getCurrentTimeStamp();
    }
}