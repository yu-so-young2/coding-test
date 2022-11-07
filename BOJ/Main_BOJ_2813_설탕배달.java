

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2813_설탕배달 {
	static int min;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		
		boolean find = false;
		for (int i = N/5; i >= 0; i--) {
			int current = N;
			//i: 5키로 봉지 개수
			current -= i*5;
			int j = current/3; //j: 3키로 봉지 개수
			current -= j*3;
			if(!find && current == 0) find = true;
			if(find) {
				min = Math.min(min, i+j);
			}
		}
		if(!find) min = -1;
		
		System.out.println(min);
	}
}