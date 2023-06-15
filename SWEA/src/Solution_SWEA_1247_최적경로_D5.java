import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1247_최적경로_D5 {
	static int T, N, minPath;
	static int[] home, office, temp;
	static int[][] clients;
	static boolean visited[];
	
	public static int distance(int[] a, int[] b) {
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
		
	}
	
	public static void perm(int idx) {
		if(idx == N) {
			int path = 0;
			//temp[i]: 고객 인덱스
			//회사에서 첫번째 고객까지 거리
			path += distance(office, clients[temp[0]]);
			//고객들 사이 거리
			for (int i = 0; i < clients.length-1; i++) {
				path += distance(clients[temp[i]], clients[temp[i+1]]);
			}
			//마지막 고객에서 집까지 거리
			path += distance(clients[temp[N-1]], home);
			minPath = Math.min(minPath, path);
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			temp[idx] = i;
			perm(idx+1);
			visited[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			home = new int[2];
			office = new int[2];
			clients = new int[N][2];
			
			st = new StringTokenizer(br.readLine());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				clients[i][0] = Integer.parseInt(st.nextToken());
				clients[i][1] = Integer.parseInt(st.nextToken());
			}
			
			minPath = Integer.MAX_VALUE;
			visited = new boolean[N];
			temp = new int[N];
			perm(0);
			
			sb.append("#"+tc+" "+minPath+"\n");
		}//for each testcase

		
		System.out.println(sb);
	}//main
}