package lotti.biglietteria.client;

public final class Ticket
{
	private final int id;
	private final String location;

	public Ticket(int id, String location)
	{
		if (id < 0 || location == null || location.isEmpty())
			throw new IllegalArgumentException();

		this.id = id;
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

	@Override
	public String toString()
	{
		return "ID: " + getID() + "\nLocation: " + getLocation();
	}
}
