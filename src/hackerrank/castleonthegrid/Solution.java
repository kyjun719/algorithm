package hackerrank.castleonthegrid;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
    	Queue<Node> q = new LinkedList<>();
    	q.add(new Node(startX, startY,0,null));
    	boolean[][] visited = new boolean[grid.length][grid.length];
    	int ret = -1;
    	while(!q.isEmpty()) {
    		Node node = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = node.x;
        		int ny = node.y;
        		
    			while(true) {    				
    				nx += dt[i][0];
        			ny += dt[i][1];
        			
        			if(isInBound(nx,ny,grid.length) && canGo(nx,ny,grid)) {
        				if(nx == goalX && ny == goalY) {
        	    			ret = node.cnt+1;
        	    			break;
        	    		} else {
        	    			if(!visited[nx][ny]) {
                				//System.out.println(nx+","+ny+">>"+(node.dir==dir[i]?node.cnt:node.cnt+1)+","+dir[i]);
                				q.add(new Node(nx,ny,node.cnt+1,dir[i]));
                				visited[nx][ny]= true;
                			}
        	    		}
        			} else {
        				break;
        			}
    			}
    		}
    		
    		if(ret >= 0) {
    			break;
    		}
    	}
    	
    	return ret;
    }
    
    private static boolean canGo(int nx, int ny, String[] grid) {
		return grid[nx].charAt(ny) == '.';
	}

	private static boolean isInBound(int x, int y, int n) {
		return (x >= 0) && (x < n) && (y >= 0) && (y < n);
	}

	static String[] dir = {"S","N","W","E"};
    static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}};
    static class Node {
    	int cnt,x,y;
    	String dir = null;
    	public Node(int x, int y, int cnt, String dir) {
    		this.x = x;
    		this.y = y;
    		this.cnt = cnt;
    		this.dir = dir;
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
