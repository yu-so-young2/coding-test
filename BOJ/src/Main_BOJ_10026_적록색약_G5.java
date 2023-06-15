import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//class Node {
//	int r, c;
//	char color;
//	
//	public Node(int r, int c, char color) {
//		this.r = r;
//		this.c = c;
//		this.color = color;
//	}
//}

public class Main_BOJ_10026_적록색약_G5 {
	public static int N;
	public static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		System.out.print(bfs1());
		System.out.print(" ");
		System.out.print(bfs2());
	}//main
	
	public static int bfs1() {
		int division = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(0,0, map[0][0]));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			//현재 구역 탐색
			division++;
			while(!q.isEmpty()) {
				//갈 수 있을 때까지 가면서 visited[][] = true; 변경
				Node current = q.poll();
				int r = current.r;
				int c = current.c;
				char color = current.color;
//				System.out.println("poll: ("+r+", "+c+"), "+color+"!!");
				//상하좌우, 범위 내이고, 안갔고, 색이 같으면 진행
				if(r-1 >= 0 && map[r-1][c] == color && !visited[r-1][c]) {
					q.add(new Node(r-1, c, color));
					visited[r-1][c] = true;
				}
				if(r+1 < N && map[r+1][c] == color && !visited[r+1][c]) {
					q.add(new Node(r+1, c, color));
					visited[r+1][c] = true;
				}
				if(c-1 >= 0 && map[r][c-1] == color && !visited[r][c-1]) {
					q.add(new Node(r, c-1, color));
					visited[r][c-1] = true;
				}
				if(c+1 < N && map[r][c+1] == color && !visited[r][c+1]) {
					q.add(new Node(r, c+1, color));
					visited[r][c+1] = true;
				}
			}//현재구역 탐색 while
			
			//아직 탐색 안한 노드 추가
			search:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						q.add(new Node(i, j, map[i][j]));
						visited[i][j] = true;
						break search;
					}
				}
			}
		}
		
		return division;
	}
	
	//적록색
	public static int bfs2() {
		int division = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(0,0, map[0][0]));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			//현재 구역 탐색
			division++;
			while(!q.isEmpty()) {
				//갈 수 있을 때까지 가면서 visited[][] = true; 변경
				Node current = q.poll();
				int r = current.r;
				int c = current.c;
				char color = current.color;
//				System.out.println("poll: ("+r+", "+c+"), "+color+"!!");
				//상하좌우, 범위 내이고, 안갔고, 색이 같으면 진행
				if(r-1 >= 0 && ((color=='B'&&map[r-1][c]=='B')||(color!='B'&&map[r-1][c]!='B')) && !visited[r-1][c]) {
					q.add(new Node(r-1, c, color));
					visited[r-1][c] = true;
				}
				if(r+1 < N &&  ((color=='B'&&map[r+1][c]=='B')||(color!='B'&&map[r+1][c]!='B')) && !visited[r+1][c]) {
					q.add(new Node(r+1, c, color));
					visited[r+1][c] = true;
				}
				if(c-1 >= 0 &&  ((color=='B'&&map[r][c-1]=='B')||(color!='B'&&map[r][c-1]!='B')) && !visited[r][c-1]) {
					q.add(new Node(r, c-1, color));
					visited[r][c-1] = true;
				}
				if(c+1 < N &&  ((color=='B'&&map[r][c+1]=='B')||(color!='B'&&map[r][c+1]!='B')) && !visited[r][c+1]) {
					q.add(new Node(r, c+1, color));
					visited[r][c+1] = true;
				}
			}//현재구역 탐색 while
			
			//아직 탐색 안한 노드 추가
			search:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						q.add(new Node(i, j, map[i][j]));
						visited[i][j] = true;
						break search;
					}
				}
			}
		}
		
		return division;
	}
	
}//class