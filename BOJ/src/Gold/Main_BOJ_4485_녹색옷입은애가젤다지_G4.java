package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    다익스트라 알고리즘

 */
public class Main_BOJ_4485_녹색옷입은애가젤다지_G4 {
    static class Edge implements Comparable<Edge> {
        int cost, r, c;

        public Edge(int cost, int r, int c) {
            this.cost = cost;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N==0) break;

            // input
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // dijkstra
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            int[][] dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            boolean[][] visited = new boolean[N][N];

            pq.add(new Edge(map[0][0], 0, 0));
            dp[0][0] = map[0][0];

            while(!pq.isEmpty()) {
                Edge cur = pq.poll(); // 상단의 노드 뽑기
//                System.out.println("cur: "+cur.r+","+cur.c+" and: "+cur.cost);
                visited[cur.r][cur.c] = true;
                for (int i = 0; i < 4; i++) {
                    int ny = cur.r + dy[i];
                    int nx = cur.c + dx[i];
                    if(ny<0 || nx<0 || ny>=N || nx>=N || visited[ny][nx]) continue;
                    pq.add(new Edge(cur.cost+map[ny][nx], ny, nx));

                    if(dp[ny][nx] > cur.cost + map[ny][nx]) {
                        dp[ny][nx] = cur.cost + map[ny][nx];
                    }

                }

//                for (int i = 0; i < N; i++) {
//                    for (int j = 0; j < N; j++) {
//                        System.out.print(dp[i][j]==Integer.MAX_VALUE?'-':dp[i][j]+" ");
//                    }
//                    System.out.println();
//                }
            }

            sb.append("Problem "+tc+": "+dp[N-1][N-1]+"\n");
            tc++;
        }

        System.out.print(sb);

    }

}