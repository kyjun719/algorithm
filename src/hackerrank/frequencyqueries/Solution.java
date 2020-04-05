package hackerrank.frequencyqueries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
    	//숫자의 갯수 저장
    	HashMap<Integer, Integer> numCntMap = new HashMap<>();
    	//해당 갯수를 가진 숫자카운트값 저장
    	HashMap<Integer, Integer> cntMap = new HashMap<>();
    	List<Integer> ret = new ArrayList<>();
    	
    	for(List<Integer> query : queries) {
    		int cmd = query.get(0);
    		int val = query.get(1);
    		if(cmd == 1) {
    			//insert
    			int cnt = numCntMap.get(val)==null?0:numCntMap.get(val);
    			
    			if(cntMap.get(cnt) != null) {
    				cntMap.put(cnt, Math.max(cntMap.get(cnt)-1, 0));
    			}
    			
    			cnt++;
    			numCntMap.put(val, cnt);
    			if(cntMap.get(cnt) != null) {
    				cntMap.put(cnt, cntMap.get(cnt)+1);
    			} else {
    				cntMap.put(cnt, 1);
    			}
    		} else if(cmd == 2) {
    			//delete
    			if(numCntMap.get(val) != null) {
    				int cnt = numCntMap.get(val);
        			
        			if(cntMap.get(cnt) != null) {
        				cntMap.put(cnt, Math.max(cntMap.get(cnt)-1, 0));
        			}
        			
        			cnt = Math.max(cnt-1, 0);
        			numCntMap.put(val, cnt);
        			if(cnt > 0) {
        				cntMap.put(cnt, cntMap.get(cnt)+1);
        			}
    			}
    		} else {
    			//check length
    			ret.add(new Integer((cntMap.get(val)!=null && cntMap.get(val)!=0)?1:0));
    		}
    	}
    	
    	return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

