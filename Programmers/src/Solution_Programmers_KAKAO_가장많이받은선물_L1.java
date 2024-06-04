/**
 * 1. 아이디어
 * - 그대로 구현..
 * - Map을 활용하여 String -> index 접근을 가능하게 하는 것이 포인트!
 *
 * 2. 시간복잡도
 * - O(N^2)
 *
 * 3. 자료구조
 * - Map<String, Integer> dict : name으로 index 찾기
 * - int[] degree : 선물지수
 * - int[][] history : 주고받은 교환 이력
 * - int[] plans : 다음 달에 받을 선물 개수
 */

import java.util.*;

public class Solution_Programmers_KAKAO_가장많이받은선물_L1 {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;

        // 이름 map
        Map<String, Integer> dict = new HashMap<>();
        for(int i = 0; i < N; i++) {
            dict.put(friends[i], i);
        }

        // 선물지수, 이력
        int[] degree = new int[N];
        int[][] history = new int[N][N];

        for(String gift : gifts) {
            String[] names = gift.split(" ");
            int from = dict.get(names[0]);
            int to = dict.get(names[1]);

            // 선물지수 업데이트
            degree[from]++;
            degree[to]--;

            // 이력 업데이트
            history[from][to]++;
        }

        // 받을 선물 개수
        int[] plans = new int[friends.length];
        for(int i = 0; i < friends.length; i++) {
            for(int j = i+1; j < friends.length; j++) {

                if(history[i][j] > history[j][i]) {
                    plans[i]++;
                } else if(history[i][j] < history[j][i]) {
                    plans[j]++;
                } else {
                    // 선물지수 더 큰 사람
                    if(degree[i] > degree[j]) {
                        plans[i]++;
                    } else if(degree[i] < degree[j]) {
                        plans[j]++;
                    }
                }
            }
        }

        // 최대 선물 개수 구하기
        int answer = 0;
        for(int i = 0; i < friends.length; i++) {
            answer = Math.max(answer, plans[i]);
        }

        return answer;
    }
}
