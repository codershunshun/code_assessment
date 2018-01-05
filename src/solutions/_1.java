/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 */

import java.util.*;

class _1 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Stack<Character> stack = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if ("({[".contains(String.valueOf(c))) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && isValidParentheses(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isValidParentheses(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }
}