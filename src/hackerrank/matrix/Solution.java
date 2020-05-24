package hackerrank.matrix;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
	static class Edge {
		int to, w;
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
		@Override
		public int hashCode() {
			int ret = 31;
			ret = ret*17 + to;
			ret = ret*17 + w;
			return ret;
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof Edge) {
				Edge obj = (Edge) o;
				return obj.to == this.to && obj.w == this.w;
			}
			return false;
		}
	}

    // Complete the minTime function below.
    static int minTime(int[][] roads, int[] machines) {
    	ret = 0;
    	int n = roads.length+1;
    	adj = new ArrayList[n];
    	for(int i = 0; i < n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	boolean[] isMachine = new boolean[n];
    	for(int m : machines) {
    		isMachine[m] = true;
    	}
    	
    	for(int i = 0; i < roads.length; i++) {
    		int a = roads[i][0];
    		int b = roads[i][1];
    		int w = roads[i][2];
    		if(a == 0 && b == 0) {
    			continue;
    		}
    		adj[a].add(new Edge(b,w));
    		adj[b].add(new Edge(a,w));
    	}
    	dfsAll(n, isMachine);
    	
    	return ret;
    }
    
    static int ret;
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    private static void dfsAll(int n, boolean[] isMachine) {
    	visited = new boolean[n];
    	dfs(0, 0, isMachine);
    }
    
    private static int dfs(int idx, int w, boolean[] isMachine) {
    	visited[idx] = true;
    	int max = 0;
    	int sum = 0;
    	for(Edge e : adj[idx]) {
    		if(visited[e.to]) {
    			continue;
    		}
    		
    		int time = dfs(e.to, e.w, isMachine);
    		sum += time;
    		max = Math.max(max, time);
    	}
    	
		//해당 정점이 기계인 경우
    	if(isMachine[idx]) {
    		//해당 정점과 연결된 모든 간선을 끊어야 하므로 답에 더함 
    		ret += sum;
    		//해당 간선에서 끊는데 걸리는 시간 반환
    		return w;
    	} else {
    		//해당 정점과 연결된 정점중 최대값을 제외하고 끊음
    		ret += sum-max;
    		//이전정점과 연결된 값과 해당 정점의 간선의 최대치중 작은값을 끊음
    		return Math.min(w, max);
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(scanner.next());
        
        int k = Integer.parseInt(scanner.next());

        int[][] roads = new int[n - 1][3];

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            
            if(roadsRowItems[0].equals("a")) {
            	i--;
            	k--;
                continue;
            }

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(roads, machines);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
