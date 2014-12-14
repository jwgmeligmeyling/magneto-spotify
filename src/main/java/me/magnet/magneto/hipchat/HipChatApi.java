package me.magnet.magneto.hipchat;

public interface HipChatApi {
	/**
	 * @param notification Send a given notification.
	 * @param jabberId The room you want to send it to identified by its Jabber ID.
	 */
	void send(HipChatNotification notification, String jabberId);

	/**
	 * this function should be called when a room is added or removed.
	 */
	void refreshRoomIds();
}
