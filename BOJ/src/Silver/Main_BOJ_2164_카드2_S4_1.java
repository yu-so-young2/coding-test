package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 백준 2164. 카드2
 * 큐 단순 이용
 * 
 */

public class Main_BOJ_2164_카드2_S4_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		while(q.size()>1) {
			q.remove();
			q.add(q.remove());
		}
		
		System.out.println(q.remove());
		
	}//main
}
