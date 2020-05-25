/**
 * LeetCode No.303
 */
public class NumArray2 {

    //sum[i]存储前i个元素和，sum[0] = 0
    //sum[i]存储nums[0...i-1]的和
    private int[] sum;

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
