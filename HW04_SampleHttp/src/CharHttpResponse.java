import java.util.LinkedList;
import java.util.List;

public class CharHttpResponse {
	private static final int MAX_CONTENT_LENGTH = 1024 * 1024 * 10;

	private String statusLine;
	private List<HttpHeader> headers;
	private char[] body;

	public CharHttpResponse() {
		this.headers = new LinkedList<HttpHeader>();
	}

	public String getStatusLine() {
		return this.statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public List<HttpHeader> getHeaders() {
		return this.headers;
	}

	public char[] getBody() {
		if (this.body == null) {
			this.body = new char[this.getContentLength()];
		}
		return this.body;
	}

	private int getContentLength() {
		for (HttpHeader header : this.getHeaders()) {
			if (header.getName().toLowerCase().equals("content-length")) {
				int size = Integer.parseInt(header.getValue());
				return Math.min(size, CharHttpResponse.MAX_CONTENT_LENGTH);
			}
		}

		return 0;
	}
}
