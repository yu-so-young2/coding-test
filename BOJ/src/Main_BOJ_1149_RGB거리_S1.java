import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB거리_S1 {
    public static void main(String[] args) throws IOException {
        //입력받기 n, 배열선언, 배열초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int costs[][] = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 실행
        int dp[][] = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];


        for (int i = 1; i < n; i++) {
            for (int color = 0; color < 3; color++) {
                //이전 집까지의 비용 중 현재 색과 다른 색으로 칠한 비용 중 더 작은 비용 + 현재 색 비용
                dp[i][color] = Math.min(dp[i-1][(color+1)%3], dp[i-1][(color+2)%3]) + costs[i][color];
            }
        }

        //결과출력
        int result = Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
        System.out.println(result);
    }
}