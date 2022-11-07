import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 달이 차오른다, 가자
 * 
 */


public class Main_BOJ_1194_달이차오른다가자_G1 {
	static int H, W;
	static boolean visited[][][];
	
	static char[][] map;
	static int minStep;
	
	
	static class Travel {
		int r, c, key, step;

		public Travel(int r, int c, int key, int step) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.step = step;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		visited = new boolean[H][W][1<<6];
		map = new char[H][W];
		Queue<Travel> q = new LinkedList<>();
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				
				//출발점
				if(map[i][j] == '0') {
					visited[i][j][0<<6] = true;
					q.add(new Travel(i, j, 0, 0));
				}
			}
		}
		
		
		
		//bfs
		minStep = Integer.MAX_VALUE;
		int level = 0;
		while(!q.isEmpty()) {
			level++;
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Travel cur = q.poll();
//				System.out.println("Hi! I am "+cur.r+", "+cur.c+" and key: "+cur.key+", steps: "+cur.step);
				
				int r = cur.r;
				int c = cur.c;
				
				if(map[r][c] == '1') {
					System.out.println(cur.step);
					return;
				}
				
				
				//상하좌우
				for (int k = 0; k < 4; k++) {
					int ny = r+dy[k];
					int nx = c+dx[k];
					
					//갈 수 없으면 / 갈 필요 없으면 continue
					//1)인덱스 밖
					if(!(ny >= 0 && nx >= 0 && ny < H && nx < W)) continue;
					//2)벽이거나
					if(map[ny][nx] == '#') continue;
					//3)문인데 열쇠가 없는 경우
					if(map[ny][nx] >= 'A' && map[ny][nx] <= 'F') {
						if((cur.key & (1 << (map[ny][nx]-'A') ) ) == 0) { //열쇠가 없으면
							continue;
						}
					}
					
					//갈 수 있으면 가
					//1)열쇠일 경우
					if(map[ny][nx] >= 'a' && map[ny][nx] <= 'f') {
						//열쇠 줍고 가기
						int newKey = cur.key | (1 <<(map[ny][nx]-'a'));
						//지금 열쇠 상태로 이미 방문안했었다면
						if(!visited[ny][nx][newKey]) {
							//방문처리하고 bfs
							Travel temp = new Travel(ny, nx, newKey, level);
							visited[ny][nx][newKey] = true;
							q.add(temp);
						}
					}
					
					//2) 문일 경우(열쇠 있음), 빈칸일 경우, 0일 경우, 출구일 경우
					else {
						//지금 열쇠 상태로 이미 방문안했었다면
						if(!visited[ny][nx][cur.key]) {
							//방문처리하고 bfs
							Travel temp = new Travel(ny, nx, cur.key, level);
							visited[ny][nx][cur.key] = true;
							q.add(temp);
						}
					}

					
				}
				
			}
		}
		
		minStep = minStep==Integer.MAX_VALUE? -1:minStep;
		System.out.println(minStep);
	}
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
}
