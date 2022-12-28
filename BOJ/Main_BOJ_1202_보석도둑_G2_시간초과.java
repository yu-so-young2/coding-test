import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1202_보석도둑_G2_시간초과 {
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
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] item = new int[2];
            item[0] = Integer.parseInt(st.nextToken()); // 보석 무게
            item[1] = Integer.parseInt(st.nextToken()); // 보석 가치
            items.add(item);
        }

        ArrayList<Integer> bag = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bag); // 가방 무게 작은 순으로 시작
        long total = 0; // 가방에 담긴 총 무게
        for (int i = 0; i < K; i++) {
            int[] get = new int[2]; // 현재 가방에 담을 아이템
            LinkedList<int[]> poll = new LinkedList<>();

            while(!items.isEmpty()){
                int[] cur = items.poll();
                if(cur[0] > bag.get(i)) { // 담을 수 없으면 탐색 중단
                    poll.add(cur);
                    break;
                }

                if(get[0]==0) { // 아직 가방에 담지 않았으면 담기
                    get[0] = cur[0];
                    get[1] = cur[1];
                } else {
                    if(get[1] < cur[1]) {
                        poll.add(new int[]{get[0], get[1]});
                        get[0] = cur[0];
                        get[1] = cur[1];
                    } else {
                        poll.offer(cur);
                    }
                }


            }// while

            while(!poll.isEmpty()){
                int[] a = poll.poll();
                items.add(a);
            }
            total += get[1];
        }

        System.out.println(total);
    }
}
