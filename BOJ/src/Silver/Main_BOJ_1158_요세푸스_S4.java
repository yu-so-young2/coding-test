package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_1158_요세푸스_S4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N  = Integer.parseInt(st.nextToken());
		int k  = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> people = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			people.add(i);
		}
		
		int index = k;
		while(people.size() > 0) {
			while(index > people.size()) index -= people.size();
			sb.append(people.remove(index-1));
			index += k-1;
			if(people.size() > 0) sb.append(", ");
		}
		
		System.out.println("<"+sb.toString()+">");

	}
}
