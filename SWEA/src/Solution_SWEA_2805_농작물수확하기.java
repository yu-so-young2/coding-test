import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_SWEA_2805_농작물수확하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			int profit = 0;
			int N = Integer.parseInt(br.readLine()); //size of farm
			int[][] farm = new int[N][N];
			
			//farm initialize
			for (int i = 0; i < farm.length; i++) {
				String str = br.readLine();
				for (int j = 0; j < farm.length; j++) {
					farm[i][j] = str.charAt(j)-'0';
				}
			}
			
			
			int n = 1; //해당 열에서 탐색할 칸 개수
			int center = N/2; //기준점
			
			for (int i = 0; i < farm.length; i++) {
				for (int j = -n/2; j < n/2+1; j++) {
					profit += farm[center+j][i];
				}
				//탐색할 개수 갱신
				if(i<N/2)	n += 2;
				else		n -= 2;
				
			}
			
			bw.write("#"+(testcase+1)+" "+profit+"\n");
		} //for each testcase
		bw.close();
		
	}

}
