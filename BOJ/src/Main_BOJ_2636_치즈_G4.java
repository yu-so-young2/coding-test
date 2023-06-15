
/*
치즈
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_2636_치즈_G4 {
    static int H, W, airs, map[][];
    static boolean visited[][];

    static ArrayList<int[]> meltCheese;


    static final int AIR = 2;
    static final int HOLE = 0;
    static final int CHEESE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int mapSize = H*W;

        map = new int[H][W];
        int cheese = 0;

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == CHEESE) cheese++;
            }
        }

        //최초 1회 공기 0->2로 바꾸기 dfs
        visited = new boolean[H][W];

        visited[0][0] = true;
        map[0][0] = AIR;
        makeAir(0,0);

        int time = 0;
        while(true) {
            time++;

            //공기 dfs 하면서 녹일 치즈 인덱스 저장 dfs
            //녹일 치즈 리스트 초기화 후 녹일 치즈 저장
            //녹일 치즈 저장된 게 0이라면 현재 시간 출력하고 저장해둔 사이즈 출력
            visited = new boolean[H][W];

            meltCheese = new ArrayList<>();

            visited[0][0] = true;
            findMelt(0,0);

            if(cheese - meltCheese.size() == 0) {
                System.out.println(time+"\n"+cheese);
                return;
            }

            //녹일 치즈들 녹이면서 (본인 2로 바꾸기)
            // dfs(): 사방탐색 후 0이라면 공기로 바꾸기
            visited = new boolean[H][W];
            for (int i = 0; i < meltCheese.size(); i++) {
                int r = meltCheese.get(i)[0];
                int c = meltCheese.get(i)[1];

                map[r][c] = AIR;
                visited[r][c] = true;
                makeAir(r, c);

            }

            cheese -= meltCheese.size();

        }

    }

    static int[] dy= {-1,1,0,0};
    static int[] dx= {0,0,-1,1};

    public static void makeAir(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int ny = r+dy[i];
            int nx = c+dx[i];

            if(ny<0 || nx<0 || ny >= H || nx >= W) continue;

            if(map[ny][nx] == 0 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                map[ny][nx] = AIR;
                makeAir(ny, nx);
            }

        }
    }

    public static void findMelt(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int ny = r+dy[i];
            int nx = c+dx[i];

            if(ny<0 || nx<0 || ny >= H || nx >= W) continue;


            if(map[ny][nx] == CHEESE && !visited[ny][nx]) {
               visited[ny][nx] = true;
                meltCheese.add(new int[] {ny, nx});
            }

            if(map[ny][nx] == AIR && !visited[ny][nx]) {
                visited[ny][nx] = true;
                findMelt(ny, nx);
            }

        }
    }

}
