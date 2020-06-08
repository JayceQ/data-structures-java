import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("BSTSet: " + testSet(new BSTSet<>()) + "ms");
        System.out.println("LinkedListSet: " + testSet(new LinkedListSet<>()) + "ms");
        System.out.println("AVLSet: " + testSet(new AVLSet<>()) + "ms");
    }

    private static double testSet(Set<String> set) {
        ArrayList<String> words = new ArrayList<>();
        FileUtil.readFile("Set/src/test.txt", words);
        System.out.println(set.getClass().getName() + ": Total words: " + words.size());
        long start = System.nanoTime();
        for (String word : words) {
            set.add(word);
        }
        System.out.println(set.getClass().getName() + ": Total different words: " + set.size());
        return (System.nanoTime() - start) / 1000000.0;
    }


}
