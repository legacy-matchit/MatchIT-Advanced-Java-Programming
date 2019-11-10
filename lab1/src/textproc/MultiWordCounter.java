package textproc;

import java.util.HashMap;

public class MultiWordCounter implements TextProcessor {

    private String [] landscapes;
    private HashMap<String ,Integer> m;
    public MultiWordCounter(String [] landscapes){
        this.landscapes = landscapes;
        m = new HashMap<>();
    }

    @Override
    public void process(String w) {
        for(int i = 0; i < landscapes.length; i++){
            if(landscapes[i].equals(w)){
                int nbr = 0;
                if(m.containsKey(w)){
                    nbr = m.get(w);
                }
                nbr++;
                m.put(w,nbr);
            }
        }
    }

    @Override
    public void report() {
        for (String key : m.keySet()){
            System.out.println(key + "  " + m.get(key));
        }
    }
}
