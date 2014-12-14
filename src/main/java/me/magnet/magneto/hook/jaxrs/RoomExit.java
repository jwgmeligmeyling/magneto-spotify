package me.magnet.magneto.hook.jaxrs;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoomExit extends BaseEvent {
	
	@NotNull
	private Item item;
	
	@Data
	public static class Item {
		
		@NotNull
		private Room room;
		
		@NotNull
		private Sender sender;
		
	}
	
}
