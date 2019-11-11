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

		long t0 = System.nanoTime();

        /** D4 list of TextProcessor for finding each word **/
		ArrayList<TextProcessor> textProcessors = new ArrayList<>(); // 배열에 TextProcessor 인터페이스 타입의 요소를 담는다
		textProcessors.add(new SingleWordCounter("nils")); // 찾을 단어 초기후에 배열에 담는다.
		textProcessors.add(new SingleWordCounter("norge")); // 찾을 단어 초기후에 배열에 담는다.

		/**D6 **/
		TextProcessor multiCounter = new MultiWordCounter(Holgersson.REGIONS);

		/**D7 load from file for don't want to find words **/
		Scanner scanner = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<>();
		while (scanner.hasNext()){
			String word = scanner.next();
			stopwords.add(word);
		}
		scanner.close();
		TextProcessor r = new GeneralWordCounter(stopwords,true);



		/** nilsholg.txt파일 로드후 소문자로 변환 한 단어를 인자로 넘기는 부분 특수문자는 제외함 (정규식표현) **/
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning



		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor p:textProcessors){
				p.process(word); // D4
			}

			multiCounter.process(word); //D6

			r.process(word); // D8
		}

		s.close();


		for(TextProcessor p:textProcessors){
			p.report(); /** D4 result **/
		}

		System.out.println("----------");
		multiCounter.report(); /** D6 result **/

		System.out.println("----------");
		r.report(); /** D8 result **/


		System.out.println("----------");
		long t1 = System.nanoTime();
		System.out.println("Time Map: " + (t1 - t0) / 1000000.0 + " ms");

	}

}