import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 개선사항
 * 1) 링크드로 변경
 * 2) 10개만 저장
 */

public class Solution_SWEA_1228_암호문1_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N; //암호문 길이
		int x, y; //x: 추가 삽입할 인덱스, y: 삽입할 숫자 개수
		
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList<Integer> pwd = new LinkedList<>();
			N = Integer.parseInt(br.readLine()); //N 버리기
			st = new StringTokenizer(br.readLine()); //원본 암호문
			for (int i = 0; i < N ; i++) { //10개까지만 받기
				pwd.add(Integer.parseInt(st.nextToken()));
				if(i == 10) break;
			}
			while(st.hasMoreTokens()) st.nextToken();
			
			br.readLine(); //명령어 개수 버리기
			st = new StringTokenizer(br.readLine()); //명령어
			
			while(st.hasMoreTokens()) {
				st.nextToken(); // I 버리기
				x = Integer.parseInt(st.nextToken()); //삽입 시작 인덱스
				y = Integer.parseInt(st.nextToken()); //삽입할 숫자 개수

				if(x >= 10) { //어차피 10개까지만 출력할 것이므로 10 이후 인덱스 변경은 무시 
					for (int i = 0; i < y; i++) {
						st.nextToken();
					}
				}
				else {
					for (int i = 0; i < y; i++) {
						pwd.add(x++, Integer.parseInt(st.nextToken()));
					}
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
