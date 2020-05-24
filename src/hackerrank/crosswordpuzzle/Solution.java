package hackerrank.crosswordpuzzle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the crosswordPuzzle function below.
    static String[] crosswordPuzzle(String[] crossword, String words) {
    	String[] board = new String[crossword.length];
    	//for sample testcase 2
    	boolean needChange = false;
    	for(int i = 0; i < crossword.length; i++) {
    		char[] tmp = crossword[i].toCharArray();
    		for(int j = 0; j < crossword[i].length(); j++) {
    			if(crossword[i].charAt(j) == 'X') {
    				needChange = true;
    				tmp[j] = '+';
    			}
    		}
    		board[i] = new String(tmp);
    	}
    	List<String> wordList = Arrays.asList(words.split(";"));
    	
    	isFinish = false;
    	ret = board;
    	solve(board, wordList, 0);
    	//for sample testcase 2
    	if(needChange) {
    		board = new String[ret.length];
        	for(int i = 0; i < ret.length; i++) {
        		char[] tmp = ret[i].toCharArray();
        		for(int j = 0; j < crossword[i].length(); j++) {
        			if(ret[i].charAt(j) == '+') {
        				tmp[j] = 'X';
        			}
        		}
        		board[i] = new String(tmp);
        	}
        	return board;
    	} else {
    		return ret;
    	}
    }
    
    static String[] ret;
    static boolean isFinish = false;;
    private static void solve(String[] crossword, List<String> wordList, int cnt) {
    	if(isFinish) {
    		return;
    	}
    	
    	int x = cnt%crossword.length;
    	int y = cnt/crossword.length;
    	
    	if(wordList.size() == 0) {
    		isFinish = true;
    		ret = crossword;
    		return;
    	}
    	
    	if(crossword[y].charAt(x) == '-') {
    		WordInfo info = findWordInfo(crossword, y, x);
    		//System.out.println(y+","+x+">>"+info);
    		for(String word : wordList) {
        		if(canInsert(crossword, info, word)) {
        			//System.out.println(info+">>"+word);
        			String[] next = insertWord(crossword, info, word);
        			//showAnswer(next);
        			List<String> tmpList = new ArrayList<>(wordList);
        			tmpList.remove(word);
        			
        			solve(next, tmpList, cnt+1);
        		}
        	}
    	} else {
    		solve(crossword, wordList, cnt+1);
    	}
	}

	private static String[] insertWord(String[] crossword, WordInfo info, String word) {
		String[] ret = Arrays.copyOf(crossword, crossword.length);
		// TODO Auto-generated method stub
		if(info.sy == info.ey) {
			char[] tmp = ret[info.sy].toCharArray();
			for(int i = info.sx; i <= info.ex; i++) {
				tmp[i] = word.charAt(i-info.sx);
			}
			ret[info.sy] = new String(tmp); 
		} else {
			for(int i = info.sy; i <= info.ey; i++) {
				char[] tmp = ret[i].toCharArray();
				tmp[info.sx] = word.charAt(i-info.sy);
				ret[i] = new String(tmp);
			}
		}
		return ret;
	}
	
	private static void showAnswer(String[] crossword) {
		System.out.println("========================");
		for(String tmp : crossword) {
			System.out.println(tmp);
		}
		System.out.println("========================");
	}

	private static boolean canInsert(String[] crossword, WordInfo info, String word) {
		if(word.length() != info.len) {
			return false;
		}
		
		//해당칸에 단어 채울수 있는지 검색
		for(int y = info.sy; y <= info.ey; y++) {
			for(int x = info.sx; x <= info.ex; x++) {
				//빈칸인경우 넘어감
				if(crossword[y].charAt(x) == '-') {
					continue;
				}
				//글자가 차있는경우 해당 위치의 글자와 동일한지 확인
				if(crossword[y].charAt(x) != '+') {
					int cnt = info.sy==info.ey?(x-info.sx):(y-info.sy);
					if(word.charAt(cnt) != crossword[y].charAt(x)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static WordInfo findWordInfo(String[] crossword, int y, int x) {
		WordInfo info = new WordInfo();
		//가로줄인 경우
		if((x > 0 && crossword[y].charAt(x-1)=='-') || (x < crossword[y].length()-1 && crossword[y].charAt(x+1)=='-')) {
			int sx = x;
			int ex = x;
			String str = crossword[y];
			boolean moved = true;
			while(moved) {
				moved = false;
				if(sx >= 0) {
					if(str.charAt(sx) == '-' || str.charAt(sx) != '+') {
    					sx--;
    					moved = true;
    				}
				}
				if(ex < str.length()) {
					if(str.charAt(ex) == '-' || str.charAt(ex) != '+') {
    					ex++;
    					moved = true;
    				}
				}
			}
			info.sx = sx+1;
			info.ex = ex-1;
			info.sy = y;
			info.ey = y;
			info.len = ex-sx-1;
		} else {
			//세로줄인 경우
			int sy = y;
			int ey = y;
			boolean moved = true;
			while(moved) {
				moved = false;
				if(sy >= 0) {
					if(crossword[sy].charAt(x) == '-' || crossword[sy].charAt(x) != '+') {
    					sy--;
    					moved = true;
    				}
				}
				if(ey < crossword.length) {
					if(crossword[ey].charAt(x) == '-' || crossword[ey].charAt(x) != '+') {
    					ey++;
    					moved = true;
    				}
				}
			}
			info.sy = sy+1;
			info.ey = ey-1;
			info.sx = x;
			info.ex = x;
			info.len = ey-sy-1;
		}
		return info;
	}

	static class WordInfo {
    	int sx, sy, ex, ey, len;
    	String word;
    	@Override
    	public String toString() {
    		return "("+sy+","+sx+")~("+ey+","+ex+")::"+len;
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] crossword = new String[10];

        for (int i = 0; i < 10; i++) {
            String crosswordItem = scanner.nextLine();
            crossword[i] = crosswordItem;
        }

        String words = scanner.nextLine();

        String[] result = crosswordPuzzle(crossword, words);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
