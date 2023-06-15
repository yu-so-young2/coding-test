package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16928_뱀과사다리게임_G5 {
    static int[] ladder, snake; // 사다리, 뱀 연결 저장
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //input
        ladder = new int[101]; // 1~100 저장
        snake = new int[101]; // 1~100 저장
        st = new StringTokenizer(br.readLine()); // N M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){ // 사다리 저장
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            ladder[src] = dest;
        }
        for(int i = 0; i < M; i++){ // 뱀 저장
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            snake[src] = dest;
        }


        //bfs
        visited = new boolean[101];
        Queue<Integer> q = new LinkedList<>();
        int step = 0;
        q.add(1); // 시작점 1
        visited[1] = true;

        bfs:
        while(!q.isEmpty()){
            int qSize = q.size(); // q 단계 저장
            for (int i = 0; i < qSize; i++) {
                int cur = q.poll();
//                System.out.println("I am "+cur);
                // cur == 100 도착이라면 탐색 종료
                if(cur==100) {
                    break bfs;
                }

                for (int j = 1; j <= 6; j++) { // 1~6의 주사위
                    if(cur+j <= 100 && !visited[cur+j]) { // 100 초과하지 않는 전진만 가능
                        // 뱀이나 사다리가 있다면 그리로 곧장
                        // 주의) 뱀, 사다리는 곧장 가야 하므로 방문 여부를 확인하면 안됨
                        if(ladder[cur+j] != 0) {
                            q.add(ladder[cur+j]);
//                            System.out.println("go to "+ ladder[cur+j]);
                            visited[ladder[cur+j]] = true;
                        } else if(snake[cur+j] != 0) {
                            q.add(snake[cur+j]);
//                            System.out.println("go to "+ snake[cur+j]);
                            visited[snake[cur+j]] = true;
                        } else { // 아니라면 주사위 굴리기
                            q.add(cur+j);
//                            System.out.println("go to "+ (cur+j));
                            visited[cur+j] = true;
                        }

                    }
                }
            }
            step++; // 사다리, 뱀으로 가는 것은 step 증가 X
//            System.out.println("step++");
        }

        //print
        System.out.println(step);

    }
}