package no.hvl.dat110.messaging;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class UnboundedMessage {

	private byte[] payload;


	public static int UnmarshallInt(byte[] intBytes) {
		return ByteBuffer.wrap(intBytes).order(ByteOrder.BIG_ENDIAN).getInt();
	}
	public UnboundedMessage(byte[] payload) {
		this.payload = payload; // 
	}

	public UnboundedMessage() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {

		int messageLength = payload.length;
		byte[] encoded = new byte[messageLength + 4];

		byte[] intBytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(messageLength).array();
		
		System.arraycopy(intBytes, 0, encoded, 0, intBytes.length); 
		System.arraycopy(payload, 0, encoded, intBytes.length, payload.length);

		return encoded;
	}

	public void decapsulate(byte[] received) {
		if(received.length < 4) {
			throw new IllegalArgumentException("0:Wrong Format!");
		}
		
		byte[] intBytes = Arrays.copyOf(received, 4);

		int length = ByteBuffer.wrap(intBytes).order(ByteOrder.BIG_ENDIAN).getInt();
		
		if(length + 4 != received.length) {
			System.out.println("Expected length |"+ (length + 4) + "| got |"+received.length+"|");
			throw new IllegalArgumentException("1:Wrong Format!");
		}
		
		payload = new byte[length];
		
		if(length != 0) {
			System.arraycopy(received,4, payload,0,length);
			//payload = Arrays.copyOfRange(received, 4, length);
		}
	}
}
