import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2225_합분해_G5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        // N K
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N+1][K+1];
        for (int i = 0; i < N+1; i++) { // 자기 자신 1개만을 사용
            dp[i][1] = 1;
        }
        for (int j = 0; j < K+1; j++) { // 하나도 사용하지 않음
            dp[0][j] = 1;
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 2; j < K+1; j++) {
                for (int k = 0; k <= i; k++) {
                    dp[i][j] += dp[k][j-1];
                    dp[i][j] %= 1000000000;
                }
            }
        }

        long ans = dp[N][K];
        System.out.println(ans);

    }
}