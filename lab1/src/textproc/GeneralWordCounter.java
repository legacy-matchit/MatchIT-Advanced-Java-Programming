package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private boolean useTreeMap;
    private Set<String> stopWordSet;
    private Map<String ,Integer> map;

    public GeneralWordCounter(Set<String> stopWordSet, boolean useTreeMap){
        this.stopWordSet = stopWordSet;
        this.useTreeMap = useTreeMap;
        if(useTreeMap){
            map = new TreeMap<>();
        }else{
            map = new HashMap<>();
        }
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

        entryList.sort(new WordCountComparator());

        for(int i = 0; i < 5; i++){
            System.out.println(entryList.get(i).getKey() + " " + entryList.get(i).getValue());
        }
    }
}
