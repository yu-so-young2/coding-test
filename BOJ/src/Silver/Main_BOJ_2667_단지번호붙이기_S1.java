package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_2667_단지번호붙이기_S1 {
    static int T, map[][], cnt;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        map = new int[T][T];
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            for (int j = 0; j < T; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

//        // 확인용 print
//        for (int i = 0; i < T; i++) {
//            for (int j = 0; j < T; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

        int region = 0;
        cnt = 0;
        visited = new boolean[T][T];
        List<Integer> cntList = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    region++;
                    cnt = 1;
                    visited[i][j] = true;
                    map[i][j] = region;
                    dfs(i, j, region);

                    cntList.add(cnt);
                }
            }
        }

//        // 확인용 print
//        for (int i = 0; i < T; i++) {
//            for (int j = 0; j < T; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

        System.out.println(region);
        Collections.sort(cntList);
        for (int i = 0; i < cntList.size(); i++) {
            System.out.println(cntList.get(i));
        }

    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    private static void dfs(int i, int j, int region) {
        int ny = 0, nx = 0;
        for (int k = 0; k < 4; k++) {
            ny = i+dy[k];
            nx = j+dx[k];
            if(ny < 0 || nx < 0 || ny >= T || nx >= T) continue;
            if(!visited[ny][nx] && map[ny][nx] == 1) {
                visited[ny][nx] = true;
                map[ny][nx] = region;
                cnt++;
                dfs(ny, nx, region);
            }
        }
    }
}
