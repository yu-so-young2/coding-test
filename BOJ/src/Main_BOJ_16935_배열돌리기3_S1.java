import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16935_배열돌리기3_S1 {
	public static int[][] arr;
	public static int N;
	public static int M;
	public static int R;
	public static int[] cmd;
	
	
	public static void reverseUpDown() {
		for (int i = 0; i < N/2; i++) {
			//i번째와 N-1-i번째 row swap
			for (int j = 0; j < M; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[N-1-i][j];
				arr[N-1-i][j] = temp;
			}
		}
	}
	public static void reverseLeftRight() {
		for (int j = 0; j < M/2; j++) {
			//j번째와 M-1-j번째 column swap
			for (int i = 0; i < N; i++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][M-1-j];
				arr[i][M-1-j] = temp;
			}
		}
	}
	public static void rotateLeft() {
		int[][] temp = arr.clone();
		arr = new int[M][N];

		//복사해오기
		//temp의 M-1-j 열이 arr의 j 행
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = temp[j][M-1-i];
			}
		}
		N = arr.length;
		M = arr[0].length;
	}
	public static void rotateRight() {
		int[][] temp = arr.clone();
		arr = new int[M][N];

		//복사해오기
		//temp의 j 열이 arr의 j 행인데 반대
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = temp[j][i];
			}
		}
		N = arr.length;
		M = arr[0].length;
		reverseLeftRight();
	}

	public static void rotateRight2() {
		//2사분면에 있는 모든 원소에 대하여
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				int temp = arr[i][j];
				//한 바퀴 돌며 값 변경
				arr[i][j] = arr[i+(N+1)/2][j];
				arr[i+(N+1)/2][j] = arr[i+(N+1)/2][j+(M+1)/2];
				arr[i+(N+1)/2][j+(M+1)/2] = arr[i][j+(M+1)/2];
				arr[i][j+(M+1)/2] = temp;
			}
		}
	}

	public static void rotateLeft2() {
		//2사분면에 있는 모든 원소에 대하여
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				int temp = arr[i][j];
				//한 바퀴 돌며 값 변경
				arr[i][j] = arr[i][j+(M+1)/2];
				arr[i][j+(M+1)/2] = arr[i+(N+1)/2][j+(M+1)/2];
				arr[i+(N+1)/2][j+(M+1)/2] = arr[i+(N+1)/2][j];
				arr[i+(N+1)/2][j] = temp;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
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
		cmd = new int[R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			cmd[i]= Integer.parseInt(st.nextToken());
		}
		
		//연산 수행
		for (int i = 0; i < R; i++) {
			switch(cmd[i]) {
			case 1: //상하반전
				reverseUpDown();
				break;
			case 2: //좌우반전
				reverseLeftRight();
				break;
			case 3: //오른쪽 회전
				rotateRight();
				break;
			case 4: //왼쪽 회전
				rotateLeft();
				break;
			case 5: //그룹 오른쪽 회전
				rotateRight2();
				break;
			case 6: //그룹 왼쪽 회전
				rotateLeft2();
				break;
			}
		}
		
		//print output
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	} //main
}
