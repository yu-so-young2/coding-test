import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2931_가스관_G2 {
	static int R, C, src[], dest[], lost[];
	static char lostBlock;
	static boolean isCon[]; // 출발점과 도착지에 연결된 도로가 있는지 확인
	static Node[][] map;

	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	// 사방탐색
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	// BFS하기 위한 노드
	static class Node {
		int r, c;
		char road;
		boolean[] connected; // 상하좌우에 대하여 연결 여부 저장

		public Node() {
			super();
		}

		public Node(int r, int c, char road) {
			super();
			this.r = r;
			this.c = c;
			this.road = road;
			connected = new boolean[4];
			setConnection(road);
		}

		// 해당 모양에 대하여 연결 여부 setting
		public void setConnection(char c) {
			switch (c) {
			case '|':
				connected[UP] = true;
				connected[DOWN] = true;
				break;
			case '-':
				connected[LEFT] = true;
				connected[RIGHT] = true;
				break;
			case '+':
				connected[UP] = true;
				connected[DOWN] = true;
				connected[LEFT] = true;
				connected[RIGHT] = true;
				break;
			case '1':
				connected[RIGHT] = true;
				connected[DOWN] = true;
				break;
			case '2':
				connected[UP] = true;
				connected[RIGHT] = true;
				break;
			case '3':
				connected[LEFT] = true;
				connected[UP] = true;
				break;
			case '4':
				connected[LEFT] = true;
				connected[DOWN] = true;
				break;
			}
		}

		public boolean isConnected(Node n) {
			// 해당 노드와 연결되어 있는가
			// 나와 연결된 노드 중 있는지 확인
			for (int i = 0; i < 4; i++) {
				if (connected[i]) {
					// 확인할 노드
					int ny = this.r + dy[i];
					int nx = this.c + dx[i];
					if (map[ny][nx] == n)
						return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new Node[R][C];
		src = new int[2];
		dest = new int[2];
		lost = new int[2];

		// map 입력
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = new Node(i, j, str.charAt(j));

				// 시작, 도착 위치 저장
				if (str.charAt(j) == 'M') {
					src[0] = i;
					src[1] = j;
				} else if (str.charAt(j) == 'Z') {
					dest[0] = i;
					dest[1] = j;
				}
			}
		}

		isCon = new boolean[2];
		//시작점, 도착점과 연결된 것 찾기
		for (int i = 0; i < 4; i++) {
			int ny = src[0] + dy[i];
			int nx = src[1] + dx[i];
			if (!(ny < 0 || nx < 0 || ny == R || nx == C)) {
				if (map[ny][nx].isConnected(map[src[0]][src[1]])) {
					isCon[0] = true;
				}				
			}

			ny = dest[0] + dy[i];
			nx = dest[1] + dx[i];
			if (!(ny < 0 || nx < 0 || ny == R || nx == C)) {
				if (map[ny][nx].isConnected(map[dest[0]][dest[1]])) {
					isCon[1] = true;
				}				
			}
		}

		// bfs 사전준비
		boolean[][] visited = new boolean[R][C];
		Queue<Node> q = new LinkedList<>();

		// 아무 길에서 시작
		a: for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j].road != '.' && map[i][j].road != 'M' && map[i][j].road != 'Z') {
					visited[i][j] = true;
					q.add(map[i][j]);
					break a;
				}
			}
		}

		// 만약 길이 아예 없다면 (= 출발지와 도착지 사이에 한 칸만 비어져 있었다면)
		if (q.isEmpty()) {
			// 출발지와 도착지 사이 위치관계를 통해 계산
			if (src[0] == dest[0]) { // 같은 행에 있을 경우
				lost[0] = src[0];
				lostBlock = '-';
				// 열 비교
				if (src[1] > dest[1]) {
					lost[1] = src[1] - 1;
				} else {
					lost[1] = src[1] + 1;
				}
			} else if(src[1] ==dest[1]) { // 같은 열에 있을 경우
				lost[1] = src[1];
				lostBlock = '|';
				// 행 비교
				if (src[0] > dest[0]) {
					lost[0] = src[0] - 1;
				} else {
					lost[0] = src[0] + 1;
				}
			} else { //대각선에 있을 경우
				if(src[0]>dest[0]) {
					lost[0] = src[0]-1;
					if(src[1]>dest[1]) {
						lost[1] = src[1];	
						lostBlock = '2';
					} else {
						lost[1] = src[1]+1;					
						lostBlock = '1';
					}
				} else {
					lost[0] = src[0];					
					if(src[1]>dest[1]) {
						lost[1] = src[1]-1;					
						lostBlock = '1';
					} else {
						lost[1] = src[1]+1;					
						lostBlock = '2';
					}

				}
			}
		}

		else {
			// bfs 실시
			while (!q.isEmpty()) {
				// 빼기, 점이라면 그곳이 바로 없어진곳
				Node cur = q.poll();
				int y = cur.r, x = cur.c;

				// 없어진 곳 찾음
				if (map[y][x].road == '.') { // 없어진 곳 찾음
					// 지워진 블록의 행과 열
					lost[0] = y;
					lost[1] = x;
					break; // 탐색 끝
				} // 지워진 블록

				// 갈 수 있는 곳 모두 넣기(연결되어 있는 곳)
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					// ArrayIndexOutOfBounds 방지
					if (ny < 0 || nx < 0 || ny == R || nx == C)
						continue;

					// 연결되어있고 방문한 적 없다면 큐에 추가
					if (cur.connected[i] && !visited[ny][nx]) {
						visited[ny][nx] = true;
						q.add(map[ny][nx]);
					}
				}
			} // bfs

			// 지워진 블록 모양 찾기
			// 지워진 블록 기준 사방탐색하여 연결된 곳 찾은 후
			// 연결된 곳을 모두 이을 수 있는 길 모양 찾기
			boolean[] conn_lost = new boolean[4]; // 연결된 곳 저장
			int conn = 0;
			for (int i = 0; i < 4; i++) {
				int ny = lost[0] + dy[i];
				int nx = lost[1] + dx[i];
				// ArrayIndexOutOfBounds 방지
				if (ny < 0 || nx < 0 || ny == R || nx == C)
					continue;

				if (map[ny][nx].road != '.') { // 길이 있다면
					if (map[ny][nx].isConnected(map[lost[0]][lost[1]])) {
						conn_lost[i] = true;
						conn++;
					}
				}
			} // 사방탐색, 연결된 길 개수

			// 출발점 혹은 도착점과 연결되어야 하는 경우 보정
			if (conn == 1 || conn == 3) {
				if (!isCon[0]) { // 출발지와 연결되어야 하는 경우
					for (int i = 0; i < 4; i++) {
						int ny = lost[0] + dy[i];
						int nx = lost[1] + dx[i];
						if (ny < 0 || nx < 0 || ny == R || nx == C)
							continue;
						if (map[ny][nx].road == 'M') {
							conn_lost[i] = true;
						}
					}

				} else if (!isCon[1]) { // 도착지와 연결되어야 하는 경우
					for (int i = 0; i < 4; i++) {
						int ny = lost[0] + dy[i];
						int nx = lost[1] + dx[i];
						if (ny < 0 || nx < 0 || ny == R || nx == C)
							continue;
						if (map[ny][nx].road == 'Z') {
							conn_lost[i] = true;
						}
					}
				}
			}

			lostBlock = connRoad(conn_lost);

		}
		
//		System.out.println("isCon: "+isCon[0]+" "+isCon[1]);
		
		// index 보정(1부터 시작)
		lost[0]++;
		lost[1]++;

		// 결과 출력
		System.out.println(lost[0] + " " + lost[1] + " " + lostBlock);

	}

	// 주어진 연결된 곳을 모두 이을 수 있는 길의 모양을 리턴
	private static char connRoad(boolean[] conn_lost) {
//		System.out.println();
//		for (int i = 0; i < 4; i++) {
//			System.out.print(conn_lost[i] + " ");
//		}

		char c = '0';

		int check = 0;
		for (int i = 0; i < 4; i++) {
			if (conn_lost[i])
				check += i;
		}
		switch (check) {
		case 1:
			c = '|';
			break;
		case 2:
			c = '3';
			break;
		case 4:
			c = '1';
			break;
		case 5:
			c = '-';
			break;
		case 6:
			c = '+';
			break;
		case 3:
			if (conn_lost[0])
				c = '2';
			else
				c = '4';
			break;
		}
		return c;
	}

}
