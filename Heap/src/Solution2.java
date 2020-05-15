import java.util.PriorityQueue;
import java.util.*;

/**
 * LeetCode No.347 Top K 使用Java的PriorityQueue
 */
public class Solution2 {

    public int[] topKFrequnet(int[] nums, int k) {
        //key = num, value = freq;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (int key : map.keySet()) {
            if (pq.size() < k)
                pq.add(key);
            else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty())
            res.add(pq.remove());
        return Arrays.stream(res.toArray(new Integer[0])).mapToInt(Integer::valueOf).toArray();
    }


    public static void main(String[] args) {
        //int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums = {5, 2, 5, 3, 5, 3, 1, 1, 3};
        int[] ints = new Solution2().topKFrequnet(nums, 2);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

}
