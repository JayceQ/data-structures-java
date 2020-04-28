import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        boolean b = FileUtil.readFile("Set/src/test.txt", words);
        System.out.println("Total words: " + words.size());


        BSTSet<String> set = new BSTSet<>();
        for (String word : words) {
            set.add(word);
        }
        LinkedListSet<String> set2 = new LinkedListSet<>();
        for (String word : words) {
            set2.add(word);
        }
        System.out.println("Total different words: " + set.size());
        System.out.println("Total different words: " + set2.size());
    }
}
