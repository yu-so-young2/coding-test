public class Solution_Programmers_KAKAO_코딩테스트공부 {
	public static void main(String[] args) {
		int alp = 21;
		int cop = 10;
//		int[][] problems = {	{0,0,21,21,1}, {20, 21, 0, 0, 1} };
//		int[][] problems = {	{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2} };
//		int[][] problems = {	{10,15,2,1,2},{20,20,3,3,4} };
		int[][] problems = {	{10,15,2,1,2},{20,20,3,3,4} };
		int ans = solution(alp, cop, problems);
		System.out.println(ans);
	}

	
	public static int solution(int alp, int cop, int[][] problems) {
		int[][] dp = new int[300][300]; //dp[i][j] : i, j 능력치를 얻는 데에 소모된 최소 시간
		
		//만들어야 하는 alp, cop 찾기
		int alp_max = 0, cop_max = 0;
		for (int i = 0; i < problems.length; i++) {
			alp_max = Math.max(alp_max, problems[i][0]);
			cop_max = Math.max(cop_max, problems[i][1]);
		}
		
		//필요한 최대값보다 더 가지고 있는 경우 -> 필요한 최대값으로 보정
		alp_max = Math.max(alp_max, alp);
		cop_max = Math.max(cop_max, cop);
		System.out.println("start: "+alp+" "+cop+", find: "+alp_max+" "+cop_max);
		

		//MAX 값으로 초기화 -> 더 작은 값으로 갱신 예정
		for (int i = 0; i <= alp_max; i++) {
			for (int j = 0; j <= cop_max; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dp[alp][cop] = 0; //시작점

		/*
		 * 알고력: dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1)
		 * 코딩력: dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1)
		 * 문제풀: dp[i+alp_rwd][j+cop_rwd] = Math.min(dp[i+alp_rwd][j+cop_rwd], dp[i][j]+cost)
		 */
		
		for (int i = alp; i <= alp_max; i++) {
			for (int j = cop; j <= cop_max; j++) {
//				System.out.println("dp["+i+"]["+j+"]: "+dp[i][j]);
				dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
//				System.out.println(">> dp["+(i+1)+"]["+j+"]: "+dp[i+1][j]);
				dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
//				System.out.println(">> dp["+(i)+"]["+(j+1)+"]: "+dp[i][j+1]);
				for (int p = 0; p < problems.length; p++) {
					int alp_req = problems[p][0];
					int cop_req = problems[p][1];
					if(i>=alp_req && j>=cop_req) { //풀수있다면
						int alp_rwd = problems[p][2];
						int cop_rwd = problems[p][3];
						int cost = problems[p][4];
						
						int new_alp = i+alp_rwd;
						int new_cop = j+cop_rwd;

						if(new_alp > alp_max) new_alp = alp_max;
						if(new_cop > cop_max) new_cop = cop_max;
						
						dp[new_alp][new_cop] = Math.min(dp[new_alp][new_cop], dp[i][j]+cost);				
//						System.out.println(">> dp["+(new_alp)+"]["+(new_cop)+"]: "+dp[new_alp][new_cop]);
					}
				}
				
			}
		}
		
		
		
		return dp[alp_max][cop_max];
	}
}
