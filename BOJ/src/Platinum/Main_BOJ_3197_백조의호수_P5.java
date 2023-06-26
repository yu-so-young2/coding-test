package Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3197_백조의호수_P5 {
    static class Ice {
        int r, c;

        public Ice(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C, map[][], day, swan[][];
    static boolean[][] visited, swanVisited;
    static boolean find;
    static Queue<Ice> iceQueue;
    static Queue<Ice> pathQueue;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        iceQueue = new LinkedList<>();
        pathQueue = new LinkedList<>();
        swan = new int[2][2];

        // map 정보 입력
        int idx = 0;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                map[i][j] = c=='.'?0:c=='X'?1:2; // 0:물, 1:얼음, 2:백조
                if(map[i][j]==2) {
                    swan[idx][0] = i;
                    swan[idx][1] = j;
                    idx++;
                }
            }
        }


        // 최초 녹일 얼음 위치 찾기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]==1) {
                    boolean find = false;
                    for (int k = 0; k < 4; k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||ny>=R||nx>=C) continue;
                        if(map[ny][nx]!=1) find = true;
                    }
                    if(find) iceQueue.add(new Ice(i, j));
                }
            }
        }


        day = 0;
        swanVisited = new boolean[R][C];
        pathQueue.add(new Ice(swan[0][0], swan[0][1]));
        swanVisited[swan[0][0]][swan[0][1]] = true;


        while(!swanCanMeet()) { // 백조 만날 수 있는지 확인
            // 얼음 녹이기
            visited = new boolean[R][C];
                int size = iceQueue.size();
//                System.out.println("size = "+size);

                for (int i = 0; i < size; i++) {
                    Ice ice = iceQueue.poll();
                    if(map[ice.r][ice.c]==1) map[ice.r][ice.c] = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = ice.r+dy[k];
                        int nx = ice.c+dx[k];
                        if(ny<0||nx<0||ny>=R||nx>=C||visited[ny][nx]) continue;
                        if(map[ny][nx]==1) {
                            visited[ny][nx] = true;
                            iceQueue.add(new Ice(ny, nx));
                        }
                    }
            }

//            System.out.println("--------");
//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(map[i][j]);
//                }
//                System.out.println();
//            }

            day++;
        }

        System.out.println(day);
    }


    private static boolean swanCanMeet() {
        Queue<Ice> nextQueue = new LinkedList<>();
        while(!pathQueue.isEmpty()) {
            Ice ice = pathQueue.poll();

            for (int k = 0; k < 4; k++) {
                int ny = ice.r + dy[k];
                int nx = ice.c + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || swanVisited[ny][nx]) continue;
                if (ny == swan[1][0] && nx == swan[1][1]) {
                    return true;
                }

                swanVisited[ny][nx] = true;
                if (map[ny][nx] == 1) {
                    nextQueue.add(new Ice(ny, nx));
                } else {
                    pathQueue.add(new Ice(ny, nx));
                }
            }
        }

        pathQueue = nextQueue;
        return false;

    }
}
