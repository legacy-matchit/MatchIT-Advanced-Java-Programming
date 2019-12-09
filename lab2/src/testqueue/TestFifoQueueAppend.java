package testqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.Assert.*;

public class TestFifoQueueAppend {

    private FifoQueue<String> q1;
    private FifoQueue<String> q2;

    @Before
    public void setUp() throws Exception {
        q1 = new FifoQueue<String>();
        q2 = new FifoQueue<String>();
    }

    @After
    public void tearDown() throws Exception {
        q1 = null;
        q2 = null;
    }

    @Test
    public void twoEmptyQueues(){
        assertEquals("it should be zero", 0, q1.size());
    }

    @Test
    public void oneEmptyQueueWithNonEmptyQueue(){
        q2.offer("A");
        q2.offer("B");
        q2.offer("C");
        q1.append(q2);
        assertEquals("it should be 3", 3, q1.size());
        assertEquals("it should be 0", 0, q2.size());

    }

    @Test
    public void nonEmptyQueueWithOneEmptyQueue(){
        q1.offer("A");
        q1.offer("B");
        q1.offer("C");
        q1.append(q2);
        assertEquals("it should be 3", 3, q1.size());
    }

    @Test
    public void twoNonEmptyQueues(){
        q1.offer("A");
        q1.offer("B");

        q2.offer("C");
        q2.offer("D");
        q1.append(q2);
        assertEquals("it should be 4", 4, q1.size());
        assertEquals("it should be 0", 0, q2.size());
        assertEquals("it should be A", "A", q1.poll());
        assertEquals("it should be B", "B", q1.poll());
        assertEquals("it should be C", "C", q1.poll());
        assertEquals("it should be D", "D", q1.poll());


    }

    @Test
    public void mergeWithItSelf(){
        q1.offer("A");
        q1.offer("B");
        try {
            q1.append(q1);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // successful test
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void mergeWithItself2(){
        q1.append(q1);
    }
}