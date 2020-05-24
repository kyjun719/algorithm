package hackerrank.balancedforest;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the balancedForest function below.
    static long balancedForest(int[] c, int[][] edges) {
        int n = c.length;
        adj = new ArrayList[c.length];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++) {
            int a = edges[i][0]-1;
            int b = edges[i][1]-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        
        visited = new boolean[n];
        sum = new long[n];
        dfs(c, 0);

        ret = Long.MAX_VALUE;
        Arrays.fill(visited, false);
        include = new HashSet<>();
        exclude = new HashSet<>();
        solve(0);
        
        if(ret == Long.MAX_VALUE) {
        	ret = -1;
        }
        
        return ret;
    }
    
    private static void solve(int now) {
        visited[now] = true;

        long[] retCandidate = {3*sum[now]-sum[0], (sum[0]-sum[now])/2-sum[now]};
        if(retCandidate[0] >= 0) {
            if(exclude.contains(sum[now]) || include.contains(2*sum[now])) {
                ret = Math.min(ret, retCandidate[0]);
            }
            
            if(exclude.contains(sum[0]-2*sum[now]) || include.contains(sum[0]-sum[now])) {
            	ret = Math.min(ret, retCandidate[0]);
            }
        }
        
        if(((sum[0]-sum[now])%2 == 0) && (retCandidate[1] >= 0)) {
        	if(exclude.contains((sum[0]-sum[now])/2) || include.contains((sum[0]+sum[now])/2)) {
        		ret = Math.min(ret, retCandidate[1]);
        	}
        }
        
        include.add(sum[now]);
        for(int next : adj[now]) {
            if(!visited[next]) {
                solve(next);
            }
        }
        include.remove(sum[now]);
        exclude.add(sum[now]);
    }

    static HashSet<Long> include, exclude;
    static long ret;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static long[] sum;
    
    private static long dfs(int[] c, int now) {
        visited[now] = true;
        long ret = c[now];
        
        for(int next : adj[now]) {
            if(!visited[next]) {
                ret += dfs(c, next);
            }
        }
        sum[now] = ret;
        return ret;
    }

    private static final FastReader scanner = new FastReader();

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n - 1][2];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            long result = balancedForest(c, edges);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        //scanner.close();
    }
    
    static class FastReader { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() { 
            //br = new BufferedReader(new InputStreamReader(System.in));
        	try {
        		String path = System.getProperty("user.dir");
            	path += "\\src\\hackerrank\\balancedforest\\input.txt";
				br = new BufferedReader(new FileReader(new File(path)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
  
        String next() { 
            while (st == null || !st.hasMoreElements()) { 
                try { 
                    st = new StringTokenizer(br.readLine()); 
                } catch (IOException  e) { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() { 
            return Integer.parseInt(next()); 
        } 
  
        String nextLine() { 
            String str = ""; 
            try { 
                str = br.readLine(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}
