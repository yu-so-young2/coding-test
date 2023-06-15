import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17471_게리맨더링_G4 {
	static int N;
	static List<ArrayList<Integer>> cities;
	static int populations[];
	static int minDiff;
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		populations = new int[N];
		cities = new ArrayList<>();
		
		//인구수 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}
		
		//연결 노드 저장
		for (int i = 0; i < N; i++) {
			ArrayList<Integer> arr = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int conn = Integer.parseInt(st.nextToken()); //연결된 도시 수
			for (int j = 0; j < conn; j++) {
				arr.add(Integer.parseInt(st.nextToken())-1);
			}
			cities.add(arr);
		}
		
		minDiff = Integer.MAX_VALUE;
		visited = new boolean[(int)Math.pow(2, N)];

		//선거구 나누기
		//N개의 노드로 부분집합 만들기 (이미 만들었던 부분집합인지 확인하기 위하여 비트마스킹 활용)
		//각 그룹이 연결되어 있는지 확인
		//하나라도 연결되어 있지 않다면 차이 보지 않고 넘어가
		//둘 다 연결되어 있다면 각 그룹의 인구수 차이 구해서 갱신
		
		subset(0,0);

		//두 선거구로 나눌 수 없는 경우 체크
		minDiff = minDiff == Integer.MAX_VALUE? -1 : minDiff;
		System.out.println(minDiff);
		
	}//main

	
	public static void subset(int cur, int group) {
		if(cur == N) {
			if(isChecked(group)) return;
			
			int group1 = group;
			int group2 = group^((int)Math.pow(2, N)-1);
		
			visited[group1] = true;
			visited[group2] = true;
			
			//둘 중 한 그룹이라도 연결되어 있지 않다면
			if(!isConnected(group1) || !isConnected(group2)) return;
			
			//비어있는 그룹이 있다면
			if(group1==0 || group2==0) return;
			
			//둘 다 연결되어 있다면 인구수 확인
			int pop1 = 0, pop2 = 0;
			for (int i = 0; i < N; i++) {
				if((group1 & (1<<i)) != 0) {
					pop1 += populations[i];
				}
				else {
					pop2 += populations[i];
				}
			}
			
			int curDiff = Math.abs(pop1-pop2);
			minDiff = Math.min(minDiff, curDiff);
			
			return;
		}
		
		//체크하고 보내기
		subset(cur+1, group | (1<<cur));
		
		//체크 안하고 보내기
		subset(cur+1, group);
	}
	
	
	//해당 그룹이 연결되어 있는지 확인해주는 함수
	public static boolean isConnected(int group) {
		int visit = 0;
		
		//bfs
		//한 정점 기준으로 bfs 하면서 만나는 모든 노드의 자리에 1 비트마스크
		int start = 0;
		for (int i = 0; i < N; i++) { //가장 최초로 1이 있는 노드 번호 찾기
			if((group & (1<<i)) != 0) {
				start = i;
				break;
			}
		}
		
		Queue<Integer> q = new LinkedList<>();

		visit = visit | (1<<start); //해당 노드 방문 처리
		q.add(start);

		
		while(!q.isEmpty()) {
			int cur = q.poll();
			ArrayList<Integer> connect = cities.get(cur);
			for (Integer city : connect) {
				if((group & (1<<city))!=0) { //현재 정점의 이웃이 그룹 안에 있다면
					if((visit&(1<<city)) != 0) { //방문한 적 있음
						continue;
					}
					else { //방문한 적 없음
						visit |= (1<<city);
						q.add(city);
					}
				}
				
			}
		}
		
		//연결 확인
		if((visit&group) == group) {
			return true;
		}
		return false;
	}
	
	//해당 group(비트마스킹)을 만든 적이 있는지 확인해주는 함수
	public static boolean isChecked(int group) {
		if(visited[group]) { //해당 그룹 만든 적 있다면
			return true;
		}
		
		//만든 적 없다면 false
		return false;
	}
	
}
