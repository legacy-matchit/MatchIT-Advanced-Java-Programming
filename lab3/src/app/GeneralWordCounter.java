package app;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private Set<String> stopWordSet;
    private Map<String ,Integer> map;

    public GeneralWordCounter(Set<String> stopWordSet){
        this.stopWordSet = stopWordSet;
        map = new HashMap<>();
    }

    public Set<Map.Entry<String, Integer>> getWords() {
        return map.entrySet();
    }

    public Set<Map.Entry<String, Integer>> orderByKey(){
        return new TreeMap<>(map).entrySet();
    }

    public List<Map.Entry<String, Integer>> orderByValue(){
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1,o2)->o2.getValue()-o1.getValue());
        return entryList;
    }

    @Override
    public void process(String w) {
        if(!stopWordSet.contains(w)){
            if(map.containsKey(w)){
                int n = map.get(w);
                n++;
                map.put(w,n);
            }else{ // 초기
                map.put(w,1);
            }
        }
    }

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(entrySet);
        entryList.sort((o1,o2)->o2.getValue()-o1.getValue());
        for(int i = 0; i < 5; i++){
            System.out.println(entryList.get(i).getKey() + " " + entryList.get(i).getValue());
        }
    }
}
