package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12100_2048_Easy_G2 {
    static int N, maxBlock;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        // 확인용 print
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        maxBlock = 0;

//        int[][] test = test(RIGHT, map);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(test[i][j]+" ");
//            }
//            System.out.println();
//        }

        simulation(0, map);

        System.out.println(maxBlock);

    }

    private static void simulation(int level, int[][] map) {
        if(level == 5) {
            // maxBlock 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxBlock = Math.max(maxBlock, map[i][j]);
                }
            }
            return;
        }

        // 상하좌우 시뮬레이션
        simulation(level+1, test(UP, map));
        simulation(level+1, test(DOWN, map));
        simulation(level+1, test(LEFT, map));
        simulation(level+1, test(RIGHT, map));

    }

    private static int[][] test(int dir, int[][] oldMap) {
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = oldMap[i][j];
            }
        }

        boolean[][] isMerged = new boolean[N][N]; // 해당 칸이 이미 한 번 합쳐진 곳인지 확인

        if(dir == UP){
            for (int j = 0; j < N; j++) {
                for (int i = 1; i < N; i++) {
                    // 올라갈 수 있을 때까지 올라가기
                    int k = i;
                    while(k > 0) {
                        if(k > 0 && map[k-1][j] != 0) { // 아주 끝에 도달하거나 위에 숫자 있을 때까지
                            break;
                        }
                        k--;
                    }

                    // 위의 숫자랑 합칠 수 있으면 합치기
                    if(k > 0 && map[k-1][j] == map[i][j] && !isMerged[k-1][j]) {
                        isMerged[k-1][j] = true;
                        map[k-1][j] *= 2;
                        map[i][j] = 0;
                    }


                    // 합치지 않고 그냥 이동했다면
                    if(k != i) {
                        // 원래 있던 자리 0으로
                        map[k][j] = map[i][j];
                        map[i][j] = 0;
                    }
                }
            }
        } else if(dir == DOWN){
            for (int j = 0; j < N; j++) {
                for (int i = N-1; i >= 0; i--) {
                    // 내려갈 수 있을 때까지 내려가기
                    int k = i;
                    while(k < N-1) {
                        if(k < N-1 && map[k+1][j] != 0) { // 아주 끝에 도달하거나 위에 숫자 있을 때까지
                            break;
                        }
                        k++;
                    }

                    // 위의 숫자랑 합칠 수 있으면 합치기
                    if(k < N-1 && map[k+1][j] == map[i][j] && !isMerged[k+1][j]) {
                        isMerged[k+1][j] = true;
                        map[k+1][j] *= 2;
                        map[i][j] = 0;
                    }


                    // 합치지 않고 그냥 이동했다면
                    if(k != i) {
                        // 원래 있던 자리 0으로
                        map[k][j] = map[i][j];
                        map[i][j] = 0;
                    }
                }
            }
        } else if(dir == LEFT){
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    // 올라갈 수 있을 때까지 올라가기
                    int k = j;
                    while(k > 0) {
                        if(k > 0 && map[i][k-1] != 0) { // 아주 끝에 도달하거나 위에 숫자 있을 때까지
                            break;
                        }
                        k--;
                    }

                    // 위의 숫자랑 합칠 수 있으면 합치기
                    if(k > 0 && map[i][k-1] == map[i][j] && !isMerged[i][k-1]) {
                        isMerged[i][k-1] = true;
                        map[i][k-1] *= 2;
                        map[i][j] = 0;
                    }


                    // 합치지 않고 그냥 이동했다면
                    if(k != j) {
                        // 원래 있던 자리 0으로
                        map[i][k] = map[i][j];
                        map[i][j] = 0;
                    }
                }
            }
        } else if(dir == RIGHT){
            for (int i = 0; i < N; i++) {
                for (int j = N-1; j >= 0; j--) {
                    // 내려갈 수 있을 때까지 내려가기
                    int k = j;
                    while(k < N-1) {
                        if(k < N-1 && map[i][k+1] != 0) { // 아주 끝에 도달하거나 위에 숫자 있을 때까지
                            break;
                        }
                        k++;
                    }

                    // 위의 숫자랑 합칠 수 있으면 합치기
                    if(k < N-1 && map[i][k+1] == map[i][j] && !isMerged[i][k+1]) {
                        isMerged[i][k+1] = true;
                        map[i][k+1] *= 2;
                        map[i][j] = 0;
                    }


                    // 합치지 않고 그냥 이동했다면
                    if(k != j) {
                        // 원래 있던 자리 0으로
                        map[i][k] = map[i][j];
                        map[i][j] = 0;
                    }
                }
            }
        }


//        System.out.println("====== old map =====");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(oldMap[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("====== "+dir+" ======");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        return map;
    }

}

/*
10
16 16 8 32 32 0 0 8 8 8
16 0 0 0 0 8 0 0 0 16
0 0 0 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */