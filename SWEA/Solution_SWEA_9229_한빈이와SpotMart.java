
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 스캐너 사용(코드 길이 비교)
 * => 결과: 버퍼드 사용하니까 1000->1300 증가
 */

public class Solution_SWEA_9229_한빈이와SpotMart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt(); //테스트케이스 수
		for (int tc = 1; tc <= T; tc++) {
			int numSnack = sc.nextInt();
			int maxWeight = sc.nextInt(); //무게 합 제한
			
			ArrayList<Integer> snacks = new ArrayList<>();				
			for (int i = 0; i < numSnack; i++) { //각 무게 입력
				snacks.add(sc.nextInt());
			}
			
			int result = -1; //default -1
			for (int i = 0; i < snacks.size(); i++) {
				for (int j = i+1; j < snacks.size(); j++) {
					int sum = snacks.get(i)+snacks.get(j);
					if(sum <= maxWeight && sum > result) {
						result = sum;
					}
				}
			}
			
			sb.append("#"+tc+" "+result+"\n");
		}//for each test case
		System.out.println(sb.toString());
		sc.close();
	}
}
