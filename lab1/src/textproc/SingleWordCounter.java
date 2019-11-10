package textproc;


/**
 * 한개의 단어를 찾는 프로그램
 * TextProcessor 인터페이스를 구현 process, report 메서드를 필수 구현
 * 로직 : 카운터 할 단어를 생성자로부터 초기화
 * process : 메서드 인자로 전달되어오는 단어와 비교해서 같으면 카운터 수를 증가시킴
 * report : 찾을 단어 word를 얼마나 증가 되었는지 증가된 변수 n을 출력
 */

public class SingleWordCounter implements TextProcessor {

	// == Field == //
	private String word; // 찾을 단어
	private int n; // 카운터 할 변수

	public SingleWordCounter(String word) {
		this.word = word;
		n = 0;
	}

	/** find single word how many times mentioned this word **/
	public void process(String w) {
		if (w.equals(word)) { /** D2 change "==" to "equals()"*/
			n++;
		}
	}

	/** print the word and number of mentioned in the text **/
	public void report() {
		System.out.println(word + ": " + n);
	}

}
