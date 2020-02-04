package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {

		// implement marshalling, call and unmarshalling for write RPC method

		 byte[] sendBytes = RPCUtils.marshallString(RPCID, message);
		 
		 rpcclient.call(sendBytes);
	}
}
