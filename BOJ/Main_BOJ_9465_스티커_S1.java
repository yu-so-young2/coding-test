import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9465_스티커_S1 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
           // System.out.println(N);
            int[][] dp = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            //초기값
            if(N > 1) {
                dp[0][1] += dp[1][0];
                dp[1][1] += dp[0][0];
            }
            int maxScore = 0;

            for(int j = 0; j < N; j++) {
                if(j >= 2) {
                    dp[0][j] += Math.max(dp[1][j-1], Math.max(dp[0][j-2], dp[1][j-2]));
                    dp[1][j] += Math.max(dp[0][j-1], Math.max(dp[0][j-2], dp[1][j-2]));
                }
                maxScore = Math.max(maxScore, Math.max(dp[0][j], dp[1][j]));
            }
            sb.append(maxScore+"\n");
        }
        System.out.println(sb);
    } //main
}
