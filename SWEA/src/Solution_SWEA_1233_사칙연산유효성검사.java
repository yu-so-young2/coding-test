import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1233_사칙연산유효성검사 {
	public static void main(String[] args) throws NumberFormatException, IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
				
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine()); //num of nodes
			char[] myArr = new char[N+1];
			//initialize tree
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken(); //node 번호 버리기
				myArr[i] = st.nextToken().charAt(0);
			}
			
			//1) 숫자가 부모노드일 경우 무효
            //2) 연산자가 리프노드일 경우 무효
			int valid = 1;
			for (int i = 1; i < myArr.length; i++) {
				char current = myArr[i];
				if(i*2 < myArr.length-1) { //parent node
					if(Character.isDigit(current)) {
						valid = 0; break;
					}
				}
				else { //leaf node
					if(!Character.isDigit(current)) {
						valid = 0; break;
					}
				}	
			}
			sb.append("#"+tc+" "+valid+"\n");
		}//for each test case
		System.out.println(sb.toString());
	}//main
}//end class