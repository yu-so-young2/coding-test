import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 4013. 특이한 자석
 * 구현
 * 
 */

public class Solution_SWEA_4013_특이한자석 {
	static final int CLOCKWISE = 1;
	static final int ANTI_CLOCKWISE = -1;
	static int T, K, rotating[], score;
	static ArrayList<Magnet> m;
	
	public static class Magnet {
		int top, left, right;
		int polar[];

		public Magnet(int top, int[] polar) {
			super();
			this.top = top;
			this.left = 6;
			this.right = 2;
			this.polar = polar.clone();
		}

		
		public void rotate(int wise) {
			//시계방향 회전: idx 모두 왼쪽으로
			if(wise == CLOCKWISE) {
				this.top--;
				this.left--;
				this.right--;
				if(this.top<0) this.top += 8;
				if(this.left<0) this.left += 8;
				if(this.right<0) this.right += 8;				
			}
			//반시계방향 회전: idx 모두 오른쪽으로
			else if(wise == ANTI_CLOCKWISE) {
				this.top++;
				this.left++;
				this.right++;
				if(this.top>=8) this.top -= 8;
				if(this.left>=8) this.left -= 8;
				if(this.right>=8) this.right -= 8;				
			}
		}
		
	}
	
	public static void rotate(int magnetNum, int rotateWise) {
		int cur = magnetNum;
		int curWise = rotateWise;
		
		//나 회전
		rotating[cur] = curWise;
		
		//왼쪽 탐색(나의 왼쪽과 내 왼쪽놈의 오른쪽 비교)
		while(cur!=0) { //일단 왼쪽에 더 있으면 가			
			Magnet me = m.get(cur);
			Magnet left = m.get(cur-1);
			
			//나의 왼쪽과 내 왼쪽놈의 오른쪽 비교
			if(me.polar[me.left] == left.polar[left.right]) {
				break; //더이상 왼쪽 탐색할 필요 없음
			}
			
			//왼쪽놈 나랑 반대로 회전시키고, 더 가(cur--)
			curWise = (-1)*curWise;
			rotating[--cur] = curWise;

		}		
		cur = magnetNum;
		curWise = rotateWise;
		//오른쪽 탐색(나의 오른쪽과 내 오른쪽놈의 죈쪽 비교)
		while(cur!=3) { //일단 오른쪽에 더 있으면 가
			Magnet me = m.get(cur);
			Magnet right = m.get(cur+1);
			
			//나의 오른쪽과 내 오른쪽놈의 왼쪽 비교
			if(me.polar[me.right] == right.polar[right.left]) {
				break; //더이상 오른쪽 탐색할 필요 없음
			}
			
			//오른놈 나랑 반대로 회전시키고, 더 가(cur++)
			curWise = (-1)*curWise;
			rotating[++cur] = curWise;
		}

		
		//회전 및 회전방향 초기화
		for (int i = 0; i < rotating.length; i++) {
			m.get(i).rotate(rotating[i]);
			rotating[i] = 0;
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) { //for each test case
			m = new ArrayList<>();
			K = Integer.parseInt(br.readLine());
			rotating = new int[4];
			for (int i = 0; i < 4; i++) {
				int[] temp = new int[8];
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < temp.length; j++) {
					temp[j] = Integer.parseInt(st.nextToken());
				}
				m.add(new Magnet(0, temp));
			}
			//회전 정보
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int magnetNum = Integer.parseInt(st.nextToken())-1;
				int rotateWise = Integer.parseInt(st.nextToken());
				
				//회전 구현
				rotate(magnetNum, rotateWise);
				
			}
			
			score = 0;
			//점수 구하기
			for (int i = 0; i < 4; i++) {
				Magnet magnet = m.get(i);
				score += Math.pow(2, i)*magnet.polar[magnet.top];
			}
			
			sb.append("#"+tc+" "+score+"\n");
		}
		System.out.println(sb);
	}
}
