package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

		/** list of TextProcessor for finding each word **/
		ArrayList<TextProcessor> textProcessors = new ArrayList<>();
		textProcessors.add(new SingleWordCounter("nils"));
		textProcessors.add(new SingleWordCounter("norge"));

		/****/
		TextProcessor multiCounter = new MultiWordCounter(Holgersson.REGIONS);

		/**D7**/
		Scanner scanner = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<>();
		while (scanner.hasNext()){
			String word = scanner.next();
			stopwords.add(word);
		}
		scanner.close();
		TextProcessor r = new GeneralWordCounter(stopwords);

		/****/
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor p:textProcessors){
				p.process(word);
			}
			multiCounter.process(word);
			r.process(word);
		}

		s.close();

		for(TextProcessor p:textProcessors){
			p.report(); /** D4 result **/
		}

		System.out.println("----------");
		multiCounter.report(); /** D6 result **/

		System.out.println("----------");
		r.report(); /** D8 result **/

	}
}