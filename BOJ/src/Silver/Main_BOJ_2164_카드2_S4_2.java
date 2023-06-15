package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 백준 2164. 카드2
 * 군수열 이용, 큐X
 * 
 * 답 규칙성: 1 / 2 / 2 4 / 2 4 6 8 / 2 4 6 8 10 12 14 16 / ...
 * ==> 군수열 문제
 * ==> N이 몇 군의 몇 번째 항인지만 찾으면 되는 문제
 * 
 * 제 x 군까지의 항의 개수: 2^x개
 * 몇 군인지 찾기: N이하의 가장 큰 2의 제곱수 2^x 찾아서 그때의 x값이 전 군
 * 몇 항인지 찾기: idx = N - 2^x
 * 따라서 N번째 항: 2의 등차수열의 idx번째 항
 */

public class Main_BOJ_2164_카드2_S4_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if(N==1) {
			System.out.println(1);
			return;
		}
		
		//1. N 이하의 가장 큰 2 제곱수 찾기
		int n = 1;
		while(N > n) {
			n *= 2;
		}
		n /= 2;
		
		//2. 몇 번째 항인지 찾기
		System.out.println(2*(N-n));		
		
	}//main
}
