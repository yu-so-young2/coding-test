package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17404_RGB거리2_G4 {
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    static int N, cost[][], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine()); // 집의 수 N
        cost = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken()); // 각 집의 R, G, B 비용
            }
        }


        // 첫번째 집이 r, g, b일 경우를 각각 계산
        int minCost = Integer.MAX_VALUE;
        for(int first = 0; first < 3; first++) {
            for (int i = 0; i < 3; i++) {
                if(i==first) dp[0][i] = cost[0][i];
                else dp[0][i] = 1000*1000+1;
            }

            for (int house = 1; house < N; house++) {
                dp[house][R] = Math.min(dp[house-1][G],dp[house-1][B])+cost[house][R];
                dp[house][G] = Math.min(dp[house-1][R],dp[house-1][B])+cost[house][G];
                dp[house][B] = Math.min(dp[house-1][G],dp[house-1][R])+cost[house][B];
            }

            for (int i = 0; i < 3; i++) {
                if(first != i) minCost = Math.min(minCost, dp[N-1][i]);
            }
        }

        System.out.println(minCost);
    }

}
