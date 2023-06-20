package Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_23289_온풍기안녕_P5 {
    public static class Node {
        int r, c, dir;
        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C, K, W, temp[][], choco;
    static List<Node> machineList, searchList;
    static boolean check, walls[][][], visited[][];
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 온풍기, 조사 범위 입력
        machineList = new ArrayList<>();
        searchList = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int input = Integer.parseInt(st.nextToken());
                // 빈칸이면 다음으로 넘어가기
                if(input == 0) continue;

                if(input == 5) { // 조사 범위
                    searchList.add(new Node(i, j));
                } else { // 온풍기
                    machineList.add(new Node(i, j, input));
                }
            }
        }

        // 벽 입력
        W = Integer.parseInt(br.readLine());
        walls = new boolean[R][C][5]; // 각 칸에서 해당 방향으로 벽이 있는지 확인
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());

            if(dir == 0) { // 위
                walls[r][c][UP] = true;
                walls[r-1][c][DOWN] = true;
            } else { // 오른쪽
                walls[r][c][RIGHT] = true;
                walls[r][c+1][LEFT] = true;
            }
        }

        // 온풍기 작동 실시
        choco = 0;
        check = false;
        temp = new int[R][C];
        while(!check) {
            // 1. 모든 온풍기에서 바람 나옴
            operateMachine();

            // 2. 온도 조절
            controlTemperature();

            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            decreaseMargin();

            // 4. 초콜릿 먹기
            choco++;
            if(choco > 100) break; // 100번 넘어가면 101 출력

            // 5. 칸의 모든 온도가 K 이상이라면 check = true
            checkTerminate();
        }

        // 종료
        System.out.print(choco);
    }

    private static void operateMachine() {
        // 모든 온풍기에 대하여
        for (int i = 0; i < machineList.size(); i++) {
            Node machine = machineList.get(i);

            // 온풍기의 방향 칸으로 바람 전송 (재귀 함수)
            // 특정 칸에서 t만큼의 온도를 dir 방향으로 전송하고 싶다
            visited = new boolean[R][C];
            visited[machine.r][machine.c] = true;
            windBlow(machine.r, machine.c, machine.dir, 5);
        }
    }

    static int[] dy = {0, 0, 0, -1, 1, -1, 1, 1, -1};
    static int[] dx = {0, 1, -1, 0, 0, 1, 1, -1, -1};
    static int[][] dirs = {{0,0,0},{1, 5, 6},{2, 7, 8},{3, 5, 8},{4, 6, 7}};

    private static void windBlow(int r, int c, int machineDir, int t) {
        // t == 0 인 경우 재귀 X
        if(t == 0) return;

        // 방향으로 온도 상승 및 바람 재귀 호출
        // 이때 이번 온풍기에서 방문한 적 있는 칸이라면 호출 X
        for(int k = 0; k < 3; k++) {
            int dir = dirs[machineDir][k];
            // t == 5 인 경우면 해당 방향으로 1칸만
            if(t==5 && dir != machineDir) continue;

            int ny = r+dy[dir];
            int nx = c+dx[dir];

            // IndexOutOfBounds
            if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;

            // 벽이 없고 또한 방문하지 않은 칸에 대하여
            if(possible(r, c, dir, machineDir) && !visited[ny][nx]) {
                temp[ny][nx] += t;
                visited[ny][nx] = true;
                windBlow(ny, nx, machineDir, t-1);
            }
        }



    }

    private static void controlTemperature() {
        int[][] diffs = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        int[] dy = {0, 0, 0, -1, 1}; // X 우 하 좌 상
        int[] dx = {0, 1, -1, 0, 0};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 상하좌우에 대하여
                for (int k = 1; k < 5; k++) {
                    int ny = i+dy[k];
                    int nx = j+dx[k];

                    // IndexOutOfBounds
                    if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;

                    // 벽이 없고 또한 방문하지 않은 칸에 대하여
                    if(possible(i, j, k, 0) && !visited[ny][nx]) {
                        // 온도차이/4
                        int diff = Math.abs(temp[i][j]-temp[ny][nx]) / 4;
                        // diffs 기록
                        if(temp[i][j] > temp[ny][nx]) {
                            diffs[i][j] -= diff;
                            diffs[ny][nx] += diff;
                        } else {
                            diffs[i][j] += diff;
                            diffs[ny][nx] -= diff;
                        }
                    }
                }

                // 방문 처리
                visited[i][j] = true;
            }
        }

        // 차이 계산
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] += diffs[i][j];
            }
        }
    }

    private static void decreaseMargin() {
        // 0행, R-1행, 0열, C-1열의 칸 온도 1도씩 감소(1도 이상인 곳만)

        // 1행, R-1행
        for(int c = 0; c < C; c++) {
            if(temp[0][c] > 0) temp[0][c]--;
            if(temp[R-1][c] > 0) temp[R-1][c]--;
        }
        // 0열, C-1열 (1행, R-1행 제외)
        for(int r = 1; r < R-1; r++) {
            if(temp[r][0] > 0) temp[r][0]--;
            if(temp[r][C-1] > 0) temp[r][C-1]--;
        }
    }

    private static void checkTerminate() {
        // 조사 범위 중 한 칸이라도 온도가 K 미만이면 false
        check = true;
        for (int i = 0; i < searchList.size(); i++) {
            Node node = searchList.get(i);
            if(temp[node.r][node.c] < K) {
                check = false;
                return;
            }
        }
    }

    private static boolean possible(int i, int j, int dir, int machineDir) {
        // 각 칸의 해당 방향에 벽이 있으면 false 리턴
        if(dir <= 4) {
            return !walls[i][j][dir];
        } else {
            switch (dir) { // 하나라도 벽이 있으면 false
                case 5:
                    if(machineDir == UP)
                        return !(walls[i][j][RIGHT] || walls[i][j+1][UP]);
                    else if(machineDir == RIGHT)
                        return !(walls[i][j][UP] || walls[i-1][j][RIGHT]);
                case 6:
                    if(machineDir == RIGHT)
                        return !(walls[i][j][DOWN] || walls[i+1][j][RIGHT]);
                    else if(machineDir == DOWN)
                        return !(walls[i][j][RIGHT] || walls[i][j+1][DOWN]);
                case 7:
                    if(machineDir == LEFT)
                        return !(walls[i][j][DOWN] || walls[i+1][j][LEFT]);
                    else if(machineDir == DOWN)
                        return !(walls[i][j][LEFT] || walls[i][j-1][DOWN]);
                case 8:
                    if(machineDir == LEFT)
                        return !(walls[i][j][UP] || walls[i-1][j][LEFT]);
                    else if(machineDir == UP)
                        return !(walls[i][j][LEFT] || walls[i][j-1][UP]);
            }
        }
        return false;
    }

}
