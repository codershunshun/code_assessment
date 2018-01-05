/**
 * string里每个character代表一个movie shot, 要把这些character做partition组成一个movie scene, 要求每个partition里的character 只能在这个partition里出现。
 * 比如说"abacdfeffhijkik" 我们可以分成 "aba | c | d | feffh | ijkik"
 * 输出是每个partition的size组成的list.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class Interval {
	int start, end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class _13 {
	public static void main(String[] args) {
		String str = "abacdfeffhijkik";
		_13 solution = new _13();

		List<String> ans = solution.findPartitions(str);

		for (String s : ans) {
			System.out.println(s);
		}
	}

	public List<String> findPartitions(String string) {
		if (string == null) {
			return null;
		}

		HashMap<Character, Interval> hash = new HashMap<>();

		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);

			if (!hash.containsKey(c)) {
				hash.put(c, new Interval(i, i));
			}

			hash.get(c).end = i;
		}

		List<Interval> list = mergeIntervals(new ArrayList<>(hash.values()));
		List<String> ans = new ArrayList<>();

		for (Interval i : list) {
			ans.add(string.substring(i.start, i.end + 1));
		}

		return ans;
	}

	private List<Interval> mergeIntervals(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1) {
			return intervals;
		}

		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare (Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});

		List<Interval> ans = new ArrayList<>();
		Interval last = intervals.get(0);

		for (int i = 0; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);

			if (cur.start < last.end) {
				last.end = Math.max(cur.end, last.end);
			} else {
				ans.add(last);
				last = cur;
			}
		}
		ans.add(last);
		return ans;
	}
}
