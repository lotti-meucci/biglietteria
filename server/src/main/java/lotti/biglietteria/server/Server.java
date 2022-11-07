package lotti.biglietteria.server;
import java.net.*;
import java.util.*;

public final class Server implements Runnable
{
	private final ServerSocket port;
	private final ArrayList<Ticket> tickets = new ArrayList<>();

	public Server(int port, Ticket[] tickets)
	{
		try
		{
			this.port = new ServerSocket(port);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}

		if (tickets != null)
			for (var ticket : tickets)
				if (ticket != null)
					this.tickets.add(ticket);
	}

	public int getPort()
	{
		return port.getLocalPort();
	}

	public ArrayList<Ticket> getTickets()
	{
		return tickets;
	}

	@Override
	public void run()
	{
		for (;;)
		{
			try
			{
				var client = port.accept();
				new Thread(new ClientHandler(this, client)).start();
			}
			catch (Exception e)
			{
				throw new IllegalStateException();
			}
		}
	}
}
