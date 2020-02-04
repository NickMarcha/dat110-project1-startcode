package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(UnboundedMessage message) {

		// encapsulate the data contained in the message and write to the output stream
		// Hint: use the encapsulate method on the message
		
		try {
			outStream.write(message.encapsulate());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public UnboundedMessage receive() {
		// read a segment (128 bytes) from the input stream and decapsulate into message
				// Hint: create a new Message object and use the decapsulate method
		UnboundedMessage message = new UnboundedMessage();
		
		try {
			byte[] recvbuflength = inStream.readNBytes(4);
			
			int messageLength = UnboundedMessage.UnmarshallInt(recvbuflength);
			//System.out.println("ML:" + messageLength);
		
			byte[] recvbuf = inStream.readNBytes(messageLength);
			
			byte[] combinedRecBuff = new byte[messageLength + 4];
			
			System.arraycopy(recvbuflength, 0, combinedRecBuff, 0, recvbuflength.length); 
			System.arraycopy(recvbuf, 0, combinedRecBuff, recvbuflength.length, recvbuf.length);
			
			
			message.decapsulate(combinedRecBuff);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}