package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17281_야구_G4 {
	static int N, perform[][], maxScore, sequence[];
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //이닝 수
		perform = new int[N+1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				perform[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxScore = 0;
		sequence = new int[9]; //순열 저장할 배열
		visited = new boolean[10];
		sequence[3] = 1;
		visited[1] = true;
		
		perm(0);
		System.out.println(maxScore);
	}

	/**
	 * <pre>1~9의 숫자로 순열 생성하는 함수</pre>
	 * @param cnt 현재 뽑은 숫자 수
	 * @param pick 현재 뽑은 숫자 Bitmask
	 */
	private static void perm(int cnt) {
		if(cnt == 9) { //순열 생성 완료
//			//순열 생성 확인 프린트
//			for (int i = 0; i < 9; i++) {
//				System.out.print(sequence[i]+" ");
//			}
//			System.out.println();
			
			
			
			int score = simulation(sequence); //생성한 순열 기반 시뮬레이션
			maxScore = Math.max(maxScore, score); //최대 점수 갱신
			return;
		}
		
		
		for (int i = 2; i < 10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sequence[cnt] = i;
				if(cnt==2) {
					perm(cnt+2);					
				} else {
					perm(cnt+1);
				}
				visited[i] = false;
			}
		}
	}

	
	
	private static int simulation(int[] sequence) {
//		System.out.print("simul "+ Arrays.toString(sequence)+" ");
		int now = 0; //현재 타자 인덱스
		ScoreBoard board = new ScoreBoard(1, 0, 0);
		
		while(board.inning < N+1) { //시합종료: 마지막 이닝이면서 3아웃
			//현재 타석의 퍼포먼스
			int curPlayer = sequence[now]; //현재 선수
			int curPerform = perform[board.inning][curPlayer]; //현재 선수의 퍼포먼스
//			System.out.print(curPlayer+":"+curPerform+" ");
			board.play(curPerform); //현재 퍼포먼스로 경기 진행
			now = (now==8)?0:now+1; //다음 타자
		}
//		System.out.println();
		return board.score;
	}
	
	static class ScoreBoard {
		int inning, outCnt, score;
		boolean[] base = new boolean[3]; //1루 2루 3루 선수 여부
		public ScoreBoard(int inning, int outCnt, int score) {
			super();
			this.inning = inning;
			this.outCnt = outCnt;
			this.score = score;
		}
		
		
		public void play(int curPerform) {
			switch(curPerform) {
			case 1: //안타
				//점수
				score += base[2]?1:0; //3루에 사람이 있었다면 점수 추가
				//진루
				for (int i = 2; i > 0; i--) {
					base[i] = base[i-1];
				}
				base[0] = true;
				break; 
			case 2: //2루타
				//점수
				score += base[2]?1:0; //3루에 사람이 있었다면 점수 추가
				score += base[1]?1:0; //2루에 사람이 있었다면 점수 추가
				//진루
				base[2] = base[0];
				base[1] = true;
				base[0] = false;
				break;
			case 3: //3루타
				//점수
				score += base[2]?1:0; //3루에 사람이 있었다면 점수 추가
				score += base[1]?1:0; //2루에 사람이 있었다면 점수 추가
				score += base[0]?1:0; //2루에 사람이 있었다면 점수 추가
				//진루
				base[2] = true;
				base[1] = false;
				base[0] = false;
				break;
			case 4: //홈런(4루타)
				//점수(베이스에 있는 사람 모두 + 타자까지)
				score += base[2]?1:0; //3루에 사람이 있었다면 점수 추가
				score += base[1]?1:0; //2루에 사람이 있었다면 점수 추가
				score += base[0]?1:0; //2루에 사람이 있었다면 점수 추가
				score += 1;
				//진루
				base[2] = false;
				base[1] = false;
				base[0] = false;				
				break;
			case 0: //아웃
				outCnt++;
				break;
			}
			
			//3아웃 체크
			if(outCnt == 3) {
				inning++; //다음 이닝
				base = new boolean[3]; //베이스 초기화
				outCnt = 0;
			}
		}
	}
}
