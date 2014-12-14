package me.magnet.magneto.hipchat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HipChatNotification {

	static final String API_URL_ROOM = HipChatApiImpl.API_URL_PREFIX + "room/%s/notification";
	private final String message;
	private Color color = Color.GRAY;
	private boolean notify = false;

	public static enum Color {
		YELLOW, RED, GREEN, PURPLE, GRAY, RANDOM;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
}
