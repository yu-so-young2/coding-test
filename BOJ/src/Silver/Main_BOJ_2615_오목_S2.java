package Silver;

import java.util.Scanner;

/*
 * 백준 2615. 오목
 * 맵이 주어졌을 때 오목을 만족하는지 확인
 * 2차원 배열 탐색, 구현, 브루트포스 알고리즘
 * 
 */

public class Main_BOJ_2615_오목_S2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[19][19];
		
		boolean win = false; //오목 찾기
		int[] winner = new int[3]; //winner[0]: 승리 색 기억, winner[1], winner[2]: 승리 위치 기억
		
		//map 채우기
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//map 순회
	travel:	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				//바둑알이 없다면 넘어가
				if(map[i][j]==0) continue;
				
				winner[0] = map[i][j]; //색 기억
				winner[1] = i+1; //위치 기억
				winner[2] = j+1;
				
				//바둑알이 있다면 확인(오른쪽, 아래쪽, 대각선오른아래, 대각선오른위)
				//1. 오른쪽
				int d1 = 1, d2 = 1;
				while((j+d1)<19 && map[i][j+d1] == winner[0]) {
					d1++;
				}
				if(d1==5) { //5목 달성 시 반대방향 6목이상인지 확인
					while((j-d2)>=0 && map[i][j-d2] == winner[0]){//왼쪽도 검사
						d2++;
					}					
					if(d1+d2==6) {//5목 확인, 현재 알 승리
						win = true;
						break travel;
					}
				}
				
				//2. 아래쪽
                d1 = 1; d2 = 1;
				while((i+d1)<19 && map[i+d1][j] == winner[0]) {
					d1++;
				}
				if(d1==5) { //5목 달성 시 반대방향 6목이상인지 확인
					while((i-d2)>=0 && map[i-d2][j]==winner[0]) {//위쪽도 검사
						d2++;
					}
					if(d1+d2==6) {//5목 확인, 현재 알 승리
						win = true;
						break travel;
					}
				}
				
				//3. 대각선 오른쪽 아래
				d1 = 1; d2 = 1;
				while((i+d1)<19 && (j+d1)<19 && map[i+d1][j+d1] == winner[0]) {
					d1++;
				}
				if(d1==5) { //5목 달성 시 반대방향 6목이상인지 확인
					while((i-d2)>=0 && (j-d2)>=0 && map[i-d2][j-d2] == winner[0]) { //대각선 왼쪽 위도 검사
						d2++;
					}
					
					if(d1+d2==6) {//5목 확인, 현재 알 승리
						win = true;
						break travel;
					}
				}
				
				//4. 대각선 오른쪽 위
				d1 = 1; d2 = 1;
				while((i-d1)>=0 && (j+d1)<19 && map[i-d1][j+d1]== winner[0]) {
					d1++;
				}
				if(d1==5) { //5목 달성 시 반대방향 6목이상인지 확인
					while((i+d2)<19 && (j-d2)>=0 && map[i+d2][j-d2] == winner[0]) { //대각선 왼쪽 아래도 검사
						d2++;
					}
					
					if(d1+d2==6) {//5목 확인, 현재 알 승리
						win = true;
						break travel;
					}
				}				
			}
		}

	
		//result print
		if(win) {//승부 결정
			System.out.println(winner[0]);//승부 결과
			System.out.println(winner[1] + " " + winner[2]);//승리 바둑알의 가장 상단-왼쪽 알의 인덱스			
		}
		else {//승부 안남
			System.out.println(0);
		}
		
	}

}