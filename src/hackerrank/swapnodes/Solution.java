package hackerrank.swapnodes;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
	static class Node {
		Node left, right;
		int idx;
		int depth = 1;
		Node(int idx) {
			this.idx = idx;
		}
		void setLeft(Node left) {
			if(left != null) {
				left.depth=depth+1;
			}
			this.left = left;
		}
		void setRight(Node right) {
			if(right != null) {
				right.depth=depth+1;
			}
			this.right = right;
		}
		@Override
		public String toString() {
			String ret = "";
			if(left != null) {
				ret = left.toString()+" ";
			}
			ret += idx;
			if(right != null) {
				ret += " "+right.toString();
			}
			return ret;
		}
	}
    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        /*
         * Write your code here.
         */
    	int n = indexes.length;
    	Node[] nodeArr = new Node[n+1];
    	for(int i = 0; i < n; i++) {
    		nodeArr[i] = new Node(i+1);
    	}
    	
    	for(int i = 0; i < n; i++) {
    		int left = indexes[i][0];
    		int right = indexes[i][1];
    		if(left > 0) {
    			nodeArr[i].setLeft(nodeArr[left-1]);
    		}
    		if(right > 0) {
    			nodeArr[i].setRight(nodeArr[right-1]);
    		}
    	}
    	
    	//System.out.println(nodeArr[0]);
    	//System.out.println(nodeArr[0].left+">>"+nodeArr[0].right);
    	int[][] ret = new int[queries.length][n];
    	for(int i = 0; i < queries.length; i++) {
    		int swapDepth = queries[i];
    		
    		for(int j = 0; j < n; j++) {
    			if(nodeArr[j].depth%swapDepth == 0) {
    				Node tmp = nodeArr[j].right;
    				nodeArr[j].setRight(nodeArr[j].left);
    				nodeArr[j].setLeft(tmp);
    			}
    		}
    		
    		//System.out.println(nodeArr[0].left+">>"+nodeArr[0].right);
    		ret[i] = Arrays.stream(nodeArr[0].toString().split(" ")).mapToInt(Integer::parseInt).toArray();
    	}
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
