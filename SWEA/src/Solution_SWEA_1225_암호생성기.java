import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * swea 1225. 암호생성기
 * 큐 이용 간단 문제
 * 
 */


public class Solution_SWEA_1225_암호생성기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st; 
        int key;
        int temp;
         
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            st = new StringTokenizer(br.readLine());
 
            //queue initialize
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }
             
            //암호생성
            key = 1;
            while(true) {
                temp = queue.remove();
                queue.add(Math.max(temp-key, 0));
                if(temp-key<=0) break;
                key++;
                if(key==6) key=1;
            }
             
            sb.append("#"+tc+" ");
            for (Integer integer : queue) {
                sb.append(integer+" ");
            }
            sb.append("\n");
        }
         
        System.out.println(sb.toString());
         
    }//main
}