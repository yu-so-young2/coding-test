/**
 * 1. 아이디어
 * 처음 - Priority Queue 사용하여 순서대로 꺼내기
 * [1,2,0,1] => output = 3 같은 것이 연속으로 있을 경우도 고려해야함
 *
 * 속도 개선 -
 */

import java.util.PriorityQueue;

public class Solution_Leetcode_128_LongestConsecutiveSequence_Medium {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        int result = longestConsecutive(nums);
        System.out.println(result);
    }

    public static int longestConsecutive(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }

        int curLen = 1;
        int max = 0;
        int last = Integer.MIN_VALUE, cur;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(cur == last+1) { // 연속이면
                curLen++;
            } else if(cur != last) { // 같지도 않고 연속도 아니면
                curLen = 1;
            }

            max = Math.max(max, curLen);
            last = cur;
        }

        return max;
    }
}
