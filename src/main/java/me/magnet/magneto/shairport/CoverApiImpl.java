package me.magnet.magneto.shairport;

import java.io.IOException;
import java.io.InputStream;

import me.magnet.magneto.hook.HookConfiguration;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class CoverApiImpl implements CoverApi {

	private final HookConfiguration configuration;
	
	public CoverApiImpl(HookConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String getCover(String path) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(configuration.getShairportEndpoint());
		HttpResponse response = client.execute(request);
		try(InputStream in = response.getEntity().getContent()) {
			int i;
			while((i = in.read()) != -1) {
				System.out.print(i + " ");
				System.out.println();
			}
		}
		return "";
	}

}
