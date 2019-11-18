package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** D5 implement MultiWordCounter Class**/
public class MultiWordCounter implements TextProcessor {

    private Map<String,SingleWordCounter> map;

    public MultiWordCounter(String [] words){
        map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i],new SingleWordCounter(words[i]));// SingleWordCounter 클래스를 갖는 HashMap 초기
        }
    }

    /** Key is finding word and Value is SingleWordCounter object **/
    @Override
    public void process(String w) {
        for(String key : map.keySet()){
            map.get(key).process(w); // 프로세스 메서드를 호출해서 찾는 단어의 카운트를 증가시킴.
        }
    }

    @Override
    public void report() {
        for (String key : map.keySet()){
            map.get(key).report(); // 리포트 메서드를 호출해서 찾는 단어의 카운트를 프린트함.
        }
    }
}
