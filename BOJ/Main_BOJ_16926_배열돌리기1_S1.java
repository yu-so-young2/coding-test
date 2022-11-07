import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BOJ_16926_배열돌리기1_S1 {
	public static int[][] arr;
	public static int N;
	public static int M;
	public static int R;
	
	public static void rotate() {
		int shell = Math.min(N, M)/2;
		for (int s = 0; s < shell; s++) {
			int first = arr[s][s]; //밀기 위해 처음 값 저장해두기
			//왼쪽으로 밀기
			for (int j = s+1; j <= M-1-s; j++) {
				arr[s][j-1] = arr[s][j];
			}
			//위로 밀기
			for (int i = s+1; i <= N-1-s; i++) {
				arr[i-1][M-s-1] = arr[i][M-s-1];
			}
			//오른쪽으로 밀기
			for (int j = M-1-s-1; j >= s; j--) {
				arr[N-1-s][j+1] = arr[N-1-s][j];
			}
			//아래로 밀기
			for (int i = N-1-s-1; i >= s; i--) {
				arr[i+1][s] = arr[i][s];
			}
			arr[s+1][s] = first;
			}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
		
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
		
		//rotate
		for (int i = 0; i < R; i++) {
			rotate();
		}
		
		//print output
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				sb.append(arr[i][j]+" ");
//			}
//			sb.append("\n");
//		}
//		System.out.print(sb.toString());
		
		//print output
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(arr[i][j]+" ");
			}
			bw.write("\n");
		}
		bw.close();
	} //main
}
