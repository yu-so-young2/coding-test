package Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 11660. 구간합구하기5
 * 2차원 배열 시작인덱스~끝인덱스의 구간합 구하기
 * 누적합, DP, 동적 프로그래밍
 */

public class Main_BOJ_11660_구간합구하기5_S1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //num of Numbers
		int M = Integer.parseInt(st.nextToken()); //num of Sum
		
		int[][] numSum = new int[N+1][N+1];
		int[] start = new int[2];
		int[] end = new int[2];
		int prefixSum;
		
		for (int i = 0; i < numSum.length; i++) {
			numSum[0][i] = 0;
			numSum[i][0] = 0;
		}

		//initialize matrix, cumulative sum
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				numSum[i+1][j+1] = Integer.parseInt(st.nextToken());
				//cumulative matrix
				numSum[i+1][j+1] = numSum[i][j+1] + numSum[i+1][j] + numSum[i+i][j+j]- numSum[i][j];
			}
		}
		
		//prefix sum
		prefixSum = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken()); 
			start[1] = Integer.parseInt(st.nextToken());
			end[0] = Integer.parseInt(st.nextToken()); 
			end[1] = Integer.parseInt(st.nextToken());
			
			prefixSum = numSum[end[0]][end[1]] - numSum[start[0]-1][end[1]] - numSum[end[0]][start[1]-1] + numSum[start[0]-1][start[1]-1];
			
			bw.write(prefixSum+"\n");
		}//for each testcase
		bw.close();
		
	}//main
}