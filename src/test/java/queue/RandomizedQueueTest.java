package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void enqueue_happyDay() {
        var queue = new RandomizedQueue<Integer>();

        queue.enqueue(1);

        assertEquals(1, queue.size());

        assertEquals(1, queue.dequeue());

        assertEquals(0, queue.size());
    }

    @Test
    void enqueue_BoundsIssue() {
        var queue = new RandomizedQueue<Integer>();

        queue.enqueue(10);
        queue.enqueue(27);
        queue.enqueue(31);
        queue.enqueue(32);
        queue.dequeue();

        assertEquals(3, queue.size());

        queue.enqueue(28);

        assertEquals(4, queue.size());
    }
}