import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

public class CountriesTaskRunner {
	private static final int MIN_POPULATION = 10000000;

	public static void main(String[] args) throws IOException, ParseException {
		Map<String, Integer> countriesPopulation = new HashMap<String, Integer>();
		Countries.readFromUrl("http://restcountries.eu/rest/v1", countriesPopulation);
		Countries.printCountriesWithPopulationAbove(CountriesTaskRunner.MIN_POPULATION, countriesPopulation);
	}
}