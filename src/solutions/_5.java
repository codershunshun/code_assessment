/**
 *  Find largest find circle
 */

import java.util.*;

class UnionFind {
	Map<String, String> unionTable;
	
	UnionFind(String[][] pairs) {
		unionTable = new HashMap<String, String>();

		for (String[] pair : pairs) {
			unionTable.put(pair[0], pair[0]);
			unionTable.put(pair[1], pair[1]);
		}
	}
	
	public void connect(String p, String q) {
		String unionKey = null, other = null;
        String key0 = unionTable.get(p);
        String key1 = unionTable.get(q);
        
        if (key0.compareTo(key1) < 0) {
            unionKey = key0;
            other = q;
        } else {
            unionKey = key1;
            other = p;
        }
        unionTable.put(other, unionKey);
	}
}

class _5 {
	public static void main(String[] args) {
		String[][] pairs = {{"a", "b"}, {"b", "c"}, {"e", "b"},{"g", "d"}, {"d", "f"}, {"f", "h"}};
		
		_5 s = new _5();
		printArray(s.largestFriendCircle(pairs));
	}
	
	private static void printArray(String[] s) {
        for (String x : s) System.out.print(x + " ");
        System.out.println();
    }

	public String[] largestFriendCircle(String[][] pairs) {
		if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
			return null;
		}
		
		UnionFind uf = new UnionFind(pairs);
		
		for (String[] pair : pairs) {
			uf.connect(pair[0], pair[1]);
		}
		
		int max = -1;
		String maxKey = null;
		Map<String, Integer> counts = new HashMap<>();
		
		for (String str : uf.unionTable.keySet()) {
			counts.put(uf.unionTable.get(str), counts.getOrDefault(uf.unionTable.get(str), 0) + 1);
		}
		
		for (String str : counts.keySet()) {
			if (counts.get(str) > max || (counts.get(str) == max && str.compareTo(maxKey) < 0)) {
				max = counts.get(str);
				maxKey = str;
			}
		}
		
		 String[] res = new String[max];
		 for (String str: uf.unionTable.keySet()){
			 if (uf.unionTable.get(str).equalsIgnoreCase(maxKey)){
				 res[--max] = str;
	         }
	     }
		 
		 Arrays.sort(res);
		 
		 return res;
	}
}
