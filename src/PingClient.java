import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Server to process ping requests over UDP.
 */

public class PingClient{

	public static void main(String[] args) throws Exception {
		
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("hostname");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
		
	}

/* 
 * Print ping data to the standard output stream.
 */
	private static void printData(DatagramPacket request) throws Exception
	{
		// Obtain references to the packet's array of bytes.
		byte[] buf = request.getData();
		
		// Wrap the bytes in a byte array input stream,
		// so that you can read the data as a stream of bytes.
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		
		// Wrap the byte array output stream in an input stream reader,
		// so you can read the data as a stream of characters.
		InputStreamReader isr = new InputStreamReader(bais);
		
		// Wrap the input stream reader in a bufferred reader,
		// so you can read the character data a line at a time.
		// (A line is a sequence of chars terminated by any combination of \r and \n.) 
		BufferedReader br = new BufferedReader(isr);
		
		// The message data is contained in a single line, so read this line.
		String line = br.readLine();
		
		// Print host address and data received from it.
		System.out.println(
		"Received from " +
		request.getAddress().getHostAddress() +  ": " + new String(line) );
	}
}