import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 나무키우기
 * 필요한 수(높이)에 도달하기 위해 (1+2) 페어가 얼마나 많이 필요한지
 * => 1) 최대 높이 나무 구하기
 * => 2) 1) 기반으로하여 각 나무마다 필요한 높이 합산
 * => 3) 2)에서 구한 총 필요 높이에 도달하기 위한 (1+2) 페어 개수 구하기 (나누기 3)
 * => 4) 나머지 1이라면 하루 더 필요, 2라면 이틀 더 필요
 * 
 */

public class Solution_SW역테_나무키우기 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];
			st = new StringTokenizer(br.readLine());
			
			int maxHeight = 0; //최고 나무 높이 저장
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, trees[i]); //최고 높이 갱신
			}
			
			
			//필요한 총 길이 합산
			int total = 0;
			for (int i = 0; i < N; i++) {
				total += (maxHeight - trees[i]);
			}
			System.out.println("최고높이: "+ maxHeight+"  필요높이총합: "+total);
			
			//필요 일수: ( 필요한 1+2 페어 수 )+ ( 하루 혹은 이틀 )
			int days = (total/3)*2+(total%3);
			
			sb.append("#"+tc+" "+days+"\n");
		}
		System.out.println(sb);
	} //main
}

