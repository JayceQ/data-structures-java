import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        System.out.println("BST: " + testSet(new AVLTree<>()) + "ms");
    }

    private static double testSet(AVLTree<String, Integer> tree) {
        long start = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileUtil.readFile("AVLTree/src/test.txt", words)) {
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
            System.out.println("is BST : " + tree.isBST());
            System.out.println("is Balanced : " + tree.isBalanced());

            for (String word : words) {
                tree.remove(word);
                if (!tree.isBST() || !tree.isBalanced()) {
                    throw new RuntimeException("ERROR");
                }
            }
        }
        return (System.nanoTime() - start) / 1000000.0;
    }
}
