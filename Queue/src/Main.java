import java.util.Random;

public class Main {

    private static double testQueue(Queue<Integer> queue, int opCount) {

        long start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }

        long end = System.nanoTime();

        return (end - start) / 1000000;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue: " + testQueue(arrayQueue, opCount) + " ms");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue: " + testQueue(loopQueue, opCount) + " ms");
    }
}
