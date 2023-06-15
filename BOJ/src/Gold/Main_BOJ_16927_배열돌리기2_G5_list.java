package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_16927_배열돌리기2_G5_list {
    static final int toRight = 1;
    static final int toDown = 2;
    static final int toLeft = 3;
    static final int toUp = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine()); // N M R
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());


        // matrix
        int[][] originMap = new int[N][M]; // 회전하기 전 배열
        for (int i = 0; i < N; i++) { // row
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) { // column
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // shell list 만들기
        int shell = Math.min(N, M) / 2;
        List<List<Integer>> shells = new ArrayList<>();
        for (int s = 0; s < shell; s++) {
            shells.add(new ArrayList<>()); // 새로운 shell 생성
            List<Integer> thisShell = shells.get(s);

            // 다시 처음으로 돌아올 때까지 shell 채워넣기
            int i = s;
            int j = s;
            int dir = toRight;
            do {
                thisShell.add(originMap[i][j]);

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
                    }
                }

            } while(!(i==s && j==s)); // 다시 shell의 맨 처음으로 돌아올 때까지

        }

//        // 확인용 print
//        for (int i = 0; i < shells.size(); i++) {
//            System.out.println("shell "+i);
//            for (int j = 0; j < shells.get(i).size(); j++) {
//                System.out.print(shells.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }

        int[][] map = new int[N][M];

        for (int s = 0; s < shell; s++) {
            List<Integer> thisShell = shells.get(s);
            int idx = R % thisShell.size();

            // 다시 처음으로 돌아올 때까지 shell 채워넣기
            int i = s;
            int j = s;
            int dir = toRight;
            do {
                map[i][j] = thisShell.get(idx);
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
                    }
                }
                idx = idx < thisShell.size()-1? idx+1:0;

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
}