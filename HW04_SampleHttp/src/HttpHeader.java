public class HttpHeader {
	private String name;
	private String value;

	public HttpHeader(String name, String value) {
		this.setName(name);
		this.setValue(value);
	}

	public HttpHeader(String headerLine) {
		String[] splitHeader = headerLine.split(": ", 2);

		if (splitHeader.length != 2) {
			throw new IllegalArgumentException("Bad header line " + headerLine);
		}

		this.setName(splitHeader[0]);
		this.setValue(splitHeader[1]);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Header name cannot be null");
		}
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Header value cannot be null");
		}
		this.value = value;
	}
}
