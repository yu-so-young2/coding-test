import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13460_구슬탈출2_G1 {
    static int N, M;
    static char board[][];
    static int Rx, Ry, Bx, By;
    static boolean possible;
    static Queue<int[][]> q; // Red, Blue 구슬 위치 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);

                // R, B 구슬 위치 저장
                if(board[i][j] == 'B') {
                    By = i; Bx = j;
                    board[i][j] = '.';
                }
                if(board[i][j] == 'R') {
                    Ry = i; Rx = j;
                    board[i][j] = '.';
                }
            }
        }

//        // 확인 프린트
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }


        int step = 1;
        q = new LinkedList<>();
        possible = false;

        int[][] idx = {{Ry, Rx},{By, Bx}};
        q.add(idx);

        bfs:
        while(step <= 10 && !q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[][] cur = q.poll();

//                System.out.println("==== cur ====");
//                for (int y = 0; y < N; y++) {
//                    for (int x = 0; x < M; x++) {
//                        System.out.print(cur[y][x]);
//                    }
//                    System.out.println();
//                }

                // 상하좌우 맵 만들기
                up(cur);
                if(possible) break bfs;
                down(cur);
                if(possible) break bfs;
                left(cur);
                if(possible) break bfs;
                right(cur);
                if(possible) break bfs;
            }

            step++;
        } // while

        System.out.println(step>10?-1:step);
//        System.out.println(possible?1:0);
    }


    private static void up(int[][] cur) {
        // 일단 현재 보드로 시뮬 돌리기
        // 1) 빨간 구슬 뺄 수 있으면 possible=true, return
        // 2) 파란 구슬이 구멍에 빠지면 취소, return
        // 3) 빨강+파란 구슬이 같이 구멍에 빠져도 취소, return
        // 4) 위의 경우 다 아니면 q.add();

        boolean redIn = false, blueIn = false; // 각 구슬이 구멍에 들어갔는지 저장할 변수
        int Ry = cur[0][0];
        int Rx = cur[0][1];
        int By = cur[1][0];
        int Bx = cur[1][1];

        for (int i = 0; i < N; i++) { // 위로 올라가야 하니까 상->하, 좌->우로 탐색
            for (int j = 0; j < M; j++) {
                if(i==Ry && j==Rx) { // Red 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny-1][nx] == '.' && !(ny-1==By && nx==Bx)) || board[ny-1][nx] == 'O'){ // 위가 벽일 때까지
                        if(board[ny-1][nx] == '.') { // 위에가 . 이면 계속 올라가
                            ny--;
                        }
                        else if(board[ny-1][nx] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            ny--;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        redIn = true;
                        Ry = -1;
                        Rx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        Ry = ny;
                        Rx = nx;
                    }
                }

                if(i==By && j==Bx) { // Blue 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny-1][nx] == '.' && !(ny-1==Ry && nx==Rx)) || board[ny-1][nx] == 'O'){ // 위가 벽일 때까지
                        if(board[ny-1][nx] == '.') { // 위에가 . 이면 계속 올라가
                            ny--;
                        }
                        else if(board[ny-1][nx] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            ny--;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        blueIn = true;
                        By = -1;
                        Bx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        By = ny;
                        Bx = nx;
                    }
                }

            }
        } //for

        if(redIn && !blueIn) possible = true; // 빨강만 구멍에 들어가면 성공
        else if(!redIn && !blueIn){ // 둘 다 구멍에 안들어가면 bfs 진행
            int[][] newIdx = {{Ry,Rx},{By,Bx}};
            q.add(newIdx);
        }

    }

    private static void down(int[][] cur) {
        boolean redIn = false, blueIn = false; // 각 구슬이 구멍에 들어갔는지 저장할 변수
        int Ry = cur[0][0];
        int Rx = cur[0][1];
        int By = cur[1][0];
        int Bx = cur[1][1];

        for (int i = N-1; i >= 0; i--) { // 위로 올라가야 하니까 상->하, 좌->우로 탐색
            for (int j = 0; j < M; j++) {
                if(i==Ry && j==Rx) { // Red 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny+1][nx] == '.' && !(ny+1==By && nx==Bx)) || board[ny+1][nx] == 'O'){ // 위가 벽일 때까지
                        if(board[ny+1][nx] == '.') { // 위에가 . 이면 계속 올라가
                            ny++;
                        }
                        else if(board[ny+1][nx] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            ny++;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        redIn = true;
                        Ry = -1;
                        Rx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        Ry = ny;
                        Rx = nx;
                    }
                }

                if(i==By && j==Bx) { // Blue 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny+1][nx] == '.' && !(ny+1==Ry && nx==Rx)) || board[ny+1][nx] == 'O'){ // 위가 벽일 때까지
                        if(board[ny+1][nx] == '.') { // 위에가 . 이면 계속 올라가
                            ny++;
                        }
                        else if(board[ny+1][nx] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            ny++;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        blueIn = true;
                        By = -1;
                        Bx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        By = ny;
                        Bx = nx;
                    }
                }

            }
        } //for

        if(redIn && !blueIn) possible = true; // 빨강만 구멍에 들어가면 성공
        else if(!redIn && !blueIn){ // 둘 다 구멍에 안들어가면 bfs 진행
            int[][] newIdx = {{Ry,Rx},{By,Bx}};
            q.add(newIdx);
        }
    }

    private static void left(int[][] cur) {
        boolean redIn = false, blueIn = false; // 각 구슬이 구멍에 들어갔는지 저장할 변수
        int Ry = cur[0][0];
        int Rx = cur[0][1];
        int By = cur[1][0];
        int Bx = cur[1][1];

        for (int i = 0; i < N; i++) { // 위로 올라가야 하니까 상->하, 좌->우로 탐색
            for (int j = 0; j < M; j++) {
                if(i==Ry && j==Rx) { // Red 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny][nx-1] == '.' && !(ny==By && nx-1==Bx)) || board[ny][nx-1] == 'O'){ // 위가 벽일 때까지
                        if(board[ny][nx-1] == '.') { // 위에가 . 이면 계속 올라가
                            nx--;
                        }
                        else if(board[ny][nx-1] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            nx--;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        redIn = true;
                        Ry = -1;
                        Rx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        Ry = ny;
                        Rx = nx;
                    }
                }

                if(i==By && j==Bx) { // Blue 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny][nx-1] == '.' && !(ny==Ry && nx-1==Rx)) || board[ny][nx-1] == 'O'){ // 위가 벽일 때까지
                        if(board[ny][nx-1] == '.') { // 위에가 . 이면 계속 올라가
                            nx--;
                        }
                        else if(board[ny][nx-1] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            nx--;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        blueIn = true;
                        By = -1;
                        Bx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        By = ny;
                        Bx = nx;
                    }
                }

            }
        } //for

        if(redIn && !blueIn) possible = true; // 빨강만 구멍에 들어가면 성공
        else if(!redIn && !blueIn){ // 둘 다 구멍에 안들어가면 bfs 진행
            int[][] newIdx = {{Ry,Rx},{By,Bx}};
            q.add(newIdx);
        }
    }
    private static void right(int[][] cur) {
        boolean redIn = false, blueIn = false; // 각 구슬이 구멍에 들어갔는지 저장할 변수
        int Ry = cur[0][0];
        int Rx = cur[0][1];
        int By = cur[1][0];
        int Bx = cur[1][1];

        for (int i = 0; i < N; i++) { // 위로 올라가야 하니까 상->하, 좌->우로 탐색
            for (int j = M-1; j >= 0; j--) {
                if(i==Ry && j==Rx) { // Red 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny][nx+1] == '.' && !(ny==By && nx+1==Bx)) || board[ny][nx+1] == 'O'){ // 위가 벽일 때까지
                        if(board[ny][nx+1] == '.') { // 위에가 . 이면 계속 올라가
                            nx++;
                        }
                        else if(board[ny][nx+1] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            nx++;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        redIn = true;
                        Ry = -1;
                        Rx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        Ry = ny;
                        Rx = nx;
                    }
                }

                if(i==By && j==Bx) { // Blue 구슬이라면
                    int ny = i;
                    int nx = j;
                    while((board[ny][nx+1] == '.' && !(ny==Ry && nx+1==Rx)) || board[ny][nx+1] == 'O'){ // 위가 벽일 때까지
                        if(board[ny][nx+1] == '.') { // 위에가 . 이면 계속 올라가
                            nx++;
                        }
                        else if(board[ny][nx+1] == 'O') { // 위에가 0 이면 올라간 후 멈춰
                            nx++;
                            break;
                        }
                    }

                    // ny nx: 구슬이 있을 위치
                    if(board[ny][nx]=='O') { // 구멍에 들어간다면
                        blueIn = true;
                        By = -1;
                        Bx = -1;
                    } else if(board[ny][nx]=='.') { // 빈칸으로 간다면
                        By = ny;
                        Bx = nx;
                    }
                }

            }
        } //for

        if(redIn && !blueIn) possible = true; // 빨강만 구멍에 들어가면 성공
        else if(!redIn && !blueIn){ // 둘 다 구멍에 안들어가면 bfs 진행
            int[][] newIdx = {{Ry,Rx},{By,Bx}};
            q.add(newIdx);
        }
    }
}