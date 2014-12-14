package me.magnet.magneto.hipchat;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import me.magnet.magneto.DeliveryException;
import me.magnet.magneto.hook.HookConfiguration;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

@Singleton
@Slf4j
class HipChatApiImpl implements HipChatApi {

	static final String API_URL_PREFIX = "https://api.hipchat.com/v2/";

	private final Map<String, String> jabberToHipChatRoomId = Maps.newHashMap();
	private final String token;
	private final HttpClient client;
	private final ObjectMapper mapper;

	@Inject
	public HipChatApiImpl(HttpClient client, HookConfiguration configuration, ObjectMapper mapper) {
		this.client = client;
		this.mapper = mapper;
		this.token = configuration.getHipchatToken();
	}

	@Override
	public void refreshRoomIds() {
		log.debug("Refreshing the room ids.");
		synchronized (jabberToHipChatRoomId) {
			try {
				HttpGet get = setRequiredHeaders(new HttpGet(API_URL_PREFIX + "room"));
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					jabberToHipChatRoomId.clear();
					JsonNode jsonNode = mapper.readTree(response.getEntity().getContent());
					getRoomIds(jsonNode.findValue("items").findValuesAsText("self"));
				} else {
					logWrongStatus(response);
				}
			}
			catch (IOException e) {
				log.error("Could not update the room IDs", e);
			}
		}
	}

	private void getRoomIds(List<String> urls) throws IOException {
		for (String roomUrl : urls) {
			HttpResponse roomResponse = client.execute(setRequiredHeaders(new HttpGet(roomUrl)));
			if (roomResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				JsonNode tree = mapper.readTree(roomResponse.getEntity().getContent());
				String jabber = tree.get("xmpp_jid").asText();
				String hipChatId = tree.get("id").asText();
				jabberToHipChatRoomId.put(jabber, hipChatId);
			} else {
				logWrongStatus(roomResponse);
			}
		}
	}

	private void logWrongStatus(HttpResponse response) {
		log.error("Could not update room id because response was code {} with status {}",
		  response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
	}

	@Override
	public void send(HipChatNotification notification, String jabberId) {
		synchronized (jabberToHipChatRoomId) {
			if (!jabberToHipChatRoomId.containsKey(jabberId)) {
				refreshRoomIds();
				if (!jabberToHipChatRoomId.containsKey(jabberId)) {
					throw new DeliveryException("Could not find that room: " + jabberId);
				}
			}
			String url = String.format(HipChatNotification.API_URL_ROOM, jabberToHipChatRoomId.get(jabberId));

			log.debug("Sending {} to {}", notification, url);
			HttpPost post = setRequiredHeaders(new HttpPost(url));
			try {
				String postBody = mapper.writeValueAsString(notification);
				post.setEntity(new StringEntity(postBody, Charsets.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
					throw new DeliveryException(notification
					  + " received status code: " + response.getStatusLine().getStatusCode()
					  + " message: " + response.getStatusLine().getReasonPhrase());

				}
			}
			catch (IOException e) {
				throw new DeliveryException(notification.toString(), e);

			}
		}
	}

	private <T extends HttpRequestBase> T setRequiredHeaders(T post) {
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		post.setHeader(HttpHeaders.CONTENT_ENCODING, "UTF8");
		return post;
	}
}
