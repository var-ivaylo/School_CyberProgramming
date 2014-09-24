import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class Countries {
	public static void readFromUrl(String url, Map<String, Integer> countriesPopulation) throws IOException, ParseException {
		JSONArray jsonCountries = Countries.readJsonCountries(url);
		
		for (int i = 0; i < jsonCountries.size(); i++) {
			JSONObject country = (JSONObject) jsonCountries.get(i);
			String countryName = country.get("name").toString();
			int countryPopulation = Integer.parseInt(country.get("population").toString());

			countriesPopulation.put(countryName, countryPopulation);
		}
	}

	public static void printCountriesWithPopulationAbove(int population, Map<String, Integer> countriesPopulation) {
		System.out.printf("Countries with population above %d:\n\n", population);
		
		for (Entry<String, Integer> country : countriesPopulation.entrySet()) {
			if (country.getValue().intValue() > population) {
				System.out.printf("%s - %d\n", country.getKey(), country.getValue());
			}
		}
	}

	private static JSONArray readJsonCountries(String url) throws IOException, ParseException {
		InputStream stream = new URL(url).openStream();
		JSONParser parser = new JSONParser();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
			String jsonCountries = Countries.readAll(reader);
			JSONArray result = (JSONArray) parser.parse(jsonCountries);
			
			return result;
		} finally {
			stream.close();
		}
	}

	private static String readAll(Reader reader) throws IOException {
		StringBuilder result = new StringBuilder();
		int readChar;
		
		while ((readChar = reader.read()) != -1) {
			result.append((char) readChar);
		}

		return result.toString();
	}
}