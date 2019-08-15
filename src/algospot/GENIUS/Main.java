package algospot.GENIUS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @see https://algospot.com/judge/problem/read/GENIUS
 * @author jun
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
	//i번곡이 끝난 후 j번 곡이 나올 확률
	static double[][] arr = new double[50][50];
	//노래 길이 배열
	static int[] songLen = new int[50];
	//n:전체 곡수, m:좋아하는 곡수,k:해당 분뒤 나오는 노래가 좋아하는 노래일 확률을 구해야함
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
				 
				 songLen = Arrays.stream(bf.readLine().split(" "))
						 .mapToInt(Integer::parseInt)
						 .toArray();
				 for(int i = 0; i < n; i++) {
					 arr[i] = Arrays.stream(bf.readLine().split(" "))
							 .mapToDouble(Double::parseDouble)
							 .toArray();
				 }
				 
				 double[] result = getResult2();
				 //double[] result = getResult1();
				 
				 String[] outArr = bf.readLine().split(" ");
				 String ret = "";
				 for(String tmp : outArr) {
					 ret += String.format("%.8f", result[Integer.parseInt(tmp)]) + " ";
				 }
				 System.out.println(ret.substring(0, ret.length() - 1));
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	
	//시간초과 발생
	static double[] getResult1() {
		double[][] cache = new double[5][50];
		cache[0][0] = 1;
		//전체 노래에 대해 k분까지 반복
		for(int time = 1; time <= k; time++) {
			for(int j = 0; j < n; j++) {
				double prop = 0;
				//time에j번째 노래가 나올 확률 = sum(이전노래{last}가 재생될 확률*last다음 j번째 노래가 나올 확률)
				for(int last = 0; last < n; last++) {
					prop += cache[(time-songLen[last] + 5)%5][last]*
							arr[last][j];
				}
				cache[time%5][j] = prop;
			}
		}
		 
		double[] result = new double[50];
		for(int i = 0; i < n; i++) {
			//k분에 i번째 노래가 나올 확률 = sum({k-노래길이}에 i번째 노래가 나올확률)
			for(int start = k-songLen[i]+1; start <= k; start++) {
				result[i] += cache[start%5][i];
			}
		}
		 
		return result;
	}
	
	//확률에 대한 행렬
	private static class SquareMatrix {
		double[][] matrix;
		int size;
		//n*n의 행렬 생성자
		SquareMatrix(int n){
			size = n;
			matrix = new double[n][n];
		}
		
		//단위 행렬
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
		
		//행렬 곱
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
		
		//행렬의 거듭제곱
		//A^(2k+1)=(A^2)k*A
		//A^2k=(A^2)k
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
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < size; i++) {
				sb.append(Arrays.toString(matrix[i]));
				sb.append("\n");
			}
			return sb.toString();
		}
	}
	
	/*
	 * start(time,song)=sum(start(start(time-L[prev],song)*T[prev][song]
	 * -> time분에 song번째 노래가 시작할 확률 = sum({4-이전 노래길이}에 song번째 노래가 나올확률*
	 * 													이전노래 다음 song노래가 나올확률)
	 * C_time = [start(time-3,0)~start(time-3,n-1),
	 * 				start(time-2,0)~start(time-2,n-1),
	 * 				start(time-1,0)~start(time-1,n-1),
	 * 				start(time,0)~start(time,n-1)]
	 * C_0 = [0(0~n-1),
	 * 			0(0~n-1),
	 * 			0(0~n-1),
	 * 			1,0(1~n-1)]
	 */
	//C_time : 4n*1, W : 4n*4n
	//C_time+1 = W*C_time
	static double[] getResult2() {
		SquareMatrix W = new SquareMatrix(4*n);
		//C의 n~4n-1의 값은 그대로 가져옴 
		for(int i = 0; i < 3*n; i++) {
			W.matrix[i][i+n] = 1;
		}
		//start(time,song)=sum(start(start(time-L[prev],song)*T[prev][song]
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				W.matrix[3*n+i][(4-songLen[j])*n+j] = arr[j][i];
			}
		}
		SquareMatrix Wk = W.pow(k);
		double[] ret = new double[n];
		for(int song = 0; song < n; song++) {
			//song번째 노래가 시작한 시간의 확률을 모두 더함
			//C_time = (W^k)*C_0이므로 W[0~4n-1,3n]*C_0
			for(int start = 0; start < songLen[song]; start++) {
				ret[song] += Wk.matrix[(3-start)*n+song][3*n];
			}
		}
		return ret;
	}
}
