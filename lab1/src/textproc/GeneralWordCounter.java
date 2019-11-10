package textproc;

import java.util.HashMap;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

    Set<String> wordSet;
    HashMap<String ,Integer> map;
    public GeneralWordCounter(Set<String> wordSet){
        this.wordSet = wordSet;
        map = new HashMap<>();
    }

    @Override
    public void process(String w) {
        for (String s: wordSet) {
            if(s.equals(w)){
                int nbr = 0;
                if(map.containsKey(w)){
                    nbr = map.get(w);
                }
                nbr++;
                map.put(w,nbr);
            }
        }
    }

    @Override
    public void report() {
        for (String key : map.keySet()){
            System.out.println(key + "  " + map.get(key));
        }
    }
}
