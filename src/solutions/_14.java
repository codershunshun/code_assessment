/**
 * 小明要帮公司买水果，给了一个codeList， 里面装的是他买的水果，给了一个shoppingCart里面装的是target水果，目标是检查codelist里的水果顺序是否和shoppingCart里的顺序匹配。
 * 注意的是只有codelist中的所有链表的item之后加起来小于等于shoppingcart里item之和才可能返回1。 另外在codelist中有可能出现item时 "anything"， 它可以和任意的水果匹配。
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _14 {
	public static void main(String[] args) {
		List<List<String>> fruitList = new ArrayList<>();
		fruitList.add(Arrays.asList("apple","apple"));
		fruitList.add(Arrays.asList("orange", "anything", "orange"));
		//fruitList.add(Arrays.asList("pear","orange", "grape"));
		List<String> shoppingList = Arrays.asList("orange", "apple", "apple", "orange", "mango", "orange");

		_14 solution = new _14();

		System.out.println(solution.match(fruitList, shoppingList));
	}

	public int match(List<List<String>> fruitList, List<String> shoppingList) {
		if (fruitList == null || shoppingList == null) {
			return 0;
		}


		int i = 0, j = 0;

		while (i < fruitList.size()) {
			List<String> current = fruitList.get(i);
			int k = 0;

			while (k < fruitList.get(i).size() && j < shoppingList.size()) {
				if (current.get(k).equals(shoppingList.get(j)) || current.get(k).equals("anything")) {
					k++;
				}
				j++;
			}
			if (k == current.size()) {
				i++;
			} else {
				return 0;
			}
		}

		return 1;

	}
}
