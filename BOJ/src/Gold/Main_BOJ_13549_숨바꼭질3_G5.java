package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질3_G5 {
    static class Node {
        int num, step;

        public Node(int num, int step) {
            this.num = num;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean visited[] = new boolean[100001];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(end,0));
        visited[end] = true;

        int shortStep = 0;
        while(!q.isEmpty()) {
                Node cur = q.poll();
                int temp = cur.num;
                if(temp == start) {
                    System.out.print(cur.step);
                    return;
                }
                //각각 범위내에 있고, 방문 안했다면
                // 나누기 2
                if(temp%2==0 && temp/2>=0 && !visited[temp/2]) {
                    q.add(new Node(temp/2, cur.step));
                    visited[temp/2] = true;
                }

                // 더하기 1
                if(temp+1 <= 100000 && !visited[temp+1]) {
                    q.add(new Node(temp+1, cur.step+1));
                    visited[temp+1] = true;
                }
                // 빼기 1
                if(temp-1 >= 0 && !visited[temp-1]) {
                    q.add(new Node(temp-1, cur.step+1));
                    visited[temp-1] = true;
                }
        }
    }
}