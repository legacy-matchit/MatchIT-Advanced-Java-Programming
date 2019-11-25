package queue_singlelinkedlist;
import java.util.Iterator;
import java.util.Queue;

public class Test{
    public static void main(String[] args) {

        FifoQueue<String > fifoQueue1 = new FifoQueue<String >();
        FifoQueue<String > fifoQueue2 = new FifoQueue<String >();

        fifoQueue1.offer("A");
        fifoQueue1.offer("B");
        fifoQueue1.offer("C");

        fifoQueue2.offer("D");
        fifoQueue2.offer("E");
        fifoQueue2.offer("F");

        fifoQueue1.append(fifoQueue2);
        System.out.println(fifoQueue1);
        System.out.println(fifoQueue1.poll());
        System.out.println(fifoQueue1.poll());
        System.out.println(fifoQueue1.poll());
        System.out.println(fifoQueue1.poll());
        System.out.println(fifoQueue1.poll());
        System.out.println(fifoQueue1.poll());

    }
}
