package me.magnet.magneto.hook.jaxrs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class BaseEvent {

	@JsonProperty("oauth_client_id")
	private String oauth_client_id;
	
	@JsonProperty("webhook_id")
	private Integer webhook_id;
	
	@JsonProperty("event")
	private String event;
	
}
