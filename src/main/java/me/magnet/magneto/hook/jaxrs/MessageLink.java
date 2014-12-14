package me.magnet.magneto.hook.jaxrs;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class MessageLink {
	
	private Image image;
	private Link link;
	
	@JsonProperty("twitter_status")
	private TwitterStatus twitterStatus;
	
	@JsonProperty("twitter_user")
	private TwitterUser twitter_user;
	
	private String type;
	
	private String url;
	
	private Video video;
	
}