package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_3190_뱀_G4 {
    public static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, K, L, map[][], headDir, time;
    static List<Node> snake;
    static HashMap<Integer, Integer> path;
    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 맵의 크기
        map = new int[N][N]; // 맵 생성
        K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            // 사과 저장
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            map[r][c] = 1; // 사과 = 1
        }
        L = Integer.parseInt(br.readLine()); // 경로 개수
        path = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); // 경로 초
            String dir = st.nextToken(); // 경로 초
            path.put(t, dir.equals("L")?-1:1);
        }
        // 뱀 위치 저장
        map[0][0] = 2;
        headDir = 0;
        snake = new LinkedList<>();
        snake.add(new Node(0,0)); // 머리(이자 꼬리) 추가


        time = 1;
        while(true) {
            // 1. 머리 늘리기 : 해당 방향으로 갈 수 있는지 확인(벽이거나 몸이면 종료, 사과면 먹기, 빈칸이면 꼬리 삭제)

            // 1) 늘릴 칸 ny, nx 구하기
            // 현재 머리의 위치에서 해당 방향으로 1칸 간 위치
            int ny = snake.get(0).r+dy[headDir];
            int nx = snake.get(0).c+dx[headDir];
            // 벽이거나 몸이면 종료
            if(ny<0 || nx<0 || ny>=N || nx>=N || map[ny][nx]==2) break;
            // 2) 사과 먹기
            // 사과가 있는지 없는지 확인
            // 사과가 없다면 꼬리 삭제
            if(map[ny][nx]==0) {
                // 뱀에서 삭제
                Node tale = snake.get(snake.size()-1);
                snake.remove(tale);
                // 맵에서 삭제
                map[tale.r][tale.c] = 0;

            }
            // 해당 칸을 머리로 추가
            map[ny][nx] = 2; // 맵에 추가
            snake.add(0, new Node(ny, nx)); // 뱀에 추가

            // 2. 방향 돌리기
            if(path.containsKey(time)) {
                int dir = path.get(time);
                headDir += dir;
                if(headDir==-1) headDir = 3;
                if(headDir==4) headDir = 0;
            }

            // 3. 시간 증가
            time++;
        }
        System.out.println(time);
    }
}
