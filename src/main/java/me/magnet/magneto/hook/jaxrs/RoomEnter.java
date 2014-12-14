package me.magnet.magneto.hook.jaxrs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoomEnter extends BaseEvent {

	private Item item;
	
	@Data
	@Accessors(chain=true)
	public static class Item {
		
		private Room room;
		
		private Sender sender;
		
	}
	
	
}
