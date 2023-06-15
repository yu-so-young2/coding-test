import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * 스캐너 사용(코드 길이 비교)
 * => 결과: 버퍼드 사용하니까 1000->1300 증가
 */

public class Solution_SWEA_9229_한빈이와SpotMart_develop {
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
			
			snacks.sort(Collections.reverseOrder());
			
			int result = -1; //default -1
			
			int start = 0; int end = snacks.size();

			search:
			for (int i = start; i < end; i++) {
				for (int j = i+1; j < end; j++) {
					int sum = snacks.get(i)+snacks.get(j);
					if(sum <= maxWeight && sum > result) {
						result = sum; //최적해 갱신
						end = j; //end index 갱신
						if(result == maxWeight) break search; //완전 최적해 찾았으니 탐색 중단
						break;
					}
				}
			}
			sb.append("#"+tc+" "+result+"\n");
		}//for each test case
		System.out.println(sb.toString());
		sc.close();
	}
}
