import java.util.Arrays;

/**
 * 1. 아이디어
 * - dp[i] = i를 만들 수 있는 가장 적은 개수
 * - for(coin)
 * dp[i] = Min(dp[i-coin]+1)
 *
 * 2. 시간복잡도
 * O(amount * coins) = O(12 * 2^31)
 *
 * 3. 자료구조
 * int[] dp
 */
public class Solution_Leetcode_322_CoinChange_Medium {
    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 0;
        int result = coinChange(coins, amount);
        System.out.println(result);
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];

        // dp 배열 초기화
        for (int i = 0; i <= amount; i++) {
            dp[i] = -1;
        }

        Arrays.sort(coins);
        // 해당 코인을 가지고 있다면 1번만에 지불 가능
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coin == amount) return 1; // 이미 amount 크기의 코인이 있다면 1번만에 가능
            if (coin > amount) break; // amount보다 큰 코인이라면 사용할 필요 없음, 또한 오름차순이므로 더이상 확인할 필요 없음
            dp[coin] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            if (dp[i] == 1) continue; // 이미 해당 코인을 가지고 있다면 넘어가기
            int minCount = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin > amount) break; // amount보다 큰 코인이라면 이 이상으론 확인할 필요 없음
                if (i - coin < 0 || dp[i - coin] == -1) { // 그 전 코인이 없거나 불가능했다면 X
                    continue;
                }
                minCount = Math.min(minCount, dp[i - coin] + 1);
            }
            if (minCount != Integer.MAX_VALUE) dp[i] = minCount;
        }

        return dp[amount];
    }
}