import java.util.Scanner;

class Solution_SWEA_14413_격자판칠하기 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[] results = new int[T];

		test_case:
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][M];
			
			for (int i = 0; i < N; i++) { //각 행마다
				String row = sc.next();
				for (int j = 0; j < row.length(); j++) { //각 인덱스마다
                    //현재 인덱스 색 받아오기
					char c = row.charAt(j);
					if(c=='#') map[i][j] = 1; //# 검정
					else if(c=='.') map[i][j] = 0; //. 하양
					else map[i][j] = -1; //? 둘다

					if(j!=0) {
						//이전 인덱스 결정 X 상태
						if(map[i][j-1] == -1) {
							//현재 인덱스 결정 X -> 넘어감
							if(map[i][j] != -1) { //현재 인덱스 기준으로 최초인덱스의 색 결정
								if(j%2==1) {//현재 홀수 인덱스면 반대 색부터 시작
									map[i][0] = (map[i][j]+1)%2; //1->0, 0->1
								} else {
									map[i][0] =  map[i][j];
								}
							}
						}
						
						//이전 인덱스 결정 O 상태
						else {
							if(map[i][j] == -1) { //현재 인덱스 결정 X -> 이전 인덱스 기준으로 색칠
								map[i][j] = (map[i][j-1]+1)%2;
							}
							else {//현재 인덱스 결정 O -> 비교
								if(map[i][j] == map[i][j-1]) { //현재 색은 정해졌으나 이전 색과 같은 경우
									results[test_case-1] = 0; //impossible
									//남은 row 만큼 input 받아와서 버리기
									for (int k = 0; k < N-i-1; k++) {
										sc.next();
									}
									continue test_case;
								}
							}
						}
					}
				}//for j

					
				//각 행에서 문제가 없던 경우, 행끼리 비교
				//첫행 -> 이전 행과 비교 필요 없음, 넘어감
				//이전 행의 시작 색이 결정되지 않은 경우-> 비교 필요 없음, 넘어감
				
				//이전 행의 시작 색이 결정된 경우
				if(i!=0 && map[i-1][0]!=-1) {
					// => 현재 행 시작 색이 결정되지 않은 경우-> 이전 행 기반으로 현재 행 시작 색 결정 후 넘어감
					if(map[i][0] == -1) {
						map[i][0] = (map[i-1][0]+1)%2;
					}
					// => 현재 행 시작 색이 결정된 경우 -> 비교
					else {
						if(map[i][0]==map[i-1][0]) {
							results[test_case-1] = 0; //impossible
							for (int k = 0; k < N-i-1; k++) {
								sc.next();
							}
							continue test_case;							
						}
					}
				}
				
			} //for each row
			//아무런 문제가 없이 순회를 마쳤다면 possible 출력
            results[test_case-1] = 1; //possible
		} //test_case
		
		//output 출력
		String[] possible = {"impossible", "possible"};
		for (int i = 1; i <= T; i++) {
			System.out.println("#"+i+" "+possible[results[i-1]]);
		}
		
	}
}