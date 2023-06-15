import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨거리_G5 {
	public static int N, M, numChicken, minDistance;
	public static int[][] map, chickens;
	public static int[] temp; //현재 뽑힌 도시 저장할 배열
	
	
	public static void search(int index, int cnt) {
		//M개의 조합 뽑았으면 해당 지도 기반으로 도시치킨거리 구하기
		//temp[]: 남아있는 치킨집의 인덱스가 담겨 있음
		if(cnt == M) {
			int sumDistance = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==1) { //각 집마다
						int curDistance = Integer.MAX_VALUE;
						for (int k = 0; k < temp.length; k++) { //가장 가까운 치킨집을 찾으며 치킨거리 갱신
							curDistance = Math.min(curDistance,
									Math.abs(chickens[temp[k]][0]-i)+Math.abs(chickens[temp[k]][1]-j));
						}
						//각 집의 치킨거리 누적
						sumDistance += curDistance;
					}
				}
			}
			//각 조합의 최소 치킨거리 갱신
			minDistance = Math.min(minDistance, sumDistance);
			
			return;
		}

		//numChicken개의 치킨집 중 numChicken개의 조합 뽑기
		for (int i = index; i < numChicken; i++) {
			temp[cnt] = i;
			search(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		

		//initialize map & find chicken store num
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) numChicken++;
			}
		}
		
		//save chicken store location
		int cnt = 0;
		chickens = new int[numChicken][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) {
					chickens[cnt][0] = i;
					chickens[cnt][1] = j;
					cnt++;
				}
			}
		}
		
		
		temp = new int[M];
		
		minDistance = Integer.MAX_VALUE;
		search(0,0);
		System.out.println(minDistance);
	}

}


