import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int a = 42;
        System.out.println(Integer.valueOf(a).hashCode());

        int b = -42;
        System.out.println(Integer.valueOf(b).hashCode());

        double c = 3.1415926;
        System.out.println(Double.valueOf(c).hashCode());

        String d = "qin";
        System.out.println(d.hashCode());


        System.out.println("HashTable: " + test(new HashTable<>()) + "ms");

    }


    public static double test(HashTable<String, Integer> tree) {
        long start = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileUtil.readFile("RedBlackTree/src/test.txt", words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                if (tree.contains(word)) {
                    tree.set(word, tree.get(word) + 1);
                } else {
                    tree.add(word, 1);
                }
            }
            System.out.println("Total different words: " + tree.size());
            System.out.println("Frequency of PRIDE: " + tree.get("pride"));
        }
        return (System.nanoTime() - start) / 1000000.0;
    }
}
