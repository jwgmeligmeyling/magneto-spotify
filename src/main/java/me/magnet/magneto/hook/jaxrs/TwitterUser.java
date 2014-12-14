package me.magnet.magneto.hook.jaxrs;

import lombok.Data;

@Data
public class TwitterUser {
	private Integer followers;
	private String name;
	private String profileImageUrl;
	private String screenName;
}