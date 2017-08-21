package data.structure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class MerginCommunities {

	private static final PrintWriter writer = new PrintWriter(System.out);
	
	public static void main(String...strings) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
		
		streamTokenizer.nextToken(); int N = (int) streamTokenizer.nval;
		streamTokenizer.nextToken(); int Q = (int) streamTokenizer.nval;
		
		int[] people = new int[N + 1];
		int[] ranks  = new int[N + 1];
		initialize(people, ranks);
		
		solve(people, ranks, Q, streamTokenizer);
		writer.flush();
		
		bufferedReader.close();
		writer.close();
	}
	
	private static void initialize(int[] people, int[] ranks) throws Exception {
		for(int i = 1; i < people.length; i++) {
			people[i] = i;
			ranks[i]  = 1;
		}
	}
	
	private static void solve(int[] people, int[] ranks, int Q, StreamTokenizer streamTokenizer) throws Exception {
		while(Q-- > 0) {
			streamTokenizer.nextToken(); String query = streamTokenizer.sval;
			streamTokenizer.nextToken(); int i = (int) streamTokenizer.nval;
			
			if("Q".equalsIgnoreCase(query)) {
				int result = find(people, ranks, i);
				writer.append("" + result);
				writer.append("\n");
			} else if("M".equalsIgnoreCase(query)) {
				streamTokenizer.nextToken(); int j = (int) streamTokenizer.nval;
				union(people, ranks, i, j);
			}			
		}
	}
	
	private static int find(int[] people, int[] ranks, int i) throws Exception {
		int set = findSet(people, i);
		return ranks[set];
	}
	
	private static void union(int[] people, int[] ranks, int i, int j) throws Exception {
		int iSet = findSet(people, i);
		int jSet = findSet(people, j);
		
		if(iSet == jSet)
			return;
		
		if(iSet < jSet) {
			people[jSet] = people[iSet];
			ranks[iSet] += ranks[jSet];	
		} else {
			people[iSet] = people[jSet];
			ranks[jSet] += ranks[iSet];
		}
	}
	
	private static int findSet(int[] people, int a) throws Exception {
		while(people[a] != a) {
			people[a] = people[people[a]];
			a = people[a];
		}
		return a;
	}
}