import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        stack.pop();

        System.out.println(stack);
        LinkedListStack<Integer> linkedListStack= new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            linkedListStack.push(i);
        }
        System.out.println(linkedListStack);

        stack.pop();

        System.out.println(linkedListStack);

        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("ArrayStack, time: "+testStack(arrayStack,opCount)+"ms");

        LinkedListStack<Integer> linkedListStack1 = new LinkedListStack<>();
        System.out.println("LinkedListStack, time: "+testStack(linkedListStack1,opCount)+"ms");


    }

    public static double testStack(Stack<Integer> stack,int opCount){
        long start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long end = System.nanoTime();

        return (end - start) / 1000000.0;
    }
}
