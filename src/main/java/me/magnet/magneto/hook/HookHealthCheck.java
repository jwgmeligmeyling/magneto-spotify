package me.magnet.magneto.hook;

import com.codahale.metrics.health.HealthCheck;

public class HookHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
