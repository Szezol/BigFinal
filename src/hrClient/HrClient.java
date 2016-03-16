package hrClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HrClient
{
	HrClient(String hostName, int portNumber, String searchCriteria, SearchType searchType)
	{
		super();
		Socket socket;
		try
		{
			socket = new Socket(hostName, portNumber);
			send(searchCriteria, searchType, socket);
			get(socket);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void send(String string, SearchType searchType, Socket socket)
	{
		int i = 1;
		try
		{
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.write(i);
			oos.writeObject(string);
			oos.writeObject(searchType);
			oos.close();
			os.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void get(Socket socket)
	{
		try
		{
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			int i;
			while ((i = ois.read()) > -1)
			{
				Object object = ois.readObject();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		HrClient hrc = new HrClient("localhost", 1234, "java,sql", SearchType.MANDATORY);

	}
}
