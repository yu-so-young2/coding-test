package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 컨베이어 벨트 위의 로봇
 * 
 */

public class Main_BOJ_20055_컨베이어벨트위의로봇_G5 {
	static int N, K;
	static int[] belt; //벨트 내구성
	static boolean robots[];
	
	static int start, end;
	static int dura; //현재 유효한 칸의 개수
	static int step;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt= new int[2*N];
		robots= new boolean[2*N];
		start = 0;
		end = N-1;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < belt.length; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
			if(belt[i] != 0) dura++;
		}
		
		
		step = 0; //초기상태
		
		while(true) {
			
			//종료조건
			if(2*N-dura >= K) break;
			
			step++;
//			System.out.println(step+"단계 시작");
			
			//1단계- 벨트 회전
			start = (start-1)==-1 ? 2*N-1 : start-1;
			end = (end-1)==-1 ? 2*N-1 : end-1;

			//내리는 곳에 있다면 즉시 내리기
			if(robots[end]) robots[end] = false;
			
			//2단계- 로봇이동, 내구도 감소			
			//끝에 있는 로봇부터(end-1부터 내려가기)
			//로봇이 있는 위치 받아오기
			//갈 위치의 내구도 검사
			//0이 아니라면 move(해당 위치로 로봇 on, 내구도 감소, 원래 있던 곳에서 제거)
			//내구도 감소 시 0이 되면 dura--;
			
			int curIdx = (end-1)==-1 ? 2*N-1 : end-1;
			for (int k = 0; k < N-1; k++) {
				if(!robots[curIdx]) {
					curIdx = (curIdx-1)==-1 ? 2*N-1 : curIdx-1;
					continue;
				}
				
//				System.out.println("curIdx: "+curIdx+" and "+belt[curIdx]);
				
				int nextIdx = (curIdx+1)==2*N ? 0 : (curIdx+1);
//				System.out.println("nextIdx: "+nextIdx+" and "+belt[nextIdx]);
				//갈 자리에 로봇이 없고 내구성이 남아 있을 경우에만 이동
				if(!robots[nextIdx] && belt[nextIdx]!=0) {
					//해당 위치로 이동
					robots[curIdx] = false;
					robots[nextIdx] = true;
					belt[nextIdx]--;
					if(belt[nextIdx]==0) dura--;
				}
								
				//다음 칸으로 이동
				curIdx = (curIdx-1)==-1 ? 2*N-1 : curIdx-1;
			}
			
			//내리는 곳에 있다면 즉시 내리기
			if(robots[end]) robots[end] = false;
			
			//3단계- 로봇올리기(내구도 감소)
			//end에 있는 로봇 제거
			//start의 내구도 검사
			//0 아니면 올리기(해당 위치로 로봇 올리기, 내구도 감소)
			//내구도 감소 시 0이 되면 dura--;
			if(belt[start] != 0) {
				robots[start] = true;
				belt[start]--;
				
				if(belt[start]==0) dura--;
			}
			
			
			
			System.out.println(step+"단계 후");
			int a;
			a = start;
			for (int i = 0; i < 2*N; i++) {
				System.out.print(belt[a]+" ");
				a = (a+1)==2*N ? 0 : a+1;
			}
			System.out.println();
			
			a = start;
			for (int i = 0; i < 2*N; i++) {
				System.out.print((robots[a]?1:0)+" ");
				a = (a+1)==2*N ? 0 : a+1;
			}
			System.out.println();
			



			
		}
		
		System.out.println(step);
		
	}//main
	
}
