package Silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 11659. 구간합구하기4
 * 누적합, DP, 동적프로그래밍
 * 
 */

public class Main_BOJ_11659_구간합구하기4_S2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //num of Numbers
		int T = Integer.parseInt(st.nextToken()); //num of Sum
		
		int[] numSum = new int[N+1];
		numSum[0] = 0;
		
		//initialize cumulative matrix
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numSum[i+1] = Integer.parseInt(st.nextToken());
			numSum[i+1] = numSum[i]+numSum[i+1];
		}

		//find prefixSum
		int start = 0;
		int end = 0;
		int prefixSum = 0;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()); //num of Numbers
			end = Integer.parseInt(st.nextToken()); //num of Sum
			prefixSum = numSum[end] - numSum[start-1];
			
			bw.write(prefixSum+"\n");
		}//for each testcase
		bw.close();
		
	}//main
}