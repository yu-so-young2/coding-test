package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17070_파이프옮기기1_G5 {

	public static class Node {
		int diagonal, horizon, vertical;

		public Node() {
			super();
		}

		public Node(int diagonal, int horizon, int vertical) {
			super();
			this.diagonal = diagonal;
			this.horizon = horizon;
			this.vertical = vertical;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int map[][] = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		Node cases[][] = new Node[n+1][n+1]; //해당 좌표까지 도달할 수 있는 경우의 수 저장
		
		cases[1][2] = new Node(0,1,0); //가로 파이프 추가
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(map[i][j] == 1) continue;
				if(i==1 && (j==1 || j==2)) continue;
				//왼, 위, 대각선왼쪽위 확인
				//1. 인덱스에 있니?
				//2. 너 파이프 있니?
				//3. 왼 -> 대각선이랑 가로만 확인
				//   위 -> 대각선이랑 세로만 확인
				//대각선 -> 전부다 확인
				
//				System.out.println("current: ("+i+", "+j+")");
				
				Node node = new Node();
				//왼 확인
				if(j-1 >= 0 && cases[i][j-1] != null) {
//					System.out.println("왼쪽: "+ cases[i][j-1].diagonal+" "+cases[i][j-1].horizon+" "+cases[i][j-1].vertical);
					//가로 확인
					node.horizon += cases[i][j-1].horizon;
					//대각선 확인
					node.horizon += cases[i][j-1].diagonal;
				}
								
				//위 확인
				if(i-1 >= 0 && cases[i-1][j] != null) {
//					System.out.println("위쪽: "+ cases[i-1][j].diagonal+" "+cases[i-1][j].horizon+" "+cases[i-1][j].vertical);

					//세로 확인
					node.vertical += cases[i-1][j].vertical;
					//대각선 확인
					node.vertical += cases[i-1][j].diagonal;
				}
				
				
				
				//대각선 왼쪽 위 확인
				if(i-1 >= 0 && j-1 >=0 && cases[i-1][j-1] != null && map[i-1][j]==0 && map[i][j-1]==0) {
//					System.out.println("대각선왼쪽위: "+ cases[i-1][j-1].diagonal+" "+cases[i-1][j-1].horizon+" "+cases[i-1][j-1].vertical);
					//가로 확인
					node.diagonal += cases[i-1][j-1].horizon;
					//세로 확인
					node.diagonal += cases[i-1][j-1].vertical;
					//대각선 확인
					node.diagonal += cases[i-1][j-1].diagonal;
				}
//				System.out.println(node.diagonal+" "+node.horizon+" "+node.vertical);
				cases[i][j] = node;
			}
		}
		
		
		//결과 출력
		int result = 0;
		if(cases[n][n] != null) {
			result = (cases[n][n].diagonal+cases[n][n].horizon+cases[n][n].vertical);
		}
		
		System.out.println(result);
	}
}
