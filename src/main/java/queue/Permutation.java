package queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int sampleCount = Integer.parseInt(args[0]);

        var rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            var str = StdIn.readString();
            rq.enqueue(str);
        }

        for (var i = 0; i < sampleCount; i++) {
            var str = rq.dequeue();

            StdOut.println(str);
        }
    }
}
