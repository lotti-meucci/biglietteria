package lotti.biglietteria.client;
import java.net.*;
import java.io.*;

public class Client
{
	private final Socket socket;
	private final DataInput in;
	private final DataOutput out;

	public Client(String serverAddress, int port)
	{
		try
		{
			socket = new Socket(serverAddress, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}

	public Socket getSocket()
	{
		return socket;
	}

	public Message send(Message message)
	{
		try
		{
			out.writeBytes(Utils.toJSON(message) + '\0');
			return Utils.fromJSON(Utils.readNullTerminated(in));
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
