package me.magnet.magneto.hook.jaxrs;

import lombok.Data;

@Data
public class File {
	
	private String name;
	
	private Integer size;
	
	private String thumb_url;
		
	private String url;
	
}