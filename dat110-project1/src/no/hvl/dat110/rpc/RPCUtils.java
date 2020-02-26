package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// marshall RPC identifier and string into byte array

		byte[] stringBytes = str.getBytes();
		
		encoded = new byte[stringBytes.length + 1];
		
		encoded[0] = rpcid;
		System.arraycopy(stringBytes, 0, encoded, 1, stringBytes.length);
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// unmarshall String contained in data into decoded

		byte[] stringBytes = Arrays.copyOfRange(data, 1, data.length);
		decoded = new String(stringBytes);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// marshall RPC identifier in case of void type
		encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		//  unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		byte[] intBytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(x).array();
		
		encoded = new byte[intBytes.length + 1];
		
		encoded[0] = rpcid;
		System.arraycopy(intBytes, 0, encoded, 1, intBytes.length);
		

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// unmarshall integer contained in data

		byte[] intBytes = Arrays.copyOfRange(data, 1, data.length);
		decoded = ByteBuffer.wrap(intBytes).order(ByteOrder.BIG_ENDIAN).getInt();

		return decoded;

	}
}
