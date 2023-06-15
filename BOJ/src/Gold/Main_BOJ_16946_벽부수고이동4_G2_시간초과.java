package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16946_벽부수고이동4_G2_시간초과 {
    static int N, M, map[][], ans[][], cnt;
    static boolean visited[][];
    static int group[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (str.charAt(j)-'0'==1?-1:0);
            }
        }

//        // 확인용 프린트
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("============");

        // dfs 시행하며 빈 구역 나누기
        group = new int[N*M];
        cnt = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0 && !visited[i][j]){
                    cnt++;
                    group[cnt]++;
                    visited[i][j] = true;
                    dfs(i, j, cnt);
                }
            }
        }


//        // 확인용 프린트
//        for (int i = 1; i <= cnt; i++) {
//            System.out.println(group[i]);
//        }
//        System.out.println("cnt: "+cnt);
//
//        // 확인용 프린트
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==-1) { // 벽이라면
                    // 상하좌우 탐색하여 뭐뭐있는지 확인
                    boolean[] find = new boolean[cnt+1];
                    for (int k = 0; k < 4; k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||ny>=N||nx>=M||map[ny][nx]==-1) continue;

                        find[map[ny][nx]] = true;
                    }

                    // 있는 아이들의 숫자 더하기
                    int sum = 1;
                    for (int k = 1; k <= cnt; k++) {
                        if(find[k]){
                            sum += group[k];
                        }
                    }
                    ans[i][j] = sum%10;

                }
            }
        } // for

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]);
//                System.out.print(ans[i][j]);
            }
            sb.append("\n");
//            System.out.println();
        }
        System.out.println(sb);

    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    private static void dfs(int y, int x, int cnt) {
        map[y][x] = cnt;
        for (int k = 0; k < 4; k++) {
            int ny = y+dy[k];
            int nx = x+dx[k];
            if(ny<0||nx<0||ny>=N||nx>=M||visited[ny][nx]||map[ny][nx]!=0) continue;

            group[cnt]++; // 해당 구역의 개수++
            visited[ny][nx] = true;
            dfs(ny, nx, cnt);
        }
    }
}