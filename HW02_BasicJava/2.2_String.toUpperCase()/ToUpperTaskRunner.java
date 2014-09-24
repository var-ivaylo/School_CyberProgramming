import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ToUpperTaskRunner {
	private static final int LAST_UPPER_CHAR_ASCII = 90;
	private static final int LAST_LOWER_CHAR_ASCII = 122;

	private static BufferedReader reader;

	public static void main(String[] args) throws IOException {
		final InputStream inputSource = System.in;
		final InputStreamReader inputReader = new InputStreamReader(inputSource);
		ToUpperTaskRunner.reader = new BufferedReader(inputReader);

		String newLine;
		while (!(newLine = reader.readLine()).equals("exit")) {
			System.out.println(newLine.toUpperCase());
			// System.out.println(customToUpperCase(newLine));
		}
	}

	private static String customToUpperCase(String str) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			int currentCharAscii = (int) currentChar;
			if (currentCharAscii <= ToUpperTaskRunner.LAST_UPPER_CHAR_ASCII || currentCharAscii > ToUpperTaskRunner.LAST_LOWER_CHAR_ASCII) {
				result.append(currentChar);
			} else {
				result.append((char) (currentCharAscii - 32));
			}
		}

		return result.toString();
	}
}