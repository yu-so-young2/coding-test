package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_9019_DSLR_G4 {
    static int start, target;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            int[] from = new int[100000];
            char[] cmd = new char[100000];
            for (int j = 0; j < from.length; j++) {
                from[j] = -1;
            }


            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            from[start] = -2;

            while(!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    int cur = q.poll();
//                    System.out.println("cur: "+cur);

                    if(cur == target) {
//                        System.out.println("find!!!!!!=====");

                        Stack<Character> history = new Stack<>();
                        while(from[cur] != -2) {
//                            System.out.println("cur: "+cur);

                            history.add(cmd[cur]);
                            cur = from[cur];
                        }

                        while(!history.isEmpty()){
                            sb.append(history.pop());
                        }
                        sb.append("\n");
                        break;
                    }

                    int next = 0;

                    //D
                    next = cur*2;
                    if(next > 9999) {
                        next %= 10000;
                    }
                    if(from[next] == -1) {
                        q.add(next);
//                        System.out.println("D: *2 -> "+next);
//                        System.out.println("==> "+next+"의 부모: "+cur);
                        from[next] = cur;
                        cmd[next] = 'D';
                    }


                    //S
                    next = cur-1;
                    if(cur == 0) {
                        next = 9999;
                    }

                    if(from[next] == -1) {
                        q.add(next);
//                        System.out.println("S: -1 -> "+next);
//                        System.out.println("==> "+next+"의 부모: "+cur);

                        from[next] = cur;
                        cmd[next] = 'S';
                    }

                    //L
                    next = (cur%1000)*10+(cur/1000);
                    if(from[next] == -1) {
                        q.add(next);
//                        System.out.println("L: 왼쪽 -> "+next);
//                        System.out.println("==> "+next+"의 부모: "+cur);
                        from[next] = cur;
                        cmd[next] = 'L';
                    }

                    //R
                    next = (cur/10)+(cur%10)*1000;
                    if(from[next] == -1) {
                        q.add(next);
//                        System.out.println("R: 오른쪽 -> "+next);
//                        System.out.println("==> "+next+"의 부모: "+cur);
                        from[next] = cur;
                        cmd[next] = 'R';
                    }
                }
            }
        }
        System.out.print(sb);
    }

}