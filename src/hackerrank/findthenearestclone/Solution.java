package hackerrank.findthenearestclone;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        // solve here
    	ArrayList<Integer>[] adj = new ArrayList[graphNodes];
    	for(int i = 0; i < adj.length; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < graphFrom.length; i++) {
    		int a = graphFrom[i]-1;
    		int b = graphTo[i]-1;
    		adj[a].add(b);
    		adj[b].add(a);
    	}
    	
    	int[] ret = new int[graphNodes];
    	Arrays.fill(ret, -1);
    	boolean[] visited = new boolean[graphNodes];
    	Queue<Integer> q = new LinkedList<>();
    	for(int i = 0; i < graphNodes; i++) {
    		if(ids[i] == val) {
    			q.add(i);
    			ret[i] = 0;
    			visited[i] = true;
    		}
    	}
    	if(q.size() <= 1) {
    		return -1;
    	}
    	
    	int ans = Integer.MAX_VALUE;
    	while(!q.isEmpty()) {
    		int idx = q.poll();
    		
    		for(int next : adj[idx]) {
    			if(visited[next]) {
    				ans = ret[idx]+ret[next]+1;
    				break;
    			} else {
    				visited[next] = true;
    				ret[next] = ret[idx]+1;
    				q.add(next);
    				adj[next].remove((Object)idx);
    			}
    		}
    		
    		if(ans < Integer.MAX_VALUE) {
    			break;
    		}
    	}
    	
    	if(ans == Integer.MAX_VALUE) {
    		ans = -1;
    	}
    	return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
