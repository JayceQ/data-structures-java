/**
 * LeetCode No.20 Valid Parentheses
 */
public class Solution {

    public static boolean isValid(String s) {
        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char topChar = stack.pop();
                    if (c == ')' && topChar != '(') {
                        return false;
                    }
                    if (c == ']' && topChar != '[') {
                        return false;
                    }
                    if (c == '}' && topChar != '{') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{(())}"));
    }
}
