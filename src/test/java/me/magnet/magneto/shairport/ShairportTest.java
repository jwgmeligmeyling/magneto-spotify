package me.magnet.magneto.shairport;

import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Slf4j
public class ShairportTest {
	
	private ShairportService service;

	@Before
	public void beforeTest() {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint("http://office.magnet.me/shairport/")
			.build();
		service = restAdapter.create(ShairportService.class);
	}
	
	@Test
	public void testShairport() throws IOException {
		NowPlaying nowPlaying = service.getNowPlaying();
		log.info("Now playing: {}", nowPlaying);
	}

}
