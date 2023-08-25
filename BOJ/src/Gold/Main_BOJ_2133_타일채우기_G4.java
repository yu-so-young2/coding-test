package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 아이디어
 * - 타일채우기 -> dp
 *   dp[i] = 3 * i 타일을 채울 수 있는 방법의 수
 * - 일단 홀수면 방법 없음, 0 출력
 * - 짝수면
 *  dp[i] = dp[i-2]*3 // 이전 단계에 3*2짜리 붙이기
 *          + (dp[2]+dp[4]+...+dp[i-4])*2 (계속해서 새로 생기는 케이스 역순 배치)
 *          + 2 // 새로 생기는 케이스
 *
 * 2. 시간복잡도
 * - O(N^2)
 *
 * 3. 자료구조
 * - int[N] dp
 */
public class Main_BOJ_2133_타일채우기_G4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 만약 홀수라면 방법 없음, 0 출력
        if(N%2==1) {
            System.out.println(0);
            return;
        }

        // dp 시작
        int[] dp = new int[N+1];
        dp[2] = 3;

        for (int i = 4; i <= N; i+=2) {
            dp[i] = dp[i-2]*3+2;
            for (int j = 2; j < i-2; j++) {
                dp[i]+=dp[j]*2;
            }
        }
        System.out.println(dp[N]);
    }
}
