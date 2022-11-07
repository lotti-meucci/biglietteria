package lotti.biglietteria.server;

public class App
{
	public static void main(String[] args)
	{
		var port = 60005;
		System.out.println("Listening on port " + port);

		new Server(port, new Ticket[]
		{
			new Ticket(1, "palco-1a"),
			new Ticket(2, "palco-2a"),
			new Ticket(3, "palco-1b"),
			new Ticket(4, "palco-2b"),
			new Ticket(5, "tribuna-1a"),
			new Ticket(6, "tribuna-2a"),
			new Ticket(7, "tribuna-1b"),
			new Ticket(8, "tribuna-2b"),
			new Ticket(9, "parterre-1a"),
			new Ticket(10, "parterre-2a"),
			new Ticket(11, "parterre-1b"),
			new Ticket(12, "partette-2b"),
		}).run();
	}
}
