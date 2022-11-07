import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 17144. 미세먼지 안녕
 * 구현, 시뮬레이션
 * 
 * 즉각적인 확산을 하면 안되고 현재 있는 미세먼지마다 확산되는 값을 저장해둔 후
 * 모든 미세먼지의 확산값을 탐색 후에 갱신
 * 
 */


public class Main_BOJ_17144_미세먼지안녕_G4 {
	public static int R, C, T, map[][], update[][];
	public static int air1 = -1, air2 = -1; //공기청정기 행
	public static int dy[] = {-1,1,0,0};
	public static int dx[] = {0,0,-1,1};
	
	
	public static void rotateAntiCw(int air) {
		//아래로 밀기(air 가 있는 행 개수만큼)
		for (int i = air-1; i >= 0; i--) {
			if(i+1==air) continue; //공기청정기 흡수(옮기지 않고 덮어쓰기)
			map[i+1][0] = map[i][0];
		}
		//왼쪽으로 밀기
		for (int j = 1; j < C; j++) {
			map[0][j-1] = map[0][j];
		}
		//위로 밀기
		for (int i = 1; i <= air ; i++) {
			map[i-1][C-1] = map[i][C-1];
		}
		//오른쪽으로 밀기
		for (int j = C-2 ; j >= 0 ; j--) {
			if(j==0) map[air][j+1] = 0; //공기청정기에서 나가는 바람 0
			else {
				map[air][j+1] = map[air][j];
			}
		}
	}
	public static void rotateCw(int air) {
		//위로 밀기
		for (int i = air+1; i <= R-1 ; i++) {
			if(i==air+1) continue; //공기청정기 흡수
			map[i-1][0] = map[i][0];
		}
		//왼쪽으로 밀기
		for (int j = 1; j < C; j++) {
			map[R-1][j-1] = map[R-1][j];
		}
		
		//아래로 밀기
		for (int i = R-2; i >= air; i--) {
			map[i+1][C-1] = map[i][C-1];
		}
		//오른쪽으로 밀기
		for (int j = C-2 ; j >= 0 ; j--) {
			if(j==0) map[air][j+1] = 0; //공기청정기에서 나가는 바람 0
			else {
				map[air][j+1] = map[air][j];
			}
		}
		
	}
	
		
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		update = new int[R][C];
		
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//공기청정기 위치(행) 저장
				if(map[i][j]==-1 && air1<0) air1 = i;
				else if(map[i][j]==-1 && air2<0) air2 = i;
			}
		}
		
		//알고리즘 시행
		for (int t = 0; t < T; t++) { //T초동안
			//map의 모든 미세먼지에 대해 확산
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] <= 0) continue; //미세먼지 없으면 넘어가
					//미세먼지가 있다면
					int total = map[i][j];
					int sub = total/5;
					int possible = 0;
					//갈 수 있는 방향 개수 정하기
					for (int k = 0; k < 4; k++) {
						int ny = i+dy[k];
						int nx = j+dx[k];
						//각 방향 갈 수 있으면(ArrayIndexOutOfBounds)
						if(ny >= 0 && ny < R && nx >= 0 && nx < C) {
							//공기청정기면 확산 X
							if(nx==0&&(ny==air1 || ny==air2)) continue;
							//확산
//							System.out.println("I am "+total+", and go ("+ny+", "+nx+") sub"+sub);
							update[ny][nx] += sub;
							possible++; //원래 위치 값 갱신을 위하여 가능한 방향 저장
						}
					}
					map[i][j] -= possible*sub;
				}
			}
			
			//확산된 값 업데이트 및 초기화
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += update[i][j];
					update[i][j] = 0;
				}
			}
			
			//공기청정기 작동
			rotateAntiCw(air1);
			rotateCw(air2);	
		}
		
		//현재 방 안에 남아있는 미세먼지 양
		int dust = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] > 0) dust += map[i][j];
			}
		}
		System.out.println(dust);
	}
}
