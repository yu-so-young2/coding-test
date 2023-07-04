package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2513_통학버스_G3 {
    static int N, K, S, house[], total;
    static List<Integer> left, right;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아파트 단지 수
        K = Integer.parseInt(st.nextToken()); // 통학버스 정원
        S = Integer.parseInt(st.nextToken()); // 학교 위치

        house = new int[100001];
        left = new LinkedList<>();
        right = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int apt = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            house[apt] = num;
            if(apt < S) left.add(apt);
            else right.add(apt);
        }

        total = 0;
        Collections.sort(left);
        Collections.sort(right, Collections.reverseOrder());

        while (!left.isEmpty()) {
            // 가장 먼 거리에 있는 사람
            int apt = left.get(0);
            total += (S-apt)*2;

            // 데려올 수 있을 만큼 데려오기
            int cur = 0;
            int i = 0;
            while(i < left.size() && cur < K) {
                int curApt = left.get(i);
                int num = house[curApt];

                // 다 가져올 수 있으면 다 가져온 후에 list에서 삭제
                if(num+cur <= K) {
                    cur += num;
                    i++;
                } // 다 못가져오면 가져올 수 있는 만큼만 삭제
                else if(num+cur > K) {
                    house[curApt] -= (K-cur);
                    cur = K;
                }
            }
            // i 이전 모두 삭제
            int j = 0;
            while(j < i) {
                left.remove(0);
                j++;
            }
        }

        while (!right.isEmpty()) {
            // 가장 먼 거리에 있는 사람
            int apt = right.get(0);
            total += (apt-S)*2;

            // 데려올 수 있을 만큼 데려오기
            int cur = 0;
            int i = 0;
            while(i < right.size() && cur < K) {
                int curApt = right.get(i);
                int num = house[curApt];

                // 다 가져올 수 있으면 다 가져온 후에 list에서 삭제
                if(num+cur <= K) {
                    cur += num;
                    i++;
                } // 다 못가져오면 가져올 수 있는 만큼만 삭제
                else if(num+cur > K) {
                    house[curApt] -= (K-cur);
                    cur = K;
                }
            }
            // i 이전 모두 삭제
            int j = 0;
            while(j < i) {
                right.remove(0);
                j++;
            }
        }

        System.out.println(total);
    }
}
