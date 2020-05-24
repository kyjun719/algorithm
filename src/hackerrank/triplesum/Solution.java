package hackerrank.triplesum;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
    	Set<Integer> setA = new HashSet<>();
    	Set<Integer> setB = new HashSet<>();
    	Set<Integer> setC = new HashSet<>();
    	
    	for(int tmp : a) {
    		setA.add(tmp);
    	}
    	for(int tmp : b) {
    		setB.add(tmp);
    	}
    	for(int tmp : c) {
    		setC.add(tmp);
    	}
    	
    	Integer[] tmpA = setA.toArray(new Integer[0]);
    	Integer[] tmpB = setB.toArray(new Integer[0]);
    	Integer[] tmpC = setC.toArray(new Integer[0]);
    	
    	Arrays.sort(tmpA);
    	Arrays.sort(tmpB);
    	Arrays.sort(tmpC);

    	long ret = 0;
    	int cnt_a = 0;
    	int cnt_c = 0;
    	for(int i = 0; i < tmpB.length; i++) {
    		if(i>0 && tmpB[i-1]==tmpB[i]) {
    			continue;
    		}
    		
    		int val = (int) tmpB[i];
    		while(cnt_a < tmpA.length && val >= (int) tmpA[cnt_a]) {
    			cnt_a++;
    		}
    		while(cnt_c < tmpC.length && val >= (int) tmpC[cnt_c]) {
    			cnt_c++;
    		}
    		//System.out.println(cnt_a+","+cnt_c+">>"+val);
    		ret += (long)cnt_a*cnt_c;
    	}
    	
    	return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
