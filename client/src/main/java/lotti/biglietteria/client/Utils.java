package lotti.biglietteria.client;
import java.io.*;
import com.fasterxml.jackson.databind.*;

public final class Utils
{
	private static final ObjectMapper json = new ObjectMapper();
	private Utils() { }

	// Legge una striga che termina con il carattere '\0'.
	public static String readNullTerminated(DataInput in)
	{
		try
		{
			var string = "";

			for (char c = in.readChar(); c != '\0'; c = in.readChar())
				string += c;

			return string;
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}

	public static Message fromJSON(String message)
	{
		try
		{
			return json.readValue(message, Message.class);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}

	public static String toJSON(Message message)
	{
		try
		{
			return json.writeValueAsString(message);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}
}
