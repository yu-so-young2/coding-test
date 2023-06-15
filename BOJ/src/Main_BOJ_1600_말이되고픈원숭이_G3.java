import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1600_말이되고픈원숭이_G3 {
    static class Node {
        int r, c, level, k;
        public Node(int r, int c, int level, int k) {
            this.r = r;
            this.c = c;
            this.level = level;
            this.k = k;
        }
    }

    static int monky_dy[] = {-1, 1, 0, 0};
    static int monky_dx[] = {0, 0, -1, 1};
    static int horse_dy[] = {-2, -2, 2, 2, -1, 1, -1, 1};
    static int horse_dx[] = {-1, 1, -1, 1, -2, -2, 2, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K, W, H;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int map[][] = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int steps[][][] = new int[H][W][K+1]; //K번 말 움직임을 사용하여 몇번째 움직임으로 도착했는지
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < K+1; k++) {
                    steps[i][j][k] = -1;
                }
            }
        } //-1로 초기화 완료

        //bfs
        Queue<Node> q = new LinkedList<>();
        steps[0][0][0] = 0;
        q.add(new Node(0,0, 0, 0));
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            level++; //현재 노드가 몇 번째 방문인지
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int k = cur.k; //현재 노드에 k가 몇 번 들어가 있는지


                //원숭이 움직임
            monky:    for (int j = 0; j < 4; j++) {
                    int ny = r + monky_dy[j];
                    int nx = c + monky_dx[j];

                    if(ny>=0 && nx >=0 && ny < H && nx < W) {
                        if(ny==0 && nx==0) continue; //시작점으론 돌아가지 않을 것
                        if(map[ny][nx] == 1) continue; //벽으로는 가지 않을 것

                        //나보다 적은 k 횟수로 온 적이 있다면 가지 않을 것
                        for (int l = 0; l < k+1; l++) {
                            if(steps[ny][nx][l] != -1) continue monky;
                        }

                        if(ny == H-1 && nx == W-1) {
                            System.out.println(level);
                            return;
                        }

//                        System.out.println("go to ("+ny+", "+nx+") with "+level+"and k: "+k);
                        steps[ny][nx][k] = level;
                        q.add(new Node(ny, nx, level, k));
                    }
                }

                //말 움직임
                if(k < K) {
                horse:    for (int j = 0; j < 8; j++) {
                        int ny = r + horse_dy[j];
                        int nx = c + horse_dx[j];

                        if(ny>=0 && nx >=0 && ny < H && nx < W) {
                            if(ny==0 && nx==0) continue; //시작점으론 돌아가지 않을 것
                            if(map[ny][nx] == 1) continue; //벽으로는 가지 않을 것
                            //나보다 적은 k 횟수로 온 적이 있다면 가지 않을 것
                            for (int l = 0; l < k+2; l++) {
                                if(steps[ny][nx][l] != -1) continue horse;
                            }

                            if(ny == H-1 && nx == W-1) {
                                System.out.println(level);
                                return;
                            }

//                            System.out.println("go to ("+ny+", "+nx+") with "+level+"and k: "+(k+1));
                            steps[ny][nx][k+1] = level;
                            q.add(new Node(ny, nx, level, k+1));

                        }
                    }
                }
            }
        }

        //print
        int minStep = Integer.MAX_VALUE;
        for (int i = 0; i < K+1; i++) {
            if(steps[H-1][W-1][i] == -1) continue;
            minStep = Math.min(minStep, steps[H-1][W-1][i]);
        }
        minStep = minStep==Integer.MAX_VALUE? -1:minStep;
        System.out.println(minStep);
    }
}