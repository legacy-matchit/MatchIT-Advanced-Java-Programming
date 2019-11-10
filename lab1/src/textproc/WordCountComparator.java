package textproc;

import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map .Entry<String ,Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        int com = 0;
        if(o1.getValue() < o2.getValue()){
            com = 1;
        }else if(o1.getValue() > o2.getValue()){
            com = -1;
        }
        return com;
    }
}
