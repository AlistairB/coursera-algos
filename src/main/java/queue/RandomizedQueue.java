package queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;

    private int[] populatedIndexes;
    private int capacity;

    private int count;
    private int currentIndex;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 2;
        currentIndex = 0;
        count = 0;
        queue = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (currentIndex == capacity) {
            scaleUpCapacity();
        }

        queue[currentIndex++] = item;
        count++;
    }

    private void scaleUpCapacity() {
        var newCapacity = capacity * 2;
        var newQueue = (Item[]) new Object[newCapacity];

        var insertIndex = 0;
        for (var i = 0; i < queue.length; i++) {
            var toCopyItem = queue[i];

            if (toCopyItem != null) {
                newQueue[insertIndex++] = toCopyItem;
            }
        }

        queue = newQueue;
        capacity = newCapacity;
    }

    private void scaleDownCapacity() {
        var newCapacity = capacity / 2;
        var newQueue = (Item[]) new Object[newCapacity];

        var insertIndex = 0;
        for (var i = 0; i < queue.length; i++) {
            var toCopyItem = queue[i];

            if (toCopyItem != null) {
                newQueue[insertIndex++] = toCopyItem;
            }
        }

        queue = newQueue;
        capacity = newCapacity;
        currentIndex = newCapacity - 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();

        var toRemoveIndex = StdRandom.uniformInt(0, currentIndex);

        var removed = queue[toRemoveIndex];

        if (removed == null)
            return dequeue();

        queue[toRemoveIndex] = null;

        if (size() < (capacity / 4)) {
            scaleDownCapacity();
        }

        count--;

        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0)
            throw new NoSuchElementException();

        var toGetIndex = StdRandom.uniformInt(0, currentIndex);
        var item = queue[toGetIndex];
        if (item == null)
            return sample();

        return queue[toGetIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(this);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] queue;
        int currentIndex;


        // initialize pointer to head of the list for iteration
        public RandomizedQueueIterator(RandomizedQueue<Item> inRq)
        {
            currentIndex = 0;
            var rq = new RandomizedQueue<Item>();

            for (int i = 0; i < inRq.queue.length; i++) {
                var item = inRq.queue[i];

                if (item != null)
                    rq.enqueue(item);
            }

            queue = (Item[]) new Object[rq.count];
            var index = 0;

            while (!rq.isEmpty()) {
                queue[index++] = rq.dequeue();
            }
        }

        // returns false if next element does not exist
        public boolean hasNext()
        {
            return currentIndex < queue.length;
        }

        // return current data and update pointer
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            return queue[currentIndex++];
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
