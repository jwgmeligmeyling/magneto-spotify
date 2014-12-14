package me.magnet.magneto.hook.jaxrs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoomTopicChange extends BaseEvent {

}
