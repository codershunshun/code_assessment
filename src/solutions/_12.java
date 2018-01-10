/**
 * 有一个target list of String, 一个available list of String, 找出available list里最小连续的区间，使得targetlist里的所有词都在这个区间里，无顺序要求。
 *
 * Example:
 *
 * Input:
 * target = {s1, s2, s3} , source = {s1, s3, s2, s2, s4, s3, s1}
 *
 * Output:
 * [0, 2]
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class _12 {
	public static void main(String[] args) {
		_12 s = new _12();
		String[] source = {"the", "spain", "that", "the", "rain", "in","spain", "stays", "forcast", "int", "the"};
		String[] target = {"in","the", "spain"};
		String[] source1 = {"abc", "2abc", "cab", "bcd", "bcd"};
		String[] target1 = {"2abc","bcd", "cab"};
		List<String> targetList = Arrays.asList(target);
		List<String> sourceList = Arrays.asList(source);
		System.out.println(s.subSequenceTags(targetList, sourceList).get(0));
		System.out.println(s.subSequenceTags(targetList, sourceList).get(1));
	}

	public List<Integer> subSequenceTags(List<String> targetList, List<String> availableTagList) {
		List<Integer> ans = new ArrayList<>();

		if (targetList.size() > availableTagList.size()) {
			ans.add(0);
			return ans;
		}

		HashMap<String, Integer> targetHash = new HashMap<>();

		for (String tag : targetList) {
			String str = tag.toLowerCase();
			targetHash.put(str, targetHash.getOrDefault(str, 0) + 1);
		}

		int start = 0;
		int end = 0;
		int ansStart = 0;
		int ansEnd = availableTagList.size() - 1;
		int min = Integer.MAX_VALUE;
		int count = targetList.size();
		boolean found = false;

		for (start = 0; start < ansEnd; start++) {
			while (count > 0 && end < availableTagList.size()) {
				String str = availableTagList.get(end).toLowerCase();

				targetHash.put(str, targetHash.getOrDefault(str, 0) - 1);
				if (targetHash.get(str) >= 0) {
					count--;
				}
				end++;
			}

			if (count == 0) {
				found = true;
				if (min > end - start) {
					min = end - start;
					ansStart = start;
					ansEnd = end - 1;
				}

				String cur = availableTagList.get(start).toLowerCase();

				targetHash.put(cur, targetHash.get(cur) + 1);

				if (targetHash.get(cur) > 0) {
					count++;
				}
			}
		}

		if (!found) {
			ans.add(0);
			return ans;
		}

		ans.add(ansStart);
		ans.add(ansEnd);

		return ans;
	}
}
