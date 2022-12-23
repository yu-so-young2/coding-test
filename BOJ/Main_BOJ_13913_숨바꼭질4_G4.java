import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
BFS 경로 출력해야 할 경우
visited[] => boolean 에서 parent 저장하는 배열로 선언
 */

public class Main_BOJ_13913_숨바꼭질4_G4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());


        Queue<Integer> q = new LinkedList<>();
        int[] from = new int[1000001];
        for (int i = 0; i < 1000001; i++) {
            from[i] = -2;
        }

        int level = 0;
        from[src] = -1;

        q.add(src);


        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                if(cur == dest) {

                    sb.append(level+"\n");

                    Stack<Integer> s = new Stack<>();
                    do {
                        s.add(cur);
                        cur = from[cur];
                    } while(cur != -1);

                    while(!s.isEmpty()) {
                        sb.append(s.pop()+" ");
                    }
                    System.out.println(sb);
                    return;
                }

                if(cur < dest && cur*2 < 100001 && from[cur*2]==-2) {
                    from[cur*2] = cur;
                    q.add(cur*2);
                }
                if(cur-1 >= 0 && from[cur-1]==-2) {
                    from[cur-1] = cur;
                    q.add(cur-1);
                }
                if(cur < dest && cur+1 < 100001 && from[cur+1]==-2) {
                    from[cur+1] = cur;
                    q.add(cur+1);
                }

            }
            level++;
        } //while

    }
}