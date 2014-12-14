package me.magnet.magneto.hook.jaxrs;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoomMessage extends BaseEvent {
	
	private Item item;
	
	@Data
	public static class Item {
		
		@NotNull
		private Message message;
		
		private Room room;
		
	}
	
}
