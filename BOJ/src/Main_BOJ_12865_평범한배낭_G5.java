import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12865_평범한배낭_G5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // inputㅌㅈ
        // N K
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // int[N] weights, values
        // W V
        int[] weights = new int[N+1];
        int[] values = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weights[i] = w;
            values[i] = v;
        }

        //int dp[N+1][K+1]
        // 초기화: [N][0] = 0, [0][K] = 0
        int[][] dp = new int[N+1][K+1];

        // dp 채우기
        // dp[n][w] = Math.max(dp[n-1][w], dp[n-1][w-weights[n]+values[n]);
        for (int n = 1; n <= N; n++) {
            for(int w = 1; w <= K; w++) {
                if(w >= weights[n]){
                    dp[n][w] = Math.max(dp[n-1][w], dp[n-1][w-weights[n]]+values[n]);
                } else {
                    dp[n][w] = dp[n-1][w];
                }
            }
        }

        System.out.print(dp[N][K]);
    }
}