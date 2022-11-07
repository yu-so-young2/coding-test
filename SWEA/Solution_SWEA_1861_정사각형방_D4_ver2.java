import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 1861. 정사각형 방
 * - DFS
 * - ver1 보다 시간 비효율적..ㅠㅠ
 * 
 */

public class Solution_SWEA_1861_정사각형방_D4_ver2 {
	public static int T, N, map[][], startRoom, maxLen;
	public static boolean visited[][];
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static int dfs(int r, int c) {
		//이미 탐색했다면 더 탐색할 필요 없이 종
		if(visited[r][c]) return 1;
		
		//사방탐색 후 갈 곳 있다면 return dfs(다음칸)+1
		for (int i = 0; i < 4; i++) {
			int ny = r + dy[i];
			int nx = c + dx[i];
			
			if(ny>=0 && nx>=0 && ny<N && nx<N && (map[ny][nx]==map[r][c]+1)) {
				int len = dfs(ny, nx)+1;
				if(len > maxLen) {
					startRoom = map[r][c];
					maxLen = len;
				}
				else if(len == maxLen) {
					startRoom = Math.min(startRoom, map[r][c]);
				}
				return len;
			}
		}
		
		
		//갈 곳이 없다면
		return 1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			startRoom = -1;
			maxLen = -1;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j);					
				}
			}
			
			sb.append("#"+tc+" "+startRoom+" "+maxLen+"\n");
		}
		System.out.println(sb);
	}
}
