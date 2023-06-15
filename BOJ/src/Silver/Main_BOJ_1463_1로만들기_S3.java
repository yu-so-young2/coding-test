package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BOJ_1463_1로만들기_S3 {
    static int n;
    public static int bfs(int n) {
        Queue<Integer> q = new LinkedList<Integer>();

        boolean visited[] = new boolean[n+1];

        visited[n] = true;
        q.add(n);
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                //System.out.println(cur+" and level: "+level);

                if(cur == 1) {
                    return level;
                }

                if(cur%3==0 && !visited[cur/3]) {
                    visited[cur/3] = true;
                    q.add(cur/3);
                }
                if(cur%2==0 && !visited[cur/2]) {
                    visited[cur/2] = true;
                    q.add(cur/2);
                }

                if(!visited[cur-1]) {
                    visited[cur-1] = true;
                    q.add(cur-1);
                }

            }
            level++;
        }

        return level;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int result = bfs(n);

        System.out.println(result);
    }

}
