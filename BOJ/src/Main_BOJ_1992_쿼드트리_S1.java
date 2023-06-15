import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1992. 쿼드트리
 * 분할 재귀 호출
 * 
 */

public class Main_BOJ_1992_쿼드트리_S1 {
	static StringBuilder sb = new StringBuilder();
	static int T, map[][];
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		T = Integer.parseInt(br.readLine());
		map = new int [T][T];
		for (int i = 0; i < T; i++) {
			str = br.readLine();
			for (int j = 0; j < T; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		
		solve(0, 0, T);
		System.out.println(sb);
	}//main

	
	public static void solve(int x, int y, int size) {
		if(size == 1) {
			sb.append(isSame(x, y, size));
			return;
		}
		
		//다른 숫자가 섞여 있다면 -> 재귀적 분할, 괄호 열기
		if(isSame(x, y, size)==-1) {
			int newSize = size/2;
			sb.append("(");
			solve(x, y, newSize); //왼쪽 위(행 일정, 열 일정)
			solve(x, y+newSize, newSize); //오른쪽 위(행 일정, 열 변경)
			solve(x+newSize, y, newSize); //왼쪽 아래(행 변경, 열 일정)
			solve(x+newSize, y+newSize, newSize); //오른쪽 아래(둘 다 변경)
			sb.append(")");
		}
		else {
			sb.append(isSame(x, y, size));
		}
	}
	

	//해당 사각형이 모두 같은 숫자로 이루어져있는지 확인
	//다른 숫자라면 -1, 1로 이루어져 있다면 1, 0으로 이루어져 있다면 0 출력
	public static int isSame(int x, int y, int size) {
		int start = map[x][y]; //시작 값
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(map[x+i][y+j] != start) return -1;
			}
		}
		return start;
	}
	

}
