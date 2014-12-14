package me.magnet.magneto.shairport;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.inject.ImplementedBy;

@ImplementedBy(CoverApiImpl.class)
public interface CoverApi {
	
	String getCover(String path) throws ClientProtocolException, IOException;
	
}
