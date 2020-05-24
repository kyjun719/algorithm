package hackerrank.poisonousplants;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
    	int ret = 0;
    	int n = p.length;
    	ArrayList<Stack<Integer>> list = new ArrayList<>(); 
    	int idx = 0;
    	list.add(new Stack<>());
    	list.get(idx).add(p[0]);
    	for(int i = 1; i < n; i++) {
    		if(list.get(idx).peek() < p[i]) {
    			idx++;
    			list.add(new Stack<>());
    		}
    		list.get(idx).add(p[i]);
    	}
    	
    	while(true) {
    		if(list.size() == 1) {
    			break;
    		}
    		
    		ArrayList<Stack<Integer>> next = new ArrayList<>();
    		Stack<Integer> tmp = list.get(0);
    		for(int i = 1; i < list.size(); i++) {
    			list.get(i).remove(0);
    			
    			if(list.get(i).size() == 0) {
    				continue;
    			}
    			if(tmp.peek() < list.get(i).firstElement()) {
    				next.add(tmp);
    				tmp = list.get(i);
    			} else {
    				tmp.addAll(list.get(i));
    			}
    		}
    		if(tmp.size() > 0) {
    			next.add(tmp);
    		}
    		list = next;
    		ret++;
    	}

    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
