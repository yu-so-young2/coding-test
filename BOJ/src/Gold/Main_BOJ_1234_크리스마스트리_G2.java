package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1234_크리스마스트리_G2 {
    static int depth, red, green, blue;
    static long dp[][][][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        depth = Integer.parseInt(st.nextToken()); // 트리의 깊이
        red = Integer.parseInt(st.nextToken()); // 장식의 개수
        green = Integer.parseInt(st.nextToken());
        blue = Integer.parseInt(st.nextToken());

        // dp[x][i][j][k] : 빨강 i, 초록 j, 파랑 k 개로 x 층을 만들 수 있는 경우의 수
        dp = new long[depth+1][red+1][green+1][blue+1];
        for (int i = 0; i <= red; i++) {
            for (int j = 0; j <= green; j++) {
                for (int k = 0; k <= blue; k++) {
                    dp[0][i][j][k] = 1;
                }
            }
        }

        // 현재 층을 만들 수 있는 조합들에 대하여
        // (2,2,2) 라면 dp[x][i][j][k] += dp[x-1][i-2][j-2][k-2];
        // 이때 i-2, j-2, k-2 가 모두 0 이상이어야 함
        // 이때 순열이므로 P(2,2,2) = 6!/2!2!2!


        for (int i = 1; i <= depth; i++) {
            perm(i, 0, 0, 0, 0);
        }


        System.out.print(dp[depth][red][green][blue]);
    }

    public static void perm(int depth, int cnt, int r, int g, int b) {
        if(cnt == depth) {
            System.out.println(depth+" => "+r+","+g+","+b);
            if(valid(r,g,b)) { // 모두 같은 수이면
                System.out.println("=> count!!!");
                for (int i = 0; i <= red; i++) {
                    for (int j = 0; j <= green; j++) {
                        for (int k = 0; k <= blue; k++) {
                            if(i-r>=0 && j-g>=0 && k-b>=0) {
                                dp[depth][i][j][k] += dp[depth-1][i-r][j-g][k-b];
                                System.out.println("dp["+depth+"]["+i+"]["+j+"]["+k+"] = "+dp[depth][i][j][k]);
                            }
                        }
                    }
                }

            }
            return;
        }

        perm(depth, cnt+1, r+1, g, b);
        perm(depth, cnt+1, r, g+1, b);
        perm(depth, cnt+1, r, g, b+1);
    }

    private static boolean valid(int r, int g, int b) {
        int total = r+g+b;
        int count = (r>0?1:0)+(g>0?1:0)+(b>0?1:0);
        if(r>0 && r!=total/count) return false;
        if(g>0 && g!=total/count) return false;
        if(b>0 && b!=total/count) return false;

        return true;
    }
}