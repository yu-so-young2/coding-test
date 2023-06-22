package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14499_주사위굴리기_G4 {
    static int N, M, x, y, K, map[][], up, bottom, front, back, left, right, dice[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[7];
        up = 1;
        bottom = 6;
        front = 5;
        back = 2;
        left = 4;
        right = 3;

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()); // 주사위 굴리는 방향
            sb.append(rollDice(dir));
        }
        System.out.print(sb);
    }

    static int[] dy = {0,0,0,-1,1};
    static int[] dx = {0,1,-1,0,0};

    private static String rollDice(int dir) {
        // 해당 방향으로 이동
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 바깥으로 이동 -> 해당 명령 무시
        if(ny < 0 || nx < 0 || ny >= N || nx >= M) return "";

        y = ny;
        x = nx;
        // 회전하여 Up, Front 변경
        int temp = 0;
        switch (dir) {
            case 1:

                temp = up;
                up = left;
                left = bottom;
                bottom = right;
                right = temp;
                break;
            case 2:
                temp = up;
                up = right;
                right = bottom;
                bottom = left;
                left = temp;
                break;
            case 3:
                temp = up;
                up = front;
                front = bottom;
                bottom = back;
                back = temp;
                break;
            case 4:
                temp = up;
                up = back;
                back = bottom;
                bottom = front;
                front = temp;
                break;
        }

        // 맵의 칸 확인하여 0아니면 Bottom 으로 복사
        if(map[ny][nx] != 0) {
            dice[bottom] = map[ny][nx];
            map[ny][nx] = 0;
        } else {
            map[ny][nx] = dice[bottom];
        }

        return dice[up]+"\n";
    }
}
