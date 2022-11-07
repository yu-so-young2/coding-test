import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1210_사다리타기1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[100][100];
		int destination = 0;
		for (int TC = 1; TC <= 10; TC++) {
			br.readLine();
			//map construct
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());					
					if(map[i][j]==2) destination = j;
				}
			}
			
			int i = 99; int j = destination;
			while(i > 0) {
				//왼, 오른쪽에 있는지 확인 후 사다리 이동
				if(j-1>=0 && map[i][j-1]==1) {//왼쪽 사다리 이동
					while(j-1>=0 && map[i][j-1]==1)
						j--;
					
				}
				else if(j+1<=99 && map[i][j+1]==1) {//오른 사다리 이동
					while(j+1<=99 && map[i][j+1]==1)
						j++;					
				}
				//한칸 내려가기
				i--;
			}
			
			
			
			System.out.println("#"+TC+" "+j);
		}
	}
}
