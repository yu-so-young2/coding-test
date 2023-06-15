import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_SWEA_1954_달팽이숫자 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println("#"+tc);
			
			int[][] map = new int[n][n];
			int count = 1;
			int i = 0, j = -1, dir = 1, size = n;
			//map construct
			while(true) {
				for(int k = 0; k < n; k++) { //수평방향 이동
					j += dir;
					map[i][j] = count++;
				}
				n--;
				if(n == 0) break;
				
				for(int k = 0; k < n; k++) { //수직방향 이동
					i += dir;
					map[i][j] = count++;
				}
				dir *= -1; //방향전환
			}
			
			//map print
			for (int k = 0; k < size; k++) {
				for (int l = 0; l < size; l++) {
					bw.write(map[k][l] + " ");
				}
				bw.write("\n");
			}
			bw.flush();
		}
		bw.close();
	}
	
}
