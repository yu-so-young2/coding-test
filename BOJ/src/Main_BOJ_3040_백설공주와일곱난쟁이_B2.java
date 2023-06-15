import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_3040_백설공주와일곱난쟁이_B2 {
	public static int dwarfs[];
	public static boolean visited[];
	
	public static void search(int cnt, int sum, int num) {
		if(sum == 100 && num == 7) {
			for (int i = 0; i < dwarfs.length; i++) {
				if(visited[i])
					System.out.println(dwarfs[i]);
			}
			return;
		}
		if(cnt == 9) {
			return;
		}
		
		visited[cnt] = true;
		search(cnt+1, sum+dwarfs[cnt], num+1);
		visited[cnt] = false;
		search(cnt+1, sum, num);
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dwarfs = new int[9];
		for (int i = 0; i < 9; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());			
		}
		visited = new boolean[9];
		search(0, 0, 0);
	}
}
