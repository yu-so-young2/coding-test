package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class Main_BOJ_17406_배열돌리기4_G4 {
	public static int arr[][];
	public static int N;
	public static int M;
	public static int R;
	
	public static int cmd[][];
	public static int temp[];
	public static boolean[] isVisited;
	
	public static int arrSave[][];
	
	public static int min = Integer.MAX_VALUE;
	
	public static void perm(int cnt) {
		if(cnt == R) { //permutation finish
			//배열 임시저장 (2차원 깊은복사)
			arrSave = new int[N][M];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					arrSave[i][j] = arr[i][j];
				}
			}

			for (int i = 0; i < R; i++) { //회전 수행 
				rotateRight(cmd[temp[i]][0], cmd[temp[i]][1], cmd[temp[i]][2]);
			}
			
			for (int i = 0; i < N; i++) { //회전된 결과에 따라 row 합 구하기
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += arr[i][j];
				}
				min = Math.min(min, sum);
			}
			
			//저장해둔 배열 다시 불러오
			arr = new int[N][M];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					arr[i][j] = arrSave[i][j];
				}
			}
			
		}
		else {
			for (int i = 0; i < R; i++) {
				if(isVisited[i]) continue;
				temp[cnt] = i;
				isVisited[i] = true;
				perm(cnt+1);
				isVisited[i] = false;
			}
		}
	}
	
	public static void rotateRight(int r, int c, int shell) {
		for (int s = 1; s <= shell; s++) {
			int first = arr[r-s][c-s]; //밀기 위해 처음 값 저장해두기
			//위로 밀기
			for (int i = r-s+1; i <= r+s; i++) {
				arr[i-1][c-s] = arr[i][c-s];
			}
			//왼쪽으로 밀기
			for (int j = c-s+1; j <= c+s; j++) {
				arr[r+s][j-1] = arr[r+s][j];
			}
			//아래로 밀기
			for (int i = r+s-1; i >= r-s; i--) {
				arr[i+1][c+s] = arr[i][c+s];
			}
			//오른쪽으로 밀기
			for (int j = c+s-1; j >= c-s; j--) {
				arr[r-s][j+1] = arr[r-s][j];
			}
			arr[r-s][c-s+1] = first;
		}	
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		//연산 수
		cmd = new int[R][3];
		temp = new int[R];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			cmd[i][0] = Integer.parseInt(st.nextToken())-1;
			cmd[i][1] = Integer.parseInt(st.nextToken())-1;
			cmd[i][2] = Integer.parseInt(st.nextToken());
		}

		//calculate min rowSum & print output
		isVisited = new boolean[R];
		perm(0);
		
		//System.out.print(min);
		System.out.println(min);
		
	} //main
}	