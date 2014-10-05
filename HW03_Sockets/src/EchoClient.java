import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	private static final String SERVER = "localhost";
	private final String request;

	public EchoClient(String request) {
		 this.request = request;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Enter date (yyyy-MM-dd): ");
		
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String request = reader.readLine();
		
		final EchoClient echoClient = new EchoClient(request);
		final String response = echoClient.send();
		
		System.out.println("Days between the two dates: " + response);
	}

	private String send() throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(SERVER, EchoServer.SERVER_PORT);
		//get I/O streams
		final InputStream inputStream = clientSocket .getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final InputStreamReader inputStreamReader =
			new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		
		final PrintWriter out = new PrintWriter(outputStream);
		
		// write to socket what we have red (this is echo server)
		out.println(request);
		// always flush writer
		out.flush();
		
		// read from socket
		final String result = in.readLine();
		
		
		// we should ALWAYS close sockets!
		clientSocket.close();
		
		return result;
	}

}