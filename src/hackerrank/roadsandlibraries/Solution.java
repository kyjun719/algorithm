package hackerrank.roadsandlibraries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
    	adj = new ArrayList[n];
    	for(int i = 0; i < n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	for(int[] info : cities) {
    		adj[info[0]-1].add(info[1]-1);
    		adj[info[1]-1].add(info[0]-1);
    	}
    	
    	dfsAll(n);
    	
    	//System.out.println(cnt+","+Arrays.toString(cntArr));
    	if(c_lib <= c_road) {
    		return (long)n*c_lib;
    	}
    	
    	//System.out.println(Arrays.toString(cntArr));
    	long ret = 0;
        for(int i = 0; i < cnt; i++) {
            ret += (cntArr[i]-1)*(long)c_road;
            ret += c_lib;
        }
        
        return ret;
    }
    
    static ArrayList<Integer>[] adj;
    static long[] cntArr;
    static boolean[] visited;
    static int cnt;
    static void dfsAll(int n) {
    	cnt = 0;
    	visited = new boolean[n];
    	cntArr = new long[n];
    	for(int i = 0; i < n; i++) {
    		if(!visited[i]) {
    			/*
    			visited[i] = true;
    			*/
    			dfs(i);
    			cnt++;
    		}
    	}
    }
    
    static void dfs(int idx) {
    	visited[idx] = true;
    	cntArr[cnt]++;
    	//System.out.println(idx+">>"+adj[idx]+","+Arrays.toString(visited));
    	for(int next : adj[idx]) {
    		if(!visited[next]) {
    			//visited[next] = true;
    			//cntArr[cnt]++;
    			dfs(next);
    		}
    	}
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    	String path = System.getProperty("user.dir");
    	path += "\\src\\hackerrank\\roadsandlibraries\\input.txt";
    	scanner = new Scanner(new FileReader(new File(path)));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
