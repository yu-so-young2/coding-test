package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2961_도영이가만든맛있는음식_S2 {
	public static int sours[];
	public static int bitters[];
	public static int num;
	public static int minDiff;
	
	public static void search(int cnt, int sour, int bitter) {
		if(cnt == num) { //마지막 재료까지 확인했을 때
			if(bitter == 0) return; //최소 1개 이상의 재료 필요 
			int diff = 0;
			diff = Math.abs(sour-bitter);
			minDiff = Math.min(minDiff, diff);
			return;
		}
		//현재 재료를 포함하는 경우
		search(cnt+1, sour*sours[cnt], bitter+bitters[cnt]);
		//현재 재료를 포함하지 않는 경우
		search(cnt+1, sour, bitter);
	}

	
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		num = Integer.parseInt(br.readLine()); //재료의 개
		sours = new int[num];
		bitters = new int[num];
		//신맛, 쓴맛
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}

		minDiff =Integer.MAX_VALUE;
		search(0,1,0);
		System.out.println(minDiff);
		}
}
