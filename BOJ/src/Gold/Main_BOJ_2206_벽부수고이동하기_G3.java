package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 *  - 모든 칸에서 상하좌우 이동
 *      - 벽이 없으면 이동
 *      - 벽이 있으면 + 현재 벽을 안 쓴 상태면 부수고 이동
 *      - 이동했을 때 더 적은 수로 갈 수 있는 경우에만 이동
 *          - 해당 조건으로 인해 무한루프는 돌지 않을 것이다 라고 추측..!!
 *  - (N, M) 도착 시 종료
 *  - 이동 구현은 어떻게? DFS / BFS? BFS로 큐 사용하자
 *  - (주의) 벽 안 쓰고 돌아와야만 경로를 찾을 수 있는 경우(벽 쓰고 온 것보다 벽 안 쓰고 길게 온 것이 오히려 정답인 경우)
 *      -> dist[][] 를 2개로 나누기
 *      -> 벽 뚫고 온 경우와 벽 안 뚫고 온 경우를 모두 탐색
 *
 * 2. 시간복잡도
 *
 * 3. 자료구조
 *   - int[][] map, int[][] dist
 *   - 현재 경로에 대한 정보 : 현재 칸 위치(r, c), 현재 벽 부쉈는지 여부, 현재 경로 길이
 *   - 큐(BFS 구현)
 */
public class Main_BOJ_2206_벽부수고이동하기_G3 {
    static int N, M, map[][], distNormal[][], distBreak[][];
    static Queue<Path> queue;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Path {
        int r, c, len;
        boolean canBreakWall;

        public Path(int r, int c, int len, boolean canBreakWall) {
            this.r = r;
            this.c = c;
            this.len = len;
            this.canBreakWall = canBreakWall;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵의 크기
        M = Integer.parseInt(st.nextToken()); // 맵의 크기
        map = new int[N][M]; // 맵의 정보
        distNormal = new int[N][M]; // 벽을 뚫지 않고 해당 칸까지 가는 데 필요한 최소 칸의 개수
        distBreak = new int[N][M]; // 벽을 뚫고 해당 칸까지 가는 데 필요한 최소 칸의 개수

        // 맵 정보 입력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j)-'0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                distNormal[i][j] = Integer.MAX_VALUE; // 무한으로 초기화
                distBreak[i][j] = Integer.MAX_VALUE; // 무한으로 초기화
            }
        }
        distNormal[0][0] = 1;
        distBreak[0][0] = 1;

        queue = new LinkedList<>();
        queue.add(new Path(0,0,1,true));

        while(!queue.isEmpty()) {
            Path cur = queue.poll();

            // 현재 경로가 도착지점에 왔다면 이 경로는 탐색 중단
            if(cur.r == N-1 && cur.c == M-1) continue;

            for (int k = 0; k < 4; k++) {
                int ny = dy[k]+cur.r; // 가야 할 곳
                int nx = dx[k]+cur.c; // 가야 할 곳

                // OutOfBoundsException
                if(ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                int curLen = cur.len + 1;

                // 벽이 아니라면
                if(map[ny][nx] == 0) {
                    if (cur.canBreakWall) { // 벽을 부순 적이 없음 -> 부수지 않은 경로로 계속 탐색
                        if (distNormal[ny][nx] > curLen) {
                            distNormal[ny][nx] = curLen;
                            queue.add(new Path(ny, nx, curLen, true));
                        }
                    } else { // 벽을 부순 적이 있음 -> 벽을 부순 경로로 계속 탐색
                        if(distBreak[ny][nx] > curLen) {
                            distBreak[ny][nx] = curLen;
                            queue.add(new Path(ny, nx, curLen, false));
                        }
                    }
                } else { // 벽이라면
                    if(cur.canBreakWall) { // 벽을 부순 적이 없는 경우에만 -> 뚫고 벽 부순 경로로 계속 탐색
                        // 이동 시 더 적은 경로로 갈 수 있다면 벽 부수고 이동(큐에 넣기)
                        if(distBreak[ny][nx] > curLen) {
                            distBreak[ny][nx] = curLen;
                            queue.add(new Path(ny, nx, curLen, false));
                        }
                    }
                }
            }
        }


        // 결과 출력
        int ans = Math.min(distNormal[N-1][M-1], distBreak[N-1][M-1]);
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
}
