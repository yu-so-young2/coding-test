/**
 1. 아이디어
 - 큰 곳에서 작은 곳 빼기
 - 불가능 여부
 - 전체 합이 홀수인 경우 불가능
 - 전체 큐 순회(2N 넘어가면) 불가능

 2. 시간복잡도
 - O(N)?

 3. 자료구조
 - Queue<integer> q1, q2 : 큐
 - long q1Sum, q2Sum : 각 큐의 합
 - int N : 큐 길이
 - int step : 작업 횟수
 */

import java.util.*;

public class Solution_Programmers_KAKAO_두큐합같게만들기_L2 {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length; // 큐 길이

        // 큐 구현
        Queue<Long> q1 = new LinkedList<Long>();
        Queue<Long> q2 = new LinkedList<Long>();
        long q1Sum = 0, q2Sum = 0;
        for(int i = 0; i < N; i++) {
            q1.add(Long.valueOf(queue1[i]));
            q2.add(Long.valueOf(queue2[i]));
            q1Sum += queue1[i];
            q2Sum += queue2[i];
        }

        // 불가능 여부 확인(전체 합이 홀수라면 불가능)
        if((q1Sum+q2Sum) % 2 == 1) return -1;
        int step = 0;

        while(q1Sum != q2Sum) {
            if(step > 3*N) return -1;

            if(q1Sum > q2Sum) {
                long a = q1.poll();
                q2.add(a);
                q1Sum -= a;
                q2Sum += a;
            }
            else if(q1Sum < q2Sum) {
                long a = q2.poll();
                q1.add(a);
                q2Sum -= a;
                q1Sum += a;
            }
            step++;
        }


        return step;
    }
}