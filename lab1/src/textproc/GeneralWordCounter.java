package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    Set<String> wordSet;
    HashMap<String ,Integer> map;
    public GeneralWordCounter(Set<String> wordSet){
        this.wordSet = wordSet;
        map = new HashMap<>();
    }



    @Override
    public void process(String w) {
        if(!wordSet.contains(w)){
            int n = 0;
            if(map.containsKey(w)){
                n = map.get(w);
                n++;
                map.put(w,n);
            }else{
                map.put(w,n);
            }
        }
    }

    @Override
    public void report() {
//        for (Map.Entry<String,Integer> entry: map.entrySet()){
//            if(entry.getValue() > 200){
//                System.out.println(entry.getKey() + " " + entry.getValue());
//            }
//        }

        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        wordList.sort(new WordCountComparator());
        for(int i = 0; i < 15; i++){
            System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
        }
    }
}
