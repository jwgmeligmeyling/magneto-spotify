package me.magnet.magneto.hook.jaxrs;

import lombok.Data;

@Data
public class TwitterStatus {
	private String created;
	private String name;
	private String profileImageUrl;
	private String screenName;
	private String source;
	private String text;
}