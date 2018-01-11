/**
 * Johns play a game in which he throws a baseball at various blocks marked with a symbol so as to knock these out.
 * A score is computed for each throw. The 'last score' is the score of the previous throw (or 0 if there is no previous throw)
 * and the total score is the sum of the scores of all the throws.
 * The symbol on a block can be an integer, a sign or a letter. Each sign or letter represents a special rule as given below.
 *
 * if a throw hits a block marked with an integer, the score for that throw is the value of that integer.
 * if a throw hits a block marked with an 'X', the score for that throw is double the last score.
 * if a throw hits a block marked with an '+', the score for that throw is the sum of the last two scores.
 * if a throw hits a block marked with an 'Z', the last score is removed, as though the last throw never happened. Its value doesn't count towards the total score, and the subsequent throws will ingnore it when computing their values.
 *
 * Return an integer representing the total score for the given list of ordered hits;
 *
 * Example:
 * Given throw history array input:
 * [5, -2, 4, Z, X, 9, +, +]
 *
 * Output:
 * 27
 *
 * Explanation:
 * 5 -> sum =5
 * -2 -> sum = 5 - 2 = 3
 * 4 -> sum = 3 + 4 = 7
 * Z -> sum = 7 - 4 = 3
 * X -> sum = 3 + (-2 * 2) = -1
 * 9 -> sum = -1 + 9 = 8
 * + -> sum = 8 + (-4 + 9) = 13
 * + -> sum = 13 + (-4 + 9) + 9 = 27
 */
import java.util.Stack;

class _3 {
	public static void main(String arg[]) {
		_3 obj = new _3();
		String[] blocks = {"5", "-2", "4", "Z", "X", "9", "+", "+"};

		System.out.println(obj.totalScore(blocks));
	}

	private int currentScore = 0;

	private boolean isInteger(String block) {
		try {
			currentScore = Integer.parseInt(block);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int totalScore(String[] blocks) {
		if (blocks == null || blocks.length == 0) {
			return 0;
		}

		if (blocks.length == 1 && !isInteger(blocks[0])) {
			return 0;
		}

		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;

		while (i < blocks.length) {
			String current = blocks[i];

			if (isInteger(current)) {
				stack.push(currentScore);
			}

			if (current.equals("Z")) {
				stack.pop();
			}

			if (current.equals("X")) {
				if (stack.peek() == null) {
					stack.push(0);
				} else {
					stack.push(2 * stack.peek());
				}
			}

			if (current.equals("+") ) {
				int score1 = stack.peek() != null ? stack.pop() : 0;
				int score2 = stack.peek() != null ? stack.pop() : 0;

				stack.push(score2);
				stack.push(score1);
				stack.push(score1 + score2);
			}
			i++;
		}

		int sum = 0;

		while (!stack.isEmpty()) {
			sum += stack.pop();
		}

		return sum;
	}
}
