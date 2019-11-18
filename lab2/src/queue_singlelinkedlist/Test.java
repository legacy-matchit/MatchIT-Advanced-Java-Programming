package queue_singlelinkedlist;


import java.util.Iterator;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        FifoQueue<String > fifoQueue1 = new FifoQueue<String >();
        FifoQueue<String > fifoQueue2 = new FifoQueue<String >();

//        fifoQueue1.offer("A");
//        fifoQueue1.offer("B");
//        fifoQueue1.offer("C");

        fifoQueue2.offer("A");
        fifoQueue2.offer("B");
        fifoQueue2.offer("C");

        fifoQueue1.append(fifoQueue2);
        System.out.println(fifoQueue1);
    }
}
