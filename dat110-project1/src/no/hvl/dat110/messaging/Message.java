package no.hvl.dat110.messaging;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Deprecated
public class Message {

	private byte[] payload;

	public Message(byte[] payload) {

		if(payload.length > 128) {
			throw new InvalidParameterException("Payload to long");
		}
		this.payload = payload; // 
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {

		byte[] encoded = new byte[128];
 
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format

		encoded[0] = (byte) payload.length;
		System.arraycopy(payload, 0, encoded, 1, payload.length);

		return encoded;

	}

	public void decapsulate(byte[] received) {

		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		payload = Arrays.copyOfRange(received, 1, received[0]+1);
	}
}
