import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServer {

	public static final int SERVER_PORT = 44012;
	
	// if static - ClientHandler will not have access to EchoServer.started
	// so server can be stopped if "END" command is implemented in client handler
	
	// extends Thread - create class that can be spawned in new thread
	private /*static*/ class ClientHandler extends Thread {
		private final Socket clientSocket;
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				handleClient(clientSocket);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		private void handleClient(final Socket clientSocket) throws IOException, ParseException {
			//get I/O streams
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			final InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			
			final PrintWriter out = new PrintWriter(outputStream);
			
			// read from socket
			final String readLine = in.readLine();
			
			Date date = parseDate(readLine);
			int days = daysBetween(new Date(), date);
			
			// write to socket what we have red (this is echo server)
			out.println(days);
			
			// always flush writer
			out.flush();
			
			// we should ALWAYS close sockets!
			clientSocket.close();
		}
		
	}
	
	private boolean started;

	public static void main(String[] args) throws IOException {
		new EchoServer().run();
	}
	
	public int daysBetween(Date d1, Date d2) {
		System.out.println(d2.after(d1));
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public Date parseDate(String readLine) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = formatter.parse(readLine);
		
		return parsedDate;
	}


	public void run() throws IOException {
		started = true;

		// bind to port
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		while (started) {
			// accept client
			final Socket clientSocket = serverSocket.accept();

			//Thread.start() creates new thread and calls run() method
			new ClientHandler(clientSocket).start();
		}
		
		// we should ALWAYS close sockets!
		serverSocket.close();
	}


}
