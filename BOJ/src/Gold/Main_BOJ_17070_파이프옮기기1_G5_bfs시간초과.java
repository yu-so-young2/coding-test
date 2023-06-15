package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS 시간초과
 * 
 */


public class Main_BOJ_17070_파이프옮기기1_G5_bfs시간초과 {
	final static int diagonal = 0;
	final static int horizon = 1;
	final static int vertical = 2;

	public static class Pipe {
		int r, c, dir; //대각선0 가로1 세로2

		Pipe(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
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

		
		int cases[][] = new int[n+1][n+1]; //해당 좌표까지 도달할 수 있는 경우의 수 저장
		Queue<Pipe> q = new LinkedList<>();
		q.add(new Pipe(1,2,horizon));

		while(!q.isEmpty()) {
			//현재 파이프 뽑고 현재 위치에 +1, 해당 파이프로 갈 수 있는 파이프 추가
			Pipe p = q.poll();
			
			int ny = p.r;
			int nx = p.c;
			
			System.out.println("current pipe: ("+ny+", "+nx+"), dir: "+p.dir);
			
			cases[ny][nx]++; //현재 파이프의 도착 위치 도달 경우의수 +1
			
			if(p.dir == diagonal) { //현재 대각선 파이프
				//가로, 세로, 대각선아래 모두 확인
				//가능하다면(인덱스 내에 있고 벽이 아니라면)
				
				//가로 확인
				if(nx+1 <= n && map[ny][nx+1] == 0) {
					q.add(new Pipe(ny, nx+1, horizon));
				}
								
				//세로 확인				
				if(ny+1 <= n && map[ny+1][nx] == 0) {
					q.add(new Pipe(ny+1, nx, vertical));
				}
				
				
				//대각선아래 확인
				if(ny+1 <= n && nx+1 <=n && (map[ny+1][nx] == 0 && map[ny][nx+1] == 0 && map[ny+1][nx+1] == 0)) {
					q.add(new Pipe(ny+1, nx+1, diagonal));
				}
				
				
				
				
			} else if (p.dir == horizon) { //현재 가로 파이프
				//가로, 대각선아래 확인
				//가로 확인
				if(nx+1 <= n && map[ny][nx+1] == 0) {
					q.add(new Pipe(ny, nx+1, horizon));
				}
				//대각선아래 확인
				if(ny+1 <= n && nx+1 <=n && (map[ny+1][nx] == 0 && map[ny][nx+1] == 0 && map[ny+1][nx+1] == 0)) {
					q.add(new Pipe(ny+1, nx+1, diagonal));
				}
				
			} else { //현재 세로 파이프
				//세로 확인				
				if(ny+1 <= n && map[ny+1][nx] == 0) {
					q.add(new Pipe(ny+1, nx, vertical));
				}
				
				
				//대각선아래 확인
				if(ny+1 <= n && nx+1 <=n && (map[ny+1][nx] == 0 && map[ny][nx+1] == 0 && map[ny+1][nx+1] == 0)) {
					q.add(new Pipe(ny+1, nx+1, diagonal));
				}
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					System.out.print(cases[i][j]+" ");
				}
				System.out.println();
			}
			
			System.out.println("==========");
			
		}
		
		
		
		//결과 출력
		System.out.println(cases[n][n]);
	}
}
