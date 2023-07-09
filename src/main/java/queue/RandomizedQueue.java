package queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;

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
            resizeTo(capacity * 2);
        }

        queue[currentIndex++] = item;
        count++;
    }

    private void resizeTo(int newCapacity) {
        var newQueue = (Item[]) new Object[newCapacity];

        for (var i = 0; i < count; i++) {
            newQueue[i] = queue[i];
        }

        queue = newQueue;
        capacity = newCapacity;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();

        var toRemoveIndex = StdRandom.uniformInt(0, currentIndex);

        var removed = queue[toRemoveIndex];

        // if we will have more than 1 item after the removal
        // and we are not removing the item at the end..
        // then copy the last item into the removed item's slot
        if (count > 2 && toRemoveIndex != (currentIndex - 1)) {
            var endItem = queue[currentIndex - 1];
            queue[toRemoveIndex] = endItem;
            queue[currentIndex - 1] = null;
        } else {
            queue[toRemoveIndex] = null;
        }

        currentIndex--;
        count--;

        if (size() < (capacity / 4)) {
            resizeTo(capacity / 2);
        }

        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0)
            throw new NoSuchElementException();

        var toGetIndex = StdRandom.uniformInt(0, currentIndex);

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

            for (int i = 0; i < inRq.count; i++) {
                var item = inRq.queue[i];

                rq.enqueue(item);
            }

            queue = (Item[]) new Object[inRq.count];
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
