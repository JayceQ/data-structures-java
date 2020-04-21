/**
 *
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = genDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + "in" + head);
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: NULL");
            return null;
        }
        //删除结果
        ListNode res = removeElements(head.next, val, ++depth);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        //如果head删除，就返回删除结果；如果未删除，删除节点head的下一节点指向结果
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
//        //head下一节点指向删除的结果
//        head.next = removeElements(head.next, val);
//        //如果head删除，就返回这个head节点的下一节点；如果没删，就返回这个head节点，
//        return head.val == val ? head.next : head;

    }

    private String genDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        new Solution3().removeElements(head, 6, 0);
        System.out.println(head);
    }
}
