package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_12851_숨바꼭질2_G4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean visited[] = new boolean[100001];

        Queue<Integer> q = new LinkedList<>();
        q.add(end);

        int level = 0;
        boolean isFind = false;
        int ansCnt = 0;
        int ansLevel = 0;
        while(!q.isEmpty() && !isFind) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int temp = q.poll();
                visited[temp] = true;

                if(temp == start) {
                    isFind = true;
                    ansLevel = level;
                    ansCnt++;
                }
                //각각 범위내에 있고, 방문 안했다면
                // 나누기 2
                if(temp%2==0 && temp/2>=0 && !visited[temp/2]) {
                    q.add(temp/2);
                }

                // 더하기 1
                if(temp+1 <= 100000 && !visited[temp+1]) {
                    q.add(temp+1);
                }
                // 빼기 1
                if(temp-1 >= 0 && !visited[temp-1]) {
                    q.add(temp-1);
                }

            }
            level++;
        }

        System.out.println(ansLevel);
        System.out.println(ansCnt);
    }
}