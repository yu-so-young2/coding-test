/**
 * 1. 아이디어
 * - i번째 포도주에 대하여 다음 세 가지 경우가 있을 수 있음
 *  1)  X O O : 직전 와인 마심 + 지금 와인 마심
 *  2)    X O : 직전 와인 안 마심 + 지금 와인 마심
 *  3)      X : 지금 와인 안 마심
 * - 세 가지 경우에 대하여 가장 큰 값 = 해당 포도주까지 탐색했을 때 마실 수 있는 최대 값
 * - 이때, X 표시된 곳 전에도 포도주가 있다면 해당 포도주까지 탐색했을 때의 최대 값을 더해줘야 함
 *
 * => 최종 점화식
 * dp[i] = Math.max( wine[i]+wine[i-1]+dp[i-3] , wine[i]+dp[i-2] , dp[i-1] )
 *
 * 2. 시간복잡도
 * O(N)
 *
 * 3. 자료구조
 * wine[i] : i번째 포도주의 양
 * dp[i] : i번째 포도주까지 보았을 때 마실 수 있는 최대 포도주의 양
 */

package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2156_포도주시식_S1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 포도주 잔의 개수
        int[] wine = new int[N];
        int[] dp = new int[N];
        int a, b, c;
        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());

            // X O O
            //   X O
            //     X
            a = wine[i];
            if(i >= 1) a += wine[i-1];
            if(i >= 3) a += dp[i-3];
            b = wine[i];
            if(i >= 2) b += dp[i-2];
            c = 0;
            if(i >= 1) c += dp[i-1];


            dp[i] = Math.max(a, Math.max(b, c));
        }

        System.out.println(dp[N-1]);

    }
}
