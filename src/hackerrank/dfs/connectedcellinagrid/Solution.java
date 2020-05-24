package hackerrank.dfs.connectedcellinagrid;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
    	n = grid.length;
    	m = grid[0].length;
    	int ret = dfsAll(n, m, grid);
    	return ret;
    }

    static int n,m;
    static boolean[][] visited;
    private static int dfsAll(int n, int m, int[][] grid) {
    	visited = new boolean[n][m];
    	int ret = 0;
    	for(int r = 0; r < n; r++) {
    		for(int c = 0; c < m; c++) {
    			if(!visited[r][c] && grid[r][c]==1) {
    				ret = Math.max(ret, dfs(r, c, grid));
    			}
    		}
    	}
		return ret;
	}

    static int[][] dt = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
	private static int dfs(int r, int c, int[][] grid) {
		// TODO Auto-generated method stub
		visited[r][c] = true;
		int ret = 1;
		for(int[] d : dt) {
			int nr = r+d[0];
			int nc = c+d[1];
			if(nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc]==1 && !visited[nr][nc]) {
				ret += dfs(nr,nc,grid);
			}
		}
		return ret;
	}

	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
