package me.magnet.magneto.shairport;

import retrofit.http.GET;

public interface ShairportService {

	@GET("/now_playing.json")
	NowPlaying getNowPlaying();
	
}
