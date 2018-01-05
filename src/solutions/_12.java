import java.util.HashMap;

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

class _12 {
	public static void main(String[] args) {
		_12 s = new _12();
		String[] source = {"a", "b", "c", "d"};
		String[] target = {"b", "d"};

		System.out.println(s.range(source, target)[0]);
		System.out.println(s.range(source, target)[1]);
	}

	public int[] range(String[] source, String[] target) {
		int[] result = new int[2];

		if (target.length > source.length) {
			result[0] = -1;
			result[1] = -1;
			return result;
		}

		HashMap<String, Integer> sourceHash = new HashMap<>();
		HashMap<String, Integer> targetHash = new HashMap<>();

		for (String str : target) {
			if (!targetHash.containsKey(str)) {
				targetHash.put(str, 1);
			} else {
				targetHash.put(str, targetHash.get(str) + 1);
			}
		}

		int j = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < source.length; i++) {
			while (!valid(sourceHash, targetHash) && j < source.length) {
				String str = source[j];

				if (sourceHash.containsKey(str)) {
					sourceHash.put(str, sourceHash.get(str) + 1);
				} else {
					sourceHash.put(str, 1);
				}
				j++;
			}

			if (valid(sourceHash, targetHash)) {
				if (min > j - i) {
					min = j - i;
					result[0] = i;
					result[1] = j - 1;
				}
			}

			sourceHash.put(source[i], sourceHash.get(source[i]) - 1);
		}

		return result;
	}

	private boolean valid(HashMap<String, Integer> sourceHash, HashMap<String, Integer> targetHash) {
		for (String str : targetHash.keySet()) {
			if (!sourceHash.containsKey(str)) {
				return false;
			} else {
				if (targetHash.get(str) > sourceHash.get(str)) {
					return false;
				}
			}
		}

		return true;
	}
}
