package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1987. 알파벳
 * dfs 이용하여 최대 깊이 찾기
 * dfs 재귀 호출
 */

public class Main_BOJ_1987_알파벳_G4 {
	static int R, C;
	static char map[][];
	static boolean visited[];

	//상우하좌 방향좌표
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static int maxPath = Integer.MIN_VALUE;
	static int path;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		visited = new boolean[26]; //해당 알파벳을 이동했는지 확인
		dfs(0,0,0);
		
		System.out.println(maxPath);
	
	}//main

	
	public static void dfs(int x, int y, int path) {
		int curAlpha = map[y][x]-'A';
		
		if(visited[curAlpha]==true) { //현재 칸 알파벳 방문했었다면 탐색 종료
			maxPath = Math.max(maxPath, path);
			return;
		}
		visited[curAlpha] = true; //현재 캐릭터 방문했다고 하고 가기
		for (int i = 0; i < 4; i++) { //4가지 방향 갈 수 있는지 확인
			int newX = x+dx[i];
			int newY = y+dy[i];
			if(newX>=0 && newX<C && newY>=0 && newY<R) {
				dfs(newX, newY, path+1);
			}
		}
		visited[curAlpha] = false; //현재 캐릭터 방문 취소(다른 재귀 가지에 영향 안주기 위하여)
	}
	
}
