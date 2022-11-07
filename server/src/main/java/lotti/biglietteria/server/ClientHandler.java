package lotti.biglietteria.server;
import java.io.*;
import java.net.*;

public final class ClientHandler implements Runnable
{
	private String state = "new";
	private final Server server;
	private final Socket client;
	private final DataInput in;
	private final DataOutput out;

	public ClientHandler(Server server, Socket client)
	{
		if (server == null || client == null)
			throw new IllegalArgumentException();

		try
		{
			this.server = server;
			this.client = client;
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}

	public Server getServer()
	{
		return server;
	}

	public Socket getClient()
	{
		return client;
	}

	@Override
	public void run()
	{
		var response = new Message();
		var message = Utils.fromJSON(Utils.readNullTerminated(in));

		switch (state)
		{
			case "new":
				if (message.getTickets().isEmpty())
					response.getTickets().addAll(server.getTickets());

				state = "buying";
				break;

			case "buying":
				for (var ticket : message.getTickets())
					if (server.getTickets().remove(ticket))
						response.getTickets().add(ticket);

				state = null;
				break;

			default:
				try
				{
					client.close();
				}
				catch (Exception e) { }
				return;
		}

		try
		{
			out.writeBytes(Utils.toJSON(response) + '\0');
		}
		catch (Exception e) { }

		run();
	}
}
