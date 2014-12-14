package me.magnet.magneto.hook.jaxrs;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Sender {
	
	@NotNull
	private Integer id;
	
	@NotNull
	private Sender.Links links;
	
	@NotNull
	private String mention_name;
	
	@NotNull
	private String name;
	
	@Data
	@Accessors(chain=true)
	public static class Links {
		
		@NotNull
		private String self;
	}
	
}