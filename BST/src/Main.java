import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        bst.postOrder();
//        System.out.println();
//        bst.postOrderRN();
//        System.out.println();
        bst.remove(5);
        bst.levelOrder();

        BST<Integer> tree = new BST<>();
        Random random = new Random();
        int n = 10;
        for (int i = 0; i < n; i++) {
            tree.add(random.nextInt(100));
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!tree.isEmpty()) {
            list.add(tree.removeMin());
        }
        System.out.println(list);
        //System.out.println(bst);
    }
}
