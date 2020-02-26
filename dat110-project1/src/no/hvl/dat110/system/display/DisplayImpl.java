package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		int newSecond = (int) (System.currentTimeMillis()/10000);
		if(newSecond != lastTenSecond) {
			lastTenSecond = newSecond;
			System.out.println();
			System.out.print("DISPLAY:" + message);
		} else {
			System.out.print(", " + message);
		}
		
	}
	
	int lastTenSecond = 0;
	public byte[] invoke(byte[] request) {
		
		String temp =  RPCUtils.unmarshallString(request);
		
		
		byte rpcid = request[0];
		byte[] reply = RPCUtils.marshallVoid(rpcid);
		write( ""+temp);
		
		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done int he SensorImpl for the read method
		return reply;
	}
}
