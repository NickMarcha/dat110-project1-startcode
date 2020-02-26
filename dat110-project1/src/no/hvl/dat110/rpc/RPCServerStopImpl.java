package no.hvl.dat110.rpc;

public class RPCServerStopImpl implements RPCImpl {

	// server-side (remote) implementation of the built-in stop RPC method
	public byte[] invoke(byte[] request) {
		
		RPCUtils.unmarshallVoid(request);
		
		byte[] reply = RPCUtils.marshallVoid(RPCCommon.RPIDSTOP); 
		
		stop(); 
		
		return reply;
	}
	
	public void stop() {
		
		System.out.println("RPC server executing stop");
		
	}
}
