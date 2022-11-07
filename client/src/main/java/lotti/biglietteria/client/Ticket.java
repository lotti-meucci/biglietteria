package lotti.biglietteria.client;

public final class Ticket
{
	// Rappresenta l'id della prossima istanza.
	// Viene incrementato di 1 per ogni nuova istanza.
	// Il valore iniziale equivale a 1.
	private static int nextID = 1;

	private final int id;
	private final String location;

	public Ticket(String location)
	{
		if (location == null)
			throw new IllegalArgumentException();

		id = nextID++;
		this.location = location;
	}

	public int getID()
	{
		return id;
	}

	public String getLocation()
	{
		return location;
	}
}
