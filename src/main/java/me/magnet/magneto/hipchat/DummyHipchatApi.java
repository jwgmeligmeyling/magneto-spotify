package me.magnet.magneto.hipchat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DummyHipChatApi implements HipChatApi {

	@Override
	public void send(HipChatNotification notification, String jabberId) {
		DummyHipChatApi.log.info(
		  "Cannot send message {}. HipChat API is not configured. Did you set the key in the properties file?",
		  notification.getMessage());

	}

	@Override
	public void refreshRoomIds() {
		log.debug("Fake refresing rooms");
	}
}
