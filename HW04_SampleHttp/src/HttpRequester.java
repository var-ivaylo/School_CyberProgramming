import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequester {
	private static final String PROTOCOL_VERSION = "HTTP/1.1";

	public static void main(String[] args) throws UnknownHostException, IOException {
		HttpRequester requester = new HttpRequester();
		CharHttpResponse response = requester.createRequest("GET", "google.bg", "/");
		
		System.out.println(response.getStatusLine());
		for (HttpHeader header : response.getHeaders()) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
	}

	private CharHttpResponse createRequest(String method, String host, String path) throws UnknownHostException, IOException {
		Socket clientSocket = new Socket(host, 80);
		
		try {
			InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			
			BufferedReader in = new BufferedReader(inputStreamReader);
			PrintWriter out = new PrintWriter(outputStream);
			
			Pattern statusCodePattern = Pattern.compile("\\d{3}");		
			String statusLine = this.request(in, out, method, host, path);
			
			Matcher m = statusCodePattern.matcher(statusLine);
			if (m.find()) {
				if (m.group().equals("301")) {
					HttpHeader locationHeader = new HttpHeader(in.readLine());
					return this.createRequest(method, locationHeader.getValue().replace("http://", "").replace("/", ""), path);
				}
			}
			
			CharHttpResponse response = this.parseResponse(in, statusLine);
			
			in.close();
			out.close();
			
			return response;
		} finally {
			clientSocket.close();
		}
	}

	private String request(BufferedReader in, PrintWriter out, String method, String host, String path) throws IOException {
		this.writeRequest(out, method, host, path);
		out.flush();
		String statusLine = in.readLine();

		return statusLine;
	}

	private void writeRequest(PrintWriter printWriter, String method, String host, String path) {
		printWriter.printf("%s %s %s\n", method, path, HttpRequester.PROTOCOL_VERSION);
		printWriter.printf("Host: %s\n", host);
		printWriter.printf("\n");
	}

	private CharHttpResponse parseResponse(BufferedReader in, String statusLine) throws IOException {
		CharHttpResponse result = new CharHttpResponse();

		result.setStatusLine(statusLine);
		String header;
		while (!(header = in.readLine()).equals("")) {
			result.getHeaders().add(new HttpHeader(header));
		}

		in.read(result.getBody());

		return result;
	}
}
