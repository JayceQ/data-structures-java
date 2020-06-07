import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        System.out.println("BSTMap: " + testSet(new BSTMap<>()) + "ms");
        System.out.println("LinkedListMap: " + testSet(new LinkedListMap<>()) + "ms");
        System.out.println("AVLMap: " + testSet(new AVLMap<>()) + "ms");
    }

    private static double testSet(Map<String, Integer> map) {
        long start = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileUtil.readFile("Map/src/test.txt", words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
        }
        return (System.nanoTime() - start) / 1000000.0;
    }
}
