package no.hvl.dat110.rpc;

// server-side (remote objects) must implement this interface

public interface RPCImpl {
	
	public byte[] invoke(byte[] request);
	
}
