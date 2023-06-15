import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1759_암호만들기_G5 {
	public static boolean visited[];
	public static char temp[]; //temp combination 저장
	public static int count[]; //모음 자음 개수 저장
	public static int L, C;
	public static char chars[];
	
	public static void comb(int idx, int cnt) {
		if(cnt == L) {
			if(count[0]>=1 && count[1]>=2) {
				for (int i = 0; i < temp.length; i++) {
					System.out.print(temp[i]);
				}
				System.out.println();
			}
			return;
		}
		
		for (int i = idx; i < C; i++) {
			temp[cnt] = chars[i];
			if(isVowel(chars[i])) {
				count[0]++;;
			} else {
				count[1]++;
			}
			comb(i+1, cnt+1);
			if(isVowel(chars[i])) {
				count[0]--;;
			} else {
				count[1]--;
			}
		}
	}
	
	//현재 문자가 모음인지 아닌지 확인해주는 함수
	public static boolean isVowel(char c) {
		//a, e, i, o, u 중 하나라면 true 리턴
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return true;
		}
		//아니라면 false 리턴
		return false;
	}//isVowel()
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		chars = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			chars[i] = st.nextToken().charAt(0);
		}
		
		temp = new char[L];
		count = new int[2];
		Arrays.sort(chars);
		
		comb(0,0);
		
	}
}
