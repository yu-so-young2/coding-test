package Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10163_색종이_B1 {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int[][] map = new int[1001][1001];
		int N = Integer.parseInt(br.readLine()); //number of paper
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int start_i = Integer.parseInt(st.nextToken());
			int start_j = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			//색종이로 덮기
			for (int di = 0; di < w; di++) {
				for (int dj = 0; dj < h; dj++) {
					map[start_i+di][start_j+dj] = n;
				}
			}
		}
		
		int[] area = new int[N+1];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				area[map[i][j]]++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(area[i]);
		}
	}
}
