package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 15661. 링크와 스타트
 */

public class Main_BOJ_15661_링크와스타트_S1 {
	public static int minDiff;
	public static int N; //식재료 개수
	public static int[][] synergy; //각 재료의 시너지 값 저장할 배열
	public static boolean[] temp; //조합 완료 시 뽑힌 재료의 인덱스를 true로 변경할 배열
	
	public static void subset(int index) {
		//부분집합 뽑았으면 해당 사람 기반으로 능력치 계산
		if(index == N) {
			//뽑은 것 기준으로
			int score_A=0, score_B=0;
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp.length; j++) {
					if(temp[i] && temp[j]) { //A의 재료면 A요리
						score_A += synergy[i][j];
					}
					else if(!temp[i] && !temp[j])//B의 재료면 B요리
						score_B += synergy[i][j];
				}
			}
			
			//두 개의 차이 계산 및 갱신
			minDiff = Math.min(minDiff, Math.abs(score_A-score_B));
			return;
		}
		
		//현재 인덱스 뽑거나 안 뽑거나
		temp[index] = true;
		subset(index+1);
		temp[index] = false;
		subset(index+1);

		
	}
	
	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
			//initialize synergy map
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			//탐색
			temp = new boolean[N];
			minDiff = Integer.MAX_VALUE;
			subset(0);
		System.out.println(minDiff);
	}//main
}