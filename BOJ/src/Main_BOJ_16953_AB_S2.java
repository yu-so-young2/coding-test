import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16953_AB_S2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int des = Integer.parseInt(st.nextToken());

        int step = 1;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[1000000002];
        boolean isFind = false;
        visited[src] = true;
        q.add(src);

        bfs:
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
//                System.out.println("cur is "+cur);
                if(cur == des) {
                    isFind = true;
                    break bfs;
                }


                    if(cur <= des/2 && cur*2 < visited.length && !visited[cur*2]) {
                        visited[cur*2] = true;
                        q.add(cur*2);
                    }
                    if(cur <= des/10 && cur*10+1 < visited.length && !visited[cur*10+1]) {
                        visited[cur*10+1] = true;
                        q.add(cur*10+1);
                    }

            }
            step++;
        }
        step = isFind? step:-1;
        System.out.println(step);
    }
}
