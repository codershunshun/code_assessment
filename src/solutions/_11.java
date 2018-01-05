/**
 * Given a string and an integer K, return a list of substring with exactly k distinct characters.
 * 就是说substring里有且只有一个字母重复一次。注意输出的时候，不能有重复的substring.
 *
 */

import java.util.HashSet;
import java.util.Set;

class _11 {
	public static void main(String[] args) {
		System.out.println(substringWithKDistinct("awaglknagawunagwkwag", 4));
	}

	public static Set<String> substringWithKDistinct(String s, int k) {
		Set<String> list = new HashSet<>();

		if (s == null || s.length() == 0) {
			return list;
		}

		int[] map = new int[256];
		int j = 0;

		for (int i = 0; i < s.length(); i++) {
			while(j < s.length() && map[s.charAt(j)] == 0) {
				map[s.charAt(j)]++;
				j++;

				if (j - i == k) {
					list.add(s.substring(i, j));
					break;
				}
			}
			map[s.charAt(i)]--;
		}
		return list;
	}
}
