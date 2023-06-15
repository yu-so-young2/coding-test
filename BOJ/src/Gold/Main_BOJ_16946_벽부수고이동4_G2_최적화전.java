package Gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16946_벽부수고이동4_G2_최적화전 {
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, map[][], ans[][], cnt, num;
    static boolean visited[][];
    static int group[];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];

        // input O(N*M)
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (str.charAt(j)-'0'==1?-1:0);
            }
        }

//        // 확인용 프린트
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("============");


        // bfs 시행하며 빈 구역 나누기
        Queue<Node> q = new LinkedList<>();
        group = new int[N*M+1];
        cnt = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0 && !visited[i][j]){ // 빈공간을 만나면
                    cnt++;

                    num = 1;
                    visited[i][j] = true;
                    q.add(new Node(i, j));

                    while(!q.isEmpty()) {
                        Node cur = q.poll();
                        int y = cur.r;
                        int x = cur.c;
                        map[y][x] = cnt;
                        for (int k = 0; k < 4; k++) {
                            int ny = y+dy[k];
                            int nx = x+dx[k];
                            if(ny<0||nx<0||ny>=N||nx>=M||visited[ny][nx]||map[ny][nx]!=0) continue;

                            num++;
                            visited[ny][nx] = true;
                            q.add(new Node(ny, nx));
                        }

                    } // while

                    group[cnt] = num;

                } // if
            }
        } //for


//        // 확인용 프린트
//        for (int i = 1; i <= cnt; i++) {
//            System.out.println(group[i]);
//        }
//        System.out.println("cnt: "+cnt);
//
//        // 확인용 프린트
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==-1) { // 벽이라면
                    // 상하좌우 탐색하여 뭐뭐있는지 확인
                    // 있는 아이들의 숫자 더하기
                    int sum = 1;

                    boolean[] find = new boolean[cnt+1];
                    for (int k = 0; k < 4; k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||ny>=N||nx>=M||map[ny][nx]==-1||find[map[ny][nx]]) continue;

                        sum += group[map[ny][nx]];
                        find[map[ny][nx]] = true;
                    }


//                    for (int k = 1; k <= cnt; k++) {
//                        if(find[k]){
//                            sum += group[k];
//                        }
//                    }
                    ans[i][j] = sum%10;

                }
            }
        } // for

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        //System.out.print(sb);
    }
}