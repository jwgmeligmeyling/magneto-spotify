package me.magnet.magneto.hook.jaxrs;

import java.util.List;

import lombok.Data;

@Data
public class Message {
	
	private String date;
	
	private Sender from;
	
	private String id;
	
	private List<Sender> mentions;
	
	private String message;
	
	private String type;
	
}