/**
 * 1. 아이디어
 * - 차액이 있으면 무조건 구입 및 매수... 단타가 답이다
 *
 * 2. 시간복잡도
 * O(N)
 *
 * 3. 자료구조
 * 필요없음
 *
 */

public class Solution_Leetcode_122_BestTimeToBuyAndSellStock2_Medium {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i-1];
            if(diff > 0) profit += diff;
        }
        return profit;
    }
}
