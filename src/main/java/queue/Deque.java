package queue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item>{
        Item value;
        Node next;

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
        var newFirst = new Node(item);

        if (!isEmpty()) {
            var oldFirst = first;
            newFirst.next = oldFirst;
        }

        first = newFirst;
    }

    // add the item to the back
    public void addLast(Item item) {

    }

    // remove and return the item from the front
    public Item removeFirst() {
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}