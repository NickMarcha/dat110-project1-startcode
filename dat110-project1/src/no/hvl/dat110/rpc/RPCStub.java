package no.hvl.dat110.rpc;

// client-side stubs (local objects) must extend this class

public abstract class RPCStub {

	protected RPCClient rpcclient;
	
	public void register(RPCClient rmiclient) {
		this.rpcclient = rmiclient;
	}
	
}
