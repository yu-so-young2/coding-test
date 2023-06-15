package Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * stack 2개 사용
 * 시간초과
 * 
 */

public class Main_BOJ_2493_탑_G5_fail {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int current;
		int idx;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		//initialize stack
		for (int i = 0; i < N; i++) {
			idx = Math.max(i, 0);
			current = Integer.parseInt(st.nextToken());
			while(!stack1.empty() && stack1.peek() < current) {
				stack2.add(stack1.pop());
				idx--;
			}
			while(!stack2.empty()) {
				stack1.add(stack2.pop());
			}
			stack1.add(current);
			bw.write(idx+" ");
		}
				
		bw.close();
	}//main
}
