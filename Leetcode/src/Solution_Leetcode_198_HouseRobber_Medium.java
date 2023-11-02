/**
 1. 아이디어
 - 저번에 풀었던 포도주 시식 문제 하위호환 버전?
 1) X O = dp[i-2] + nums[i]
 2)   X = dp[i-1]
 이렇게 두 가지 경우에 대하여 더 큰 값 가져오면 될듯

 2. 시간복잡도
 O(N)

 3. 자료구조
 dp[i] : i까지 확인했을 때 최대로 얻을 수 있는 값
 */

public class Solution_Leetcode_198_HouseRobber_Medium {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            int a = nums[i];
            int b = 0;

            if(i >= 2) a += dp[i-2];
            if(i >= 1) b += dp[i-1];
            dp[i] = Math.max(a, b);
        }

        return dp[nums.length-1];
    }
}