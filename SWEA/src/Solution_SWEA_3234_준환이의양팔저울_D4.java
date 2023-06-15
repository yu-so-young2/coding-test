import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_3234_준환이의양팔저울_D4 {
	static int T, N, caseNum, weights[], perm[];
	static boolean visited[];
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			weights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			caseNum = 0;
			perm = new int[N];
			visited = new boolean[N]; //해당 인덱스를 순열에 추가했는지 확인
			permutation(0); //N!의 순열 생성
			
			sb.append("#"+tc+" "+caseNum+"\n");
		}//for each test case
		System.out.println(sb);
	}//main

	public static void permutation(int idx) {
		if(idx == N) { //무게추 올릴 순서 정해짐(각 무게추의 순서는 perm[N]에 저장되어있고,
							//무게는 weight[perm[i]]로 불러오기)
			//dfs: 주어진 순열에 대해서 올릴 수 있는 2^N가지 확인하며 caseNum++
			dfs(0, 0, 0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[idx] = i;
			permutation(idx+1);
			visited[i] = false;
		}
	}

	//현재 왼쪽 무게, 현재 오른쪽 무게, 이제 올릴 무게추의 번호
	public static void dfs(int left, int right, int idx) {
		if(right > left) return; //잘못된 시행
		if(idx==N) { //모든 추 올리기 완료
			caseNum++;
			return;
		}
		int curWeight = weights[perm[idx]];
		dfs(left+curWeight, right, idx+1);
		dfs(left, right+curWeight, idx+1);
	}
}