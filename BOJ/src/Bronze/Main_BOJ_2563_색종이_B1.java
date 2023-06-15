package Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2563_색종이_B1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] paper = new int[100][100];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//색종이만큼 색칠하기
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					paper[x+i][y+j] = 1;
				}
			}
		
		}
		
		//넓이 구하기 
		int area = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				area += paper[i][j];
			}
		}
		System.out.println(area);
	}//main
}
