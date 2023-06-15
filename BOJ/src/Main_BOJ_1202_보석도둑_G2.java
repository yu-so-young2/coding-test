import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    이미 탐색한 보석을 다시 처음부터 탐색하지 않는다.
    앞의 가방에서 탐색한 보석들은 이미 현재 가방에서도 담을 수 있음이 보장되므로
    보석들의 가치만 우선순위 큐로 저장한 후
    현재 저장된 보석 중 가장 가치가 높은 것을 pop 한다.

 */

public class Main_BOJ_1202_보석도둑_G2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> items = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });


        ArrayList<Integer> bag = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] item = new int[2];
            item[0] = Integer.parseInt(st.nextToken()); // 보석 무게
            item[1] = Integer.parseInt(st.nextToken()); // 보석 가치
            items.add(item);
        }
        for (int i = 0; i < K; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bag);
        PriorityQueue<Integer> jewels = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        long total = 0; // 가방에 담긴 총 무게
        for (int i = 0; i < K; i++) {
            // bag.get(i) : 가방 무게
            while(!items.isEmpty()){
                int[] cur = items.poll();
                if(cur[0] > bag.get(i)) { // 담을 수 없으면 탐색 중단
                    items.offer(cur);
                    break;
                }

                jewels.add(cur[1]);
            }// while

            if(!jewels.isEmpty())
                total += jewels.poll();
        }

        System.out.println(total);
    }
}