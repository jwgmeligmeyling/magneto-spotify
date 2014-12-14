package me.magnet.magneto.hook;

import io.dropwizard.testing.junit.ResourceTestRule;
import me.magnet.magneto.hook.jaxrs.RoomMessage;
import me.magnet.magneto.shairport.ShairportResource;

import org.junit.ClassRule;
import org.junit.Test;

public class HookResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ShairportResource(null, null))
            .build();
    
    @Test
    public void testGetPerson() {
    	RoomMessage roomMessage = new RoomMessage();
        resources.client().resource("/room_message").post(RoomMessage.class, roomMessage);
    }
}
