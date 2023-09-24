/**
 1. 아이디어
 - 갈 때 배달, 올 때 수거
 - 한 번 갈 때 최대의 거리만 알면 x2
 - 1트) 배달, 수거를 스택으로 저장해두고 뺄까?
 -> 이러면 스택 만들기에만 50*100,000 번의 연산 필요
 -> 최대한 주어진 배열 데이터로 사용하자

 - 2트) 현재 확인해야 할 최대 거리를 글로벌로 저장하고, 갈 때마다 갱신
 - k = 현재 확인해야 할 최대 거리의 집
 - curDeliveryCap : 현재 배달에서 배달 가능한 남은 개수
 - curPickCap : 현재 배달에서 수거 가능한 남은 개수
 - k 부터 하나씩 줄여나가기
 - 배달 가능 개수 없으면 여기서 멈추고 curDeliveryMax
 - 픽업 가능 개수 없으면 여기서 멈추고 curPickMax
 - k 를 이동 거리에 추가
 - 둘 중 더 큰 값으로 k 갱신
 -> 이렇게 하면 불필요한 연산이 많음(이미 수거, 배달할 곳이 없는 집도 계속 중복 확인해야함)..
 - 불필요한 중복 확인을 없애는 게 더 중요하니까.. 일단 스택으로 해볼까?

 2. 시간복잡도
 - 스택 채우기 : O(100,000 * 50) ~= O(n)
 - 배달하기 : O(n)
 - O(n)
 - 스택 채우는 부분이 비효율적임..

 3. 자료구조
 - Stack<Integer> deliveryList, pickupList : 배달/수거 리스트
 - int delMax, pickMax : 현재 배달에서 가야 할 최대 거리
 */

import java.util.*;

class Solution_Programmers_KAKAO_택배배달과수거하기_L2 {
    public static Stack<Integer> deliveryList;
    public static Stack<Integer> pickupList;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 두 스택 생성
        deliveryList = new Stack<Integer>();
        pickupList = new Stack<Integer>();
        // 스택 채우기
        for(int i = 1; i <= n; i++) {
            // 각 집의 배달/수거 개수만큼 집 숫자 넣기
            for(int j = 0; j < deliveries[i-1]; j++) {
                deliveryList.push(i);
            }
            for(int j = 0; j < pickups[i-1]; j++) {
                pickupList.push(i);
            }
        }

        long totalDistance = 0;
        // 둘 다 빈 스택이 때까지
        while(!(deliveryList.isEmpty() && pickupList.isEmpty())) {
            // 둘 중 더 먼 곳으로 시작
            int delMax = deliveryList.isEmpty()?0:deliveryList.peek();
            int pickMax = pickupList.isEmpty()?0:pickupList.peek();
            int curDistance = Math.max(delMax, pickMax);
            totalDistance += Long.valueOf(curDistance);
            //System.out.println(delMax+", "+pickMax+" => "+curDistance + " 만큼 가기");

            // 가능한만큼 배달/수거
            for(int i = 0; i < cap; i++) {
                if(deliveryList.isEmpty()) break; // 더이상 배달할 곳이 없으면 끝
                deliveryList.pop();
            }
            for(int i = 0; i < cap; i++) {
                if(pickupList.isEmpty()) break; // 더이상 수거할 곳이 없으면 끝
                pickupList.pop();
            }


        }

        long answer = totalDistance * 2;
        return answer;
    }
}