package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void addLast_happyDay() {
        var deque = new Deque<Integer>();

        deque.addLast(1);
        deque.addLast(2);

        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.removeLast());
    }

    @Test
    void addFirst_happyDay() {
        var deque = new Deque<Integer>();

        deque.addFirst(1);
        deque.addFirst(2);

        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.removeFirst());
    }

    @Test
    void addFirst_Iterator() {
        var deque = new Deque<Integer>();

        deque.addFirst(1);
        deque.addFirst(2);

        var iterator = deque.iterator();

        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
    }
}