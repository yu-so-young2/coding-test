import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_16927_배열돌리기2_G5 {
    static final int toRight = 1;
    static final int toDown = 2;
    static final int toLeft = 3;
    static final int toUp = 4;

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine()); // N M R
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // matrix input 받기
        int[][] originMap = new int[N][M]; // 회전하기 전 배열
        for (int i = 0; i < N; i++) { // row
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) { // column
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int shell = Math.min(N, M) / 2; // 총 회전 껍질 수
        int[][] map = new int[N][M]; // 회전 결과 저장할 배열

        // 배열 돌리기
        for (int s = 0; s < shell; s++) { // 각 껍질 채우기
            int idx = R % (2*((N-2*s)+(M-2*s))-4); // 해당 껍질에서 가야 할 인덱스(각 껍질의 크기에 따라 달라짐)
            int oi = s, oj = s; // origin Map 포인터(시작은 해당 껍질 시작점)
            int oDir = toRight;

            for (int k = 0; k < idx; k++) { // origin map 포인터 이동
                int[] proceedOrigin = proceed(originMap, s, oi, oj, oDir);
                oi = proceedOrigin[0];
                oj = proceedOrigin[1];
                oDir = proceedOrigin[2];
            }

            // 다시 처음으로 돌아올 때까지 shell 채워넣기
            int i = s;
            int j = s;
            int dir = toRight;
            do {
                map[i][j] = originMap[oi][oj];

                //방향 전진
                int[] proceed = proceed(originMap, s, i, j, dir);
                i = proceed[0];
                j = proceed[1];
                dir = proceed[2];

                int[] proceedOrigin = proceed(originMap, s, oi, oj, oDir);
                oi = proceedOrigin[0];
                oj = proceedOrigin[1];
                oDir = proceedOrigin[2];
            } while(!(i==s && j==s)); // 다시 shell의 맨 처음으로 돌아올 때까지

        }

        // matrix print
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[] proceed(int[][] map, int s, int i, int j, int dir) {
        //방향 전진
        if(dir == toRight) { // 오른쪽으로 오고 있었을때
            if(j < M-1-s) { // 아직 오른쪽 끝에 다다르지 않음
                j++; // 계속 오른쪽 전진
            } else { // 오른쪽 끝에 도착
                i++;
                dir = toDown;
            }
        } else if(dir == toDown) { // 아래로 오고 있었을 때
            if(i < N-1-s) {
                i++;
            } else {
                j--;
                dir = toLeft;
            }
        } else if(dir == toLeft) { // 왼쪽으로 가고 있었을 때
            if(j > s) {
                j--;
            } else {
                i--;
                dir = toUp;
            }
        } else if(dir == toUp) {
            if(i > s) {
                i--;
            } else {
                j++;
                dir = toRight;
            }
        }
        return new int[]{i, j, dir};
    }
}