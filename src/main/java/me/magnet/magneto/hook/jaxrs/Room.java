package me.magnet.magneto.hook.jaxrs;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Room {
	
	@NotNull
	private Integer id;
	
	@NotNull
	private Room.Links links;
	
	@NotNull
	private String name;
	
	@Data
	@Accessors(chain=true)
	public static class Links {
		
		@Nullable
		private String members;
		
		@NotNull
		private String participants;
		
		@NotNull
		private String self;
		
		@NotNull
		private String webhooks;
		
	}		
}