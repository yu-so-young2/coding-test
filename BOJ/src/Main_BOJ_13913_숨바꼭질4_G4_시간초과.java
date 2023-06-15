import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_13913_숨바꼭질4_G4_시간초과 {
    static class Step {
        int level;
        List<Integer> history;

        Step(int level, List<Integer> history) {
            this.level = level;
            this.history = new ArrayList<>();

            if(history != null) {
                for (int i = 0; i < history.size(); i++) {
                    this.history.add(history.get(i));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int src = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());


        Queue<Step> q = new LinkedList<>();
        boolean[] visited = new boolean[1000001];

        Step step = new Step(0, null);
        step.history.add(src);
        visited[src] = true;
        q.add(step);

        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Step cur = q.poll();
                int level = cur.level;
                List<Integer> history = cur.history;
                int last = history.get(history.size()-1);
                if(last == dest) {
                    sb.append(level+"\n");
                    for (Integer n:history) {
                        sb.append(n+" ");
                    }
                    System.out.println(sb);
                    return;
                }

                if(last < dest && last*2 < 100001 && !visited[last*2]) {
                    Step newStep = new Step(level+1, history);
                    newStep.history.add(last*2);
                    visited[last*2] = true;
                    q.add(newStep);
                }
                if(last-1 >= 0 && !visited[last-1]) {
                    Step newStep = new Step(level+1, history);
                    newStep.history.add(last-1);
                    visited[last-1] = true;
                    q.add(newStep);
                }
                if(last < dest && last+1 < 100001 && !visited[last+1]) {
                    Step newStep = new Step(level+1, history);
                    newStep.history.add(last+1);
                    visited[last+1] = true;
                    q.add(newStep);
                }


            }
        }

    }
}