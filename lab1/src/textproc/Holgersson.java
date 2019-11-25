package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };



	public static void main(String[] args) throws FileNotFoundException {

	    //testSingleWordCounter();
	    testMultiWordCounter();
	    testGeneralWordCounter();

	}

	private static Scanner loadText() throws FileNotFoundException {
        /** nilsholg.txt파일 로드후 소문자로 변환 한 단어를 인자로 넘기는 부분 특수문자는 제외함 (정규식표현) **/
        Scanner scan = new Scanner(new File("nilsholg.txt"));
        scan.findWithinHorizon("\uFEFF", 1);
        scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
        return scan;
    }
	public static void testSingleWordCounter() throws FileNotFoundException {
        /** D4 list of TextProcessor for finding each word **/
        ArrayList<TextProcessor> textProcessors = new ArrayList<>(); // 배열에 TextProcessor 인터페이스 타입의 요소를 담는다
        textProcessors.add(new SingleWordCounter("nils")); // 찾을 단어 초기후에 배열에 담는다.
        textProcessors.add(new SingleWordCounter("norge")); // 찾을 단어 초기후에 배열에 담는다.
        Scanner scan = loadText();
        while (scan.hasNext()) {// D4
            String findWord = scan.next().toLowerCase();
            for(TextProcessor singleCounter:textProcessors){
                singleCounter.process(findWord);
            }
        }
        scan.close();
        for(TextProcessor singleCounter:textProcessors){
            singleCounter.report(); /** D4 result **/
        }
    }
    public static void testMultiWordCounter() throws FileNotFoundException{
        /**D6 **/
        TextProcessor multiCounter = new MultiWordCounter(Holgersson.REGIONS,true);
        Scanner scan = loadText();
        long t0 = System.nanoTime();
        while (scan.hasNext()) {//D6
            String findWord = scan.next().toLowerCase();
            multiCounter.process(findWord);
        }
        long t1 = System.nanoTime();
        scan.close();
        System.out.println("----------");
        System.out.println("Time Map: " + (t1 - t0) / 1000000.0 + " ms");
        System.out.println("----------");
        multiCounter.report(); /** D6 result **/
    }

    public static void testGeneralWordCounter() throws FileNotFoundException{
        /**D7 load from file for don't want to find words **/
        Scanner scan = new Scanner(new File("undantagsord.txt"));
        Set<String> stopwords = new HashSet<>();
        while (scan.hasNext()){
            String word = scan.next();
            stopwords.add(word);
        }
        scan.close();

        TextProcessor generalWordCounter = new GeneralWordCounter(stopwords,true);
        scan = loadText();
        long t0 = System.nanoTime();
        while (scan.hasNext()) {// D8
            String findWord = scan.next().toLowerCase();
            generalWordCounter.process(findWord);
        }
        long t1 = System.nanoTime();
        scan.close();
        System.out.println("----------");
        System.out.println("Time Map: " + (t1 - t0) / 1000000.0 + " ms");
        System.out.println("----------");
        generalWordCounter.report(); /** D8 result **/
    }

}