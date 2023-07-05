package queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int capacity;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        capacity = 2;
        queue = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() > 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return queue.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (size() == capacity) {
            scaleUpCapacity();
        }

        queue[size()] = item;
    }

    @SuppressWarnings("unchecked")
    private void scaleUpCapacity() {
        var newCapacity = capacity * 2;
        var newQueue = (Item[]) new Object[newCapacity];

        for (var i = 0; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }

        queue = newQueue;
        capacity = newCapacity;
    }

    @SuppressWarnings("unchecked")
    private void scaleDownCapacity() {
        var newCapacity = capacity / 2;
        var newQueue = (Item[]) new Object[newCapacity];

        for (var i = 0; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }

        queue = newQueue;
        capacity = newCapacity;
    }

    // remove and return a random item
    public Item dequeue() {
        var toRemoveIndex = StdRandom.uniformInt(0, size() - 1);

        var removed = queue[toRemoveIndex];
        queue[toRemoveIndex] = null;

        if (size() < (capacity / 4)) {
            scaleDownCapacity();
        }

        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        var toGetIndex = StdRandom.uniformInt(0, size() - 1);

        return queue[toGetIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Deque.Node current;

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
