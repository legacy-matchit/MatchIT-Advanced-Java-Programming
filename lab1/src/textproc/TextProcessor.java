package textproc;

/**
 * Beskriver metoder för att behandla en sekvens av ord (som en bok), och därefter
 * presentera ett resultat med statistik av något slag.
 */
public interface TextProcessor {

	/**
	 * Called when a word is read.
	 * The method will then update the statistics.
	 */
	void process(String w);

	/**
	 * Method called when all the words in the sequence are read.
	 * The method should print a readable representation of the statistics.
	 */
	void report();
}
