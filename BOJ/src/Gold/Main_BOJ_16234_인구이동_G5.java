package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16234_인구이동_G5 {
    static int N, L, R, map[][], day, temp[][], cnt;
    static boolean visited[][], exist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        day = 0;
        while(true) {
            // dfs 연합 생성 -> cnt로 연합의 개수 확인
            // cnt : 연합의 개수
            // temp[][] : 연합 분포 정보
            // visited[][] : 방문했는지
            cnt = 0;
            temp = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    exist = false;
                    if(!visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i,j);
                        if(exist) cnt++;
                    }
                }
            }

            if(cnt == 0) break;

            // 인구 이동
            int totalCnt[] = new int[cnt+1];
            int totalSum[] = new int[cnt+1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(temp[i][j]!=0) {
                        totalCnt[temp[i][j]]++;
                        totalSum[temp[i][j]]+=map[i][j];
                    }
                }
            }


            for (int k = 1; k <= cnt; k++) {
                int res = totalSum[k]/totalCnt[k];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(temp[i][j]==k) {
                            map[i][j] = res;
                        }
                    }
                }
            }

            day++;
        }

        System.out.print(day);
    }

    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};
    private static void dfs(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int ny = i+dy[k];
            int nx = j+dx[k];
            if(ny<0|| nx<0|| ny>=N||nx>=N) continue;
            if(!visited[ny][nx] && inRange(Math.abs(map[i][j]-map[ny][nx]))) {
                visited[ny][nx] = true;
                temp[i][j] = cnt+1;
                temp[ny][nx] = cnt+1;
                exist = true;
                dfs(ny, nx);
            }
        }
    }

    private static boolean inRange(int diff) {
        if(diff >= L && diff <= R) return true;
        return false;
    }
}
