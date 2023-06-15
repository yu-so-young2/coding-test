package Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 백준 2493. 탑
 * 왼쪽에서 가장 가까이에 있는 나보다 높은 수 찾기
 * O(N2)이긴 해도 불필요한 비교 횟수 줄이며 탐색하므로 시간 줄이기 가능
 */

public class Main_BOJ_2493_탑_G5 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Stack<Integer> nums = new Stack<>(); //현재 레이더 송신할 수 있는 탑 높이
		Stack<Integer> keys = new Stack<>(); //현재 레이더 송신할 수 있는 탑 위치
		keys.push(0); //기본: 없음, 0
		int current; //현재 탑의 높이

		//initialize stack && find my closest
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			current = Integer.parseInt(st.nextToken());
			//현재 탑보다 낮은 탑 잡아먹기
			while(!nums.empty() && current > nums.peek()) {
				nums.pop();
				keys.pop();
			}
			bw.write(keys.peek()+" ");
			//현재 탑 저장
			nums.push(current);
			keys.push(i);
		}
		bw.close();
	}//main
}
