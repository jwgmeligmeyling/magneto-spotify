package me.magnet.magneto.hook;

import retrofit.RestAdapter;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;

import me.magnet.magneto.hipchat.HipChatApi;
import me.magnet.magneto.hipchat.HipChatModule;
import me.magnet.magneto.shairport.ShairportResource;
import me.magnet.magneto.shairport.ShairportService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

@Slf4j
public class HookApplication extends Application<HookConfiguration> {
	
	public static void main(String[] args) throws Exception {
        new HookApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HookConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HookConfiguration configuration,
                    Environment environment) {
    	log.info("Initializing with configuration : {}", configuration);
    	
    	RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint(configuration.getShairportEndpoint())
			.build();
    	ShairportService shairport = restAdapter.create(ShairportService.class);
	
    	Injector injector = Guice.createInjector(new HipChatModule(configuration));
    	HipChatApi hipchat = injector.getInstance(HipChatApi.class);

        ShairportResource hookResource = new ShairportResource(hipchat, shairport);
        environment.jersey().register(hookResource);
        environment.healthChecks().register("default", new HookHealthCheck());
    }

}
