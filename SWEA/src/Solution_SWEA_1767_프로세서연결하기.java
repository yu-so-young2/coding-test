import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기 {
	static class Node {
		int r, c;
		boolean dir[]; //상하좌우
		public Node(int r, int c, boolean[] dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	
	static int T, N, map[][], k;
	static ArrayList<Node> cores;
	static int maxCore, minLine;
	
	static void dfs(int myCore, int myLine) {
		
		if(myCore==cores.size()) { //모든 코어 노드에 대해 탐색이 끝났으면
			//현재 탐색의 코어 개수가 맥스코어보다 많을 때
				//맥스코어, minLine 갱신
			if(myCore > maxCore) {
				maxCore = myCore;
				minLine = myLine;
			}
			//현재 탐색의 코어 개수가 맥스코어와 같을 때
				//minLine 갱신
			else if(myCore == maxCore) {
				minLine = Math.min(minLine, myLine);
			}
			return;
		}
		
		if(myCore+(cores.size()-myCore) < maxCore) { //현재 연결된 노드의 개수 + 현재 남은 노드 < 현재 최대 코어
			return; //더이상 깊게 들어갈 필요 없음
		}
		
		Node me = cores.get(myCore); //남은 노드 중 하나 뽑기
		//해당 노드에서 가능한 모든 방향으로의 dfs 수행
		if(me.dir[0]) { //상 
			//현재 맵 기준으로도 상 가능한지 확인
			k = me.r;
			while(k >= 0) {
				k--;
				//갈수있는지 확인(코어있는지) 있으면 바로 나오기
				if(k==-1 || map[k][me.c] != 0) break;
			}
			if(k == -1) { //끝까지 왔으니 상 방향 가능
				//가능하다고 가정 후 맵 변경하고 dfs 넘기기
				k = me.r-1;
				while(k >= 0) {
					map[k][me.c] = 2;
					k--;
				}
				
				dfs(myCore+1, myLine+me.r);
				
				//다음방향 dfs 를 위해 맵 초기화
				k = me.r-1;
				while(k >= 0) {
					map[k][me.c] = 0;
					k--;
				}
			}	
		}//상 방향으로 dfs 호출
		
		if(me.dir[1]) { //하 
			//현재 맵 기준으로도 하 가능한지 확인
			k = me.r;
			while(k < N) {
				k++;
				//갈수있는지 확인(코어있는지) 있으면 바로 나오기
				if(k==N || map[k][me.c] != 0) break;
			}
			if(k == N) { //끝까지 왔으니 하 방향 가능
				//가능하다고 가정 후 맵 변경하고 dfs 넘기기
				k = me.r+1;
				while(k < N) {
					map[k][me.c] = 2;
					k++;
				}
				
				dfs(myCore+1, myLine+(N-1-me.r));
				
				//다음방향 dfs 를 위해 맵 초기화
				k = me.r+1;
				while(k < N) {
					map[k][me.c] = 0;
					k++;
				}
			}	
		}//하 방향으로 dfs 호출
		if(me.dir[2]) { //좌 
			//현재 맵 기준으로도 상 가능한지 확인
			k = me.c;
			while(k >= 0) {
				k--;
				//갈수있는지 확인(코어있는지) 있으면 바로 나오기
				if(k==-1 || map[me.r][k] != 0) break;
			}
			if(k == -1) { //끝까지 왔으니 상 방향 가능
				//가능하다고 가정 후 맵 변경하고 dfs 넘기기
				k = me.c-1;
				while(k >= 0) {
					map[me.r][k] = 2;
					k--;
				}
				
				dfs(myCore+1, myLine+me.c);
				
				//다음방향 dfs 를 위해 맵 초기화
				k = me.c-1;
				while(k >= 0) {
					map[me.r][k] = 0;
					k--;
				}
			}	
		}//상 방향으로 dfs 호출 
		if(me.dir[3]) { //우 
			//현재 맵 기준으로도 상 가능한지 확인
			k = me.c;
			while(k < N) {
				k++;
				//갈수있는지 확인(코어있는지) 있으면 바로 나오기
				if(k==N || map[me.r][k] != 0) break;
			}
			if(k == N) { //끝까지 왔으니 상 방향 가능
				//가능하다고 가정 후 맵 변경하고 dfs 넘기기
				k = me.c+1;
				while(k < N) {
					map[me.r][k] = 2;
					k++;
				}
				
				dfs(myCore+1, myLine+(N-1-me.c));
				
				//다음방향 dfs 를 위해 맵 초기화
				k = me.c-1;
				while(k < N) {
					map[me.r][k] = 0;
					k++;
				}
			}	
		}//상 방향으로 dfs 호출 
		
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			//변수 초기화
			cores = new ArrayList<Node>();
			map = new int[N][N];
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			
			//맵 정보 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					}
			} //맵 정보 입력 완료 
			
			//코어 정보 리스트에 받기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						//맨 끝 노드라면 제외
						if(i==0 || j==0 || i == N-1 || j == N-1)
							continue;
						
						//가능한 방향 모두 탐색 후 저장
						boolean[] dir = new boolean[4];
						
						//상
						k = i;
						while(k >= 0) {
							k--;
							//갈수있는지 확인(코어있는지) 있으면 바로 나오기
							if(k==-1 || map[k][j] == 1) break;
						}
						if(k == -1) { //끝까지 왔으니 상 방향 가능
							dir[0] = true;
						}
						//하
						k = i;
						while(k < N) {
							k++;
							//갈수있는지 확인(코어있는지) 있으면 바로 나오기
							if(k==N || map[k][j] == 1) break;
						}
						if(k == N) { //끝까지 왔으니 상 방향 가능
							dir[1] = true;
						}
						//좌
						k = j;
						while(k >= 0) {
							k--;
							//갈수있는지 확인(코어있는지) 있으면 바로 나오기
							if(k==-1 || map[i][k] == 1) break;
						}
						if(k == -1) { //끝까지 왔으니 상 방향 가능
							dir[2] = true;
						}
						//우
						k = j;
						while(k < N) {
							k++;
							//갈수있는지 확인(코어있는지) 있으면 바로 나오기
							if(k==N || map[i][k] == 1) break;
						}
						if(k == N) { //끝까지 왔으니 상 방향 가능
							dir[3] = true;
						}
						
						if(!dir[0]&&!dir[1]&&!dir[2]&&!dir[3])
							continue;
						
						//가능한 방향 기반으로 추가
						cores.add(new Node(i, j, dir));							
					}
				}
			} //코어 리스트 추가 완료
			
			
			//알고리즘 구현
			dfs(0, 0);
			
			if(minLine == Integer.MAX_VALUE) {
				minLine = 0;
			}
			sb.append("#"+tc+" "+minLine+"\n");
		}//for each testcase

		
		System.out.println(sb);
	}//main
}
