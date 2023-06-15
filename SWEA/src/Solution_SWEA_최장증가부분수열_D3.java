import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
dp 구현
 */
public class Solution_SWEA_최장증가부분수열_D3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T, N;
        int nums[], dp[];

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            dp = new int[N];
            int max = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if(nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[j]+1, dp[i]);
                    }
                }
                max =Math.max(dp[i], max);
            }
            sb.append("#"+tc+" "+max+"\n");
        }

        System.out.println(sb);
    }
}