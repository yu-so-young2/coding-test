import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * SWEA 1218. 괄호짝짓기
 * 스택 이용 간단 문제
 * 
 */

public class Solution_SWEA_1218_괄호짝짓기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack;
        String str;
        int n;
        int result;

        for (int tc = 1; tc <= 10; tc++) {
            result = 1;
            n = Integer.parseInt(br.readLine()); // 개수 받아오기
            str = br.readLine();
            stack = new Stack<>();

            for (int i = 0; i < n; i++) {
            	//열리는 괄호일 경우 push
            	if(str.charAt(i)=='(' ||
            			str.charAt(i)=='[' ||
            			str.charAt(i)=='{' ||
            			str.charAt(i)=='<' )
            		stack.add(str.charAt(i));
            	
            	//닫히는 괄호일 경우 짝 지어서 pop
            	else
            		if((str.charAt(i)==')' && stack.peek()=='(')
            				|| (str.charAt(i)==']' && stack.peek()=='[')
            				|| (str.charAt(i)=='}' && stack.peek()=='{')
            				|| (str.charAt(i)=='>' && stack.peek()=='<'))
            			stack.pop();
            		else {
            			result = 0;
            			break;
            		}
            }
            
            //미처 닫히지 않은 괄호가 있다면
            if (!stack.empty()) {
                result = 0;
            }
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }// main
}
