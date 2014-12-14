package me.magnet.magneto.shairport;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;
import me.magnet.magneto.hipchat.HipChatApi;
import me.magnet.magneto.hipchat.HipChatNotification;
import me.magnet.magneto.hook.jaxrs.RoomEnter;
import me.magnet.magneto.hook.jaxrs.RoomExit;
import me.magnet.magneto.hook.jaxrs.RoomMessage;
import me.magnet.magneto.hook.jaxrs.RoomNotification;
import me.magnet.magneto.hook.jaxrs.RoomTopicChange;

@Slf4j
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShairportResource {
	
	private final HipChatApi hipchat;
	private final ShairportService shairport;
	
	@Inject
	public ShairportResource(final HipChatApi hipchat, final ShairportService shairport) {
		this.hipchat = hipchat;
		this.shairport = shairport;
	}

    @GET
    public String sayHello() {
    	return "Hello world";
    }
    
    @POST
    @Path("/room_enter")
    public void receiveRoomEnter(@Valid RoomEnter roomEnter) {
    	log.info("Room enter : {}", roomEnter);
    }
    
    @POST
    @Path("/room_exit")
    public void receiveRoomExit(@Valid RoomExit roomExit) {
    	log.info("Room exit : {}", roomExit);
    }
    
    @POST
    @Path("/room_message")
    public void receiveRoomMessage(@Valid RoomMessage roomMessage) {
    	log.info("Room message : {}", roomMessage);
    	NowPlaying nowPlaying = shairport.getNowPlaying();
    	log.info("Now playing : {}", nowPlaying);
    	
    	String message = String.format("Now playing %s - %s", nowPlaying.getArtist(), nowPlaying.getTitle());
    	log.info("Sending message: {}", message);
    	HipChatNotification notification = new HipChatNotification(message);
    	hipchat.send(notification, "65597_hackaton_1314-12@conf.hipchat.com");
    }
    
    @POST
    @Path("/room_notification")
    public void receiveRoom(@Valid RoomNotification roomNotification) {
    	log.info("Room notification : {}", roomNotification);
    }
    
    @POST
    @Path("/room_topic_change")
    public void receiveRoomEnter(@Valid RoomTopicChange roomTopicChange) {
    	log.info("Room topic change : {}", roomTopicChange);
    }
	
}
