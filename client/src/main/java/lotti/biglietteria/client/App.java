package lotti.biglietteria.client;
import java.util.*;

public class App
{
	public static void main(String[] args)
	{
		var scanner = new Scanner(System.in);
		var client = new Client("127.0.0.1", 60005);
		var response = client.send(new Message());

		for (var ticket : response.getTickets())
		{
			System.out.println(ticket);
			System.out.println("----------");
		}

		System.out.print("Inserisci ID biglietti (separati da virgola): ");
		var ids = scanner.nextLine().split(",");
		var ticketsToBuy = new Message();

		for (String id : ids)
		{
			int n = Integer.parseInt(id);

			for (var ticket : response.getTickets())
				if (ticket.getID() == n)
					ticketsToBuy.getTickets().add(ticket);
		}

		response = client.send(ticketsToBuy);
		System.out.println();
		System.out.println("BIGLIETTI ACQUISTATI:");
		System.out.println("----------");

		for (var ticket : response.getTickets())
		{
			System.out.println(ticket);
			System.out.println("----------");
		}

		client.send(new Message());
		scanner.close();

		try
		{
			client.getSocket().close();
		}
		catch (Exception e) { }
	}
}
