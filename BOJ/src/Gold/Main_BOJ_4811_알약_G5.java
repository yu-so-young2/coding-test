package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_4811_알약_G5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long dp[][] = new long[31][31];
        for (int h = 0; h <= 30; h++) {
            for (int w = 0; w <= 30; w++) {
                if (h > w) continue;
                if (h == 0) dp[w][h] = 1;
                else dp[w][h] = dp[w - 1][h] + dp[w][h - 1];
            }
        }

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;
            sb.append(dp[n][n]+"\n");
        }
        System.out.print(sb);

    }
}