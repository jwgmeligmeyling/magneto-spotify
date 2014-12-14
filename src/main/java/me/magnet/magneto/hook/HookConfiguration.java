package me.magnet.magneto.hook;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

@Data
@EqualsAndHashCode(callSuper=true)
public class HookConfiguration extends Configuration {
	
	@NotEmpty
	private String shairportEndpoint;
	
	@NotEmpty
	private String hipchatToken;
	
}
