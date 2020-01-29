package map;

import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SimpleHashMapDemo {
    public static void main(String[] args){

        SimpleHashMap<Integer,Integer> m16 = new SimpleHashMap<>(1000);

        Random random = new Random(123456);
        HashSet<Integer> randNbrs = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(10000);
            m16.put(r, r);
            randNbrs.add(r);
        }
        for (int i : randNbrs) {
            System.out.println(i + " : " + m16.get(i));
        }

       // System.out.println(m16.show());
/*
        m16.put(800,800);
        System.out.println(m16.show());*/
    }
}
