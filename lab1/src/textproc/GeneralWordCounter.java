package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {

    private boolean useTreeMap;
    private Set<String> wordSet;
    private HashMap<String ,Integer> hashMap;
    private TreeMap<String ,Integer> treeMap;

    public GeneralWordCounter(Set<String> wordSet, boolean useTreeMap){
        this.wordSet = wordSet;
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();
        this.useTreeMap = useTreeMap;
    }

    private void useTreeMap(String w){
        if(!wordSet.contains(w)){
            int n = 0;
            if(treeMap.containsKey(w)){
                n = treeMap.get(w);
                n++;
                treeMap.put(w,n);
            }else {
                treeMap.put(w,n);
            }
        }
    }

    @Override
    public void process(String w) {
        if(useTreeMap){
            useTreeMap(w);
            return;
        }
        if(!wordSet.contains(w)){
            int n = 0;
            if(hashMap.containsKey(w)){
                n = hashMap.get(w);
                n++;
                hashMap.put(w,n);
            }else{ //
                hashMap.put(w,n);
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

        Set<Map.Entry<String, Integer>> wordSet;

        if(useTreeMap){
            wordSet = treeMap.entrySet();
        }else{
            wordSet = hashMap.entrySet();
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        wordList.sort(new WordCountComparator());

        for(int i = 0; i < 10; i++){
            System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
        }
    }
}
