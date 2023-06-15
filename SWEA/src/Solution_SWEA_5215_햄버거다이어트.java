import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_5215_햄버거다이어트 {
	
	public static int num;
	public static int maxCalories;
	public static int maxScore;
	
	public static int[] scores;
	public static int[] calories;
	
	
	public static void search(int cnt, int score, int calorie) {
		if(calorie > maxCalories) return; //제한 칼로리 넘을 시 해당 경우 탐색 종료
		if(cnt == num) { //마지막 재료까지 확인했을 때
			maxScore = Math.max(maxScore, score);
			return;
		}
		//현재 재료를 포함하는 경우
		search(cnt+1, score+scores[cnt], calorie+calories[cnt]);
		//현재 재료를 포함하지 않는 경우
		search(cnt+1, score, calorie);
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //test case
		for (int tc = 1; tc <= T; tc++) {
			//재료 수, 제한 칼로리
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			maxCalories = Integer.parseInt(st.nextToken());

			//점수, 칼로리
			scores = new int[num];
			calories = new int[num];
			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}

			maxScore = 0;
			search(0,0,0);
			
			sb.append("#"+tc+" "+maxScore+"\n");
		}
		System.out.println(sb.toString());	
	}
}
