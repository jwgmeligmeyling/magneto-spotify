package me.magnet.magneto.hook.jaxrs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoomNotification extends BaseEvent {
	
}
