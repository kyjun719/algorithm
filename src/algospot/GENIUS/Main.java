package algospot.GENIUS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/GENIUS
 * @author jun

1
3 6 3
4 4 2
0.18 0.40 0.42
0.15 0.46 0.39
0.58 0.23 0.19
0 1 2

 * input
3
3 6 3
4 4 2
0.18 0.40 0.42
0.15 0.46 0.39
0.58 0.23 0.19
0 1 2
4 10 4
1 3 2 4
0.26 0.07 0.49 0.18
0.21 0.33 0.15 0.31
0.41 0.20 0.38 0.01
0.28 0.31 0.18 0.230
2 0 3 1
4 1000 4
4 3 4 4
0.08 0.47 0.12 0.33
0.10 0.02 0.39 0.49
0.08 0.33 0.35 0.24
0.14 0.19 0.58 0.09
1 3 2 0

 * output
0.42360000 0.49660000 0.07980000 
0.31060929 0.13791635 0.26756048 0.28391388 
0.18648004 0.28409359 0.42243515 0.10699122 
 */
public class Main {
	static double[][] cache;
	static double[][] arr = new double[50][50];	
	static int[] songLen = new int[50];
	static int n,m,k;
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		 try {
			 int c = Integer.parseInt(bf.readLine());
			 for(int tc = 0; tc < c; tc++) {
				 String[] info = bf.readLine().split(" ");
				 n = Integer.parseInt(info[0]);
				 k = Integer.parseInt(info[1]);
				 m = Integer.parseInt(info[2]);
				 
				 cache = new double[5][50];
				 songLen = Arrays.stream(bf.readLine().split(" "))
						 .mapToInt(Integer::parseInt)
						 .toArray();
				 for(int i = 0; i < n; i++) {
					 arr[i] = Arrays.stream(bf.readLine().split(" "))
							 .mapToDouble(Double::parseDouble)
							 .toArray();
				 }
				 
				 double[] result = getResult2();
				 
				 String[] outArr = bf.readLine().split(" ");
				 String ret = "";
				 for(String tmp : outArr) {
					 ret += result[Integer.parseInt(tmp)] + " ";
				 }
				 System.out.println(ret.substring(0, ret.length() - 1));
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	static double[] getResult() {
		cache[0][0] = 1;
		 for(int time = 1; time <= k; time++) {
			 for(int j = 0; j < n; j++) {
				 double prop = 0;
				 for(int last = 0; last < n; last++) {
					 prop += cache[(time-songLen[last] + 5)%5][last]*
							 arr[last][j];
				 }
				 cache[time%5][j] = prop;
			 }
		 }
		 
		 double[] result = new double[50];
		 for(int i = 0; i < n; i++) {
			 for(int start = k-songLen[i]+1; start <= k; start++) {
				 result[i] += cache[start%5][i];
			 }
		 }
		 
		 return result;
	}
	
	private static class SquareMatrix {
		double[][] matrix;
		int size;
		SquareMatrix(int n){
			size = n;
			matrix = new double[n][n];
		}
		
		SquareMatrix identity(int n) {
			SquareMatrix id = new SquareMatrix(n);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(i == j) {
						id.matrix[i][j] = 1;
					} else {
						id.matrix[i][j] = 0;
					}
				}
			}
			return id;
		}
		
		SquareMatrix mutiply(SquareMatrix b) {
			SquareMatrix result = new SquareMatrix(size);
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					for(int k = 0; k < size; k++) {
						result.matrix[j][k] += this.matrix[j][i]*b.matrix[i][k];
					}
				}
			}
			return result;
		}
		
		SquareMatrix pow(int k) {
			if(k == 0) {
				return identity(size);
			}
			if(k%2 == 1) {
				return pow(k-1).mutiply(this);
			}
			
			SquareMatrix half = pow(k/2);
			return half.mutiply(half);
		}
	}
	
	//C_time+1 = W*C_time
	static double[] getResult2() {
		SquareMatrix W = new SquareMatrix(4*n);
		//C_time+1(start(time-3,song))=C_time(start(time-2,song))
		for(int i = 0; i < 3*n; i++) {
			W.matrix[i][i+n] = 1;
		}
		//C_time_1(start(time,song))=sum(C_time(start(time-L[prev],song)*T[prev][song]
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				W.matrix[3*n+i][(4-songLen[j])*n+j] = arr[j][i];
			}
		}
		SquareMatrix Wk = W.pow(k);
		double[] ret = new double[n];
		for(int song = 0; song < n; song++) {
			for(int start = 0; start < songLen[song]; start++) {
				ret[song] += Wk.matrix[(3-start)*n+song][3*n];
			}
		}
		return ret;
	}
}
