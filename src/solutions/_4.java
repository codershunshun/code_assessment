/**
 * 假设有如下的movie类
 * public class Movie {
 *     int movieId;
 *     float rating;
 *     List<Movie> similarMovies
 *     
 *     ...(some getters/setters)
 * }
 * 
 * 现在要求找到k个和movie最相似的movies
 *
 */

import java.util.*;

class _4 {
	class Movie {
		int id;
		float rating;
		List<Movie> similarMovies;
		
		public Movie(int id, float rating, List<Movie> similarMovies) {
			this.id = id;
			this.rating = rating;
			this.similarMovies = similarMovies;
		}
		
		public float getRating() {
			return rating;
		}
		
		public int getId() {
			return id;
		}
		
		public List<Movie> getSimilarMovies() {
			return similarMovies;
		}
		
		public String toString() {
			return "Movie{" + "id=" + id + ", rating=" + rating + "}";
		}
		
	}
	class Solution {
//		public static void main(String[] args) {
//			List<Movie> neibors = new ArrayList<>();
//
//	        List<Movie> nextNei = new ArrayList<>();
//	        nextNei.add(new Movie(8, 101, null));
//	        nextNei.add(new Movie(9, 100, null));
//
//	        neibors.add(new Movie(1, 90, null));
//	        neibors.add(new Movie(2, 80, null));
//	        neibors.add(new Movie(3, 70, null));
//	        neibors.add(new Movie(4, 60, null));
//	        neibors.add(new Movie(5, 50, null));
//	        neibors.add(new Movie(6, 99, null));
//
//	        neibors.add(new Movie(7, 10, nextNei));
//	        Movie root = new Movie(0, 100, neibors);
//	        Solution s = new Solution();
//	        
//	        System.out.println(s.getNearest(root, 8));
//		}

		public List<Movie> getNearest(Movie movie, int numSimilar) {
			List<Movie> ans = new ArrayList<Movie>();
			
			if (movie == null || numSimilar <= 0) {
				return ans;
			}
			
			Queue<Movie> queue = new LinkedList<Movie>();
			PriorityQueue<Movie> minHeap = new PriorityQueue<Movie>(numSimilar, new Comparator<Movie>() {
				public int compare(Movie m1, Movie m2) {
					return Float.compare(m1.getRating(), m2.getRating());
				}
			});
			
			Set<Integer> isVisited = new HashSet<Integer>();
			queue.offer(movie);
			
			while (!queue.isEmpty()) {
				Movie current = queue.poll();
				
				if (current != null && current.getSimilarMovies() != null) {
					for (Movie neighbor : current.getSimilarMovies()) {
						if (isVisited.add(neighbor.getId())) {
							if (neighbor.getId() != movie.getId()) {
								minHeap.offer(neighbor);
							}
							
							if (minHeap.size() > numSimilar) {
								minHeap.poll();
							}
							queue.offer(neighbor);
						}
					}
				}
			}
			while (!minHeap.isEmpty()) {
				ans.add(minHeap.poll());
			}
			return ans;
		}
	}
}
