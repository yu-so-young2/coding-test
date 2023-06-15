import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 링크드리스트로 구현
 */

public class Solution_SWEA_1228_암호문1_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N;
		int x, y; //x: 추가 삽입할 인덱스, y: 삽입할 숫자 개수
		
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList<Integer> pwd = new LinkedList<>();
			N = Integer.parseInt(br.readLine()); //원본 암호문의 길이 N
			
			st = new StringTokenizer(br.readLine()); //원본 암호문
			for (int i = 0; i < N; i++) {
				pwd.add(Integer.parseInt(st.nextToken()));
			}
			
			br.readLine(); //명령어의 개수(5~10)
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				st.nextToken(); // I 버리기
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				for (int i = 0; i < y; i++) {
					pwd.add(x++, Integer.parseInt(st.nextToken()));
				}
			} //명령문 수행

			sb.append("#"+tc+" ");
			for (int i = 0; i < 10; i++) {
				sb.append(pwd.get(i)+" ");
			}
			sb.append("\n");
		} //for each test case
		System.out.println(sb);
	} //main
}
