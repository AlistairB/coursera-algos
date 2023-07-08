package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item value;
        Node next;

        Node prev;

        private Node(Item item) {
            this.value = item;
        }
    }

    private Node first;
    private Node last;
    private int length;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return length == 0;
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        var newFirst = new Node(item);

        if (!isEmpty()) {
            var oldFirst = first;
            newFirst.next = oldFirst;
            oldFirst.prev = newFirst;
        } else {
            last = newFirst;
        }

        first = newFirst;

        length++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        var newLast = new Node(item);

        if (!isEmpty()) {
            last.next = newLast;
            newLast.prev = last;
        } else {
            first = newLast;
        }

        length++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        var oldFirst = first;
        first = oldFirst.next;
        first.prev = null;

        if (length == 1) {
            last = null;
        }

        length--;

        return oldFirst.value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        var oldLast = last;
        last = oldLast.prev;
        last.next = null;

        if (length == 1) {
            first = null;
        }

        length--;

        return oldLast.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        // initialize pointer to head of the list for iteration
        public DequeIterator(Deque<Item> deque)
        {
            current = deque.first;
        }

        // returns false if next element does not exist
        public boolean hasNext()
        {
            return current != null && current.next != null;
        }

        // return current data and update pointer
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            current = current.next;
            return current.value;
        }

        // implement if needed
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}