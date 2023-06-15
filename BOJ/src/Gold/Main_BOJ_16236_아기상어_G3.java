package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 16236. 아기상어
 * - bfs 알고리즘(레벨 기억)
 * 
 */

class Fish {
	public int r, c, size;
	Fish(int r, int c, int size) {
		this.r = r;
		this.c = c;
		this.size = size;
	}
}

class Node {
	int r, c;
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_BOJ_16236_아기상어_G3 {
	public static int N;
	public static int map[][];
	public static Fish shark;
	public static ArrayList<Fish> fishes, feeds;
	
	//a에서 b까지 가는데 걸리는 최단시간 bfs
	public static int distance(Fish a, Fish b) {
		boolean[][] visited = new boolean[N][N];
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a.r, a.c));
		visited[a.r][a.c] = true;
		int distance = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) { //같은 레벨의 자식만큼 반복 
				Node current = q.poll();
				
				//도착점 찾으면 레벨 리턴 
				if(current.r == b.r && current.c == b.c) return distance;
								
				//상하좌우 유효하고, 안갔고, 크기가 작으면 
				if(current.r-1 >=0 && !visited[current.r-1][current.c] && map[current.r-1][current.c] <= a.size) {
					q.add(new Node(current.r-1, current.c));
					visited[current.r-1][current.c] = true;
				}
				if(current.r+1 < N && !visited[current.r+1][current.c] && map[current.r+1][current.c] <= a.size) {
					q.add(new Node(current.r+1, current.c));
					visited[current.r+1][current.c] = true;
				}
				if(current.c-1 >=0 && !visited[current.r][current.c-1] && map[current.r][current.c-1] <= a.size) {
					q.add(new Node(current.r, current.c-1));
					visited[current.r][current.c-1] = true;
				}
				if(current.c+1 < N && !visited[current.r][current.c+1] && map[current.r][current.c+1] <= a.size) {
					q.add(new Node(current.r, current.c+1));
					visited[current.r][current.c+1] = true;
				}
			}
			//레벨 이동
			distance++;
		}
		//못찾았으면 -1 리턴 
		return -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //map size
		fishes = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if(n==9) {
					shark = new Fish(i, j, 2); //아기상어 생성 
				}
				else if(n!=0) {
					fishes.add(new Fish(i, j, n)); //물고기 생성 
				}
			}
		}//상어, 물고기 객체 생성
		
		//물고기 정렬 (사이즈별로)
		Collections.sort(fishes, new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				return o1.size-o2.size;
			}
		});
		
		//상어 위치 0으로 초기화
		map[shark.r][shark.c] = 0;
		
		feeds = new ArrayList<>(); //먹을 수 있는 물고기 저장
		int sec = 0;
		int curSize = shark.size;
		int eating = 0;
		
		//끝까지 진행했을 때 먹을 수 있는 물고기 모두 넣기
		for (Fish f : fishes) {
			if(f.size < curSize) {
				feeds.add(f);
				eating++;
				if(eating==curSize) {
					eating = 0;
					curSize++;
				}
			}
		}

		
		//(현재)먹을 수 있는 물고기 중 가장 가까운 물고기에게 접근(가까운 물고기 찾기, 해당 거리만큼 시간 이동)
		eating = 0;
		while(!feeds.isEmpty()) {
			curSize = shark.size;
			Fish closest = new Fish(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
			int minDistance = Integer.MAX_VALUE;
			boolean find = false;
			
			for (Fish f : feeds) {
				//먹을 수 있는 물고기 중 가장 가까운 물고기 위치 찾
				if(f.size < curSize) {
					int d = distance(shark, f);
					if(d < 0) continue;
					if(d < minDistance) {
						find = true;
						closest = f;
						minDistance = d;
					} else if(d == minDistance) {
						if(f.r < closest.r) {
							find = true;
							closest = f;
						} else if(f.r == closest.r && f.c < closest.c) {
							find = true;
							closest = f;
						}
					}
				}
			}//가장 가까운 물고기 찾기 
			
			//먹기(사이즈 증가, 물고기 삭제, 상어 위치 변경, 시간 추가, 맵 변)
			if(!find) break; //못찾았다면 엄마 찾으러 나오기~ 
			sec += minDistance; //시간 추가
			shark.r = closest.r; //상어 위치 변경 
			shark.c = closest.c; //상어 위치 변경
			feeds.remove(closest); //먹이 후보에서 삭제 
			map[shark.r][shark.c] = 0; //현재 위치의 물고기 먹었으니 0으로 변경
			
			//상어 사이즈 증가
 			eating++;
			if(eating == shark.size) {
				shark.size++;
				eating = 0;
			}
		}//while
		
		if(sec < 0) System.out.println(0);
		else System.out.println(sec);
	}
}