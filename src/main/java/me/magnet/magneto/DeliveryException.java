package me.magnet.magneto;

public class DeliveryException extends RuntimeException {

	private static final long serialVersionUID = -6499394053512455145L;

	public DeliveryException(String message) {
		super("Could not send " + message);
	}

	public DeliveryException(String message, Exception cause) {
		super("Could not send " + message, cause);
	}
}
