package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class GraphComponent {

	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		int[] nodes = new int[2 * N + 1];
		int[] ranks = new int[2 * N + 1];
		
		solve(nodes, ranks, streamTokenizer, N);
		bufferedReader.close();
	}
	
	private static void solve(int[] nodes, int[] ranks, StreamTokenizer streamTokenizer, int N) throws Exception {
		initialize(nodes, ranks);
		for(int i = 0; i < N; i++) {
			streamTokenizer.nextToken(); int a = (int) streamTokenizer.nval;
			streamTokenizer.nextToken(); int b = (int) streamTokenizer.nval;
			
			union(nodes, ranks, a, b);
		}
		Arrays.sort(ranks);
		findMinMax(ranks);
	}
	
	private static void initialize(int[] nodes, int[] ranks) throws Exception {
		for(int i = 1; i < nodes.length; i++ ) {
			nodes[i] = i;
			ranks[i] = 1;
		}
	}
	
	private static void union(int[] nodes, int[] ranks, int a, int b) throws Exception {
		int aSet = findSet(nodes, a);
		int bSet = findSet(nodes, b);
		
		if(aSet == bSet)
			return;
		
		if(aSet < bSet) {
			nodes[bSet] = nodes[aSet];
			ranks[aSet] += ranks[bSet];
			ranks[bSet] = 0;
		} else {
			nodes[aSet] = nodes[bSet];
			ranks[bSet] += ranks[aSet];
			ranks[aSet] = 0;
		}
	}
	
	private static int findSet(int[] nodes, int a) throws Exception {
		while(nodes[a] != a) {
			nodes[a] = nodes[nodes[a]];
			a = nodes[a];
		}
		return a;
	}
	
	private static void findMinMax(int[] ranks) throws Exception {
		int max = ranks[ranks.length - 1];
		int min = binarySearch(ranks, 0, ranks.length - 1);
		
		System.out.println(min + " " + max);
	}
	
	private static int binarySearch(int[] ranks, int low, int high) throws Exception {
		if(low <= high) {
			int mid = low + (high - low) / 2;
			if(ranks[mid] == 0 || ranks[mid] == 1) {
				if(ranks[mid + 1] > 1)
					return ranks[mid + 1];
				return binarySearch(ranks, mid + 1, high);
			} else if(ranks[mid] > 1){
				return binarySearch(ranks, low, mid);
			}
		}
		return -1;
	}
}