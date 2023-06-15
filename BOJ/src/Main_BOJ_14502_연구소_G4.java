import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소_G4 {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int[][] map;
	static int maxSafe;
	static ArrayList<Node> nodes;
	static boolean check[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int H, W;
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		nodes = new ArrayList<>(); //벽 세울 수 있는 빈칸 저장
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					nodes.add(new Node(i, j));
				}
			}
		}
		
		maxSafe = 0;
		check = new boolean[nodes.size()]; //조합 생성
		
		//Combination
		comb(0, 0);
		
		//print
		System.out.println(maxSafe);
		
	}
	
	public static void comb(int cnt, int start) {
		//조합 생성 완료 -> 벽 세우고 바이러스 퍼트리고 안전지대 세고 최댓값 갱신
		if(cnt == 3) {
			int[][] temp = copyMap(map);
			//벽 세우기
			for (int i = 0; i < check.length; i++) {
				if(check[i]) {
//					System.out.print(i+" ");
					int r = nodes.get(i).r;
					int c = nodes.get(i).c;
					temp[r][c] = 1;
				}
//				System.out.println();
			}
			
			//바이러스
			virus(temp);
			
			//안전지대 세기
			int curSize = getSafe(temp);
			
			//최댓값 갱신
			maxSafe = Math.max(curSize, maxSafe);
			
			return;
		}
		
		if(start==nodes.size()) {
			return;
		}
		
		//조합 생성
		for (int i = start; i < check.length; i++) {
			check[i] = true;
			comb(cnt+1, i+1);
			check[i] = false;
		}
		
//		check[start] = true;
//		comb(cnt+1, start+1);
//		check[start] = false;
//		comb(cnt, start+1);
		
	}
	
	//주어진 맵 복사해서 리턴해주는 함수
	public static int[][] copyMap(int[][] origin) {
		int[][] dest = new int[origin.length][origin[0].length];
		for (int i = 0; i < dest.length; i++) {
			for (int j = 0; j < dest[0].length; j++) {
				dest[i][j] = origin[i][j];
			}
		}
		return dest;
	}

	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	//주어진 맵 바이러스 퍼트리는 함수
	public static void virus(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]==2) {
					dfs(i, j, map);
				}
			}
		}
	}

	public static void dfs(int r, int c, int[][] map) {
		for (int i = 0; i < 4; i++) {
			int ny = r+dy[i];
			int nx = c+dx[i];
			if(ny>=0 && nx>=0 && ny < map.length && nx < map[0].length) {
				if(map[ny][nx] == 0) {
					map[ny][nx] = 2;
					dfs(ny, nx, map);
				}
			}
		} //상하좌우
	}
	
	//주어진 맵에서 빈칸의 수 세는 함수
	public static int getSafe(int[][] map) {
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[0].length; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		int region = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 0) {
					region++;
				}
			}
		}
		
		return region;
	}
}
