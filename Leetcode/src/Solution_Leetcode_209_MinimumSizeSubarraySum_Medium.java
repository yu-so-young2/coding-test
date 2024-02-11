/**
 * 1. 아이디어
 * - 투포인터('연속'이므로 투포인터로 접근 가능!!)
 * - 포인터 옮겨가면서, target보다 값이 커지면 길이 갱신
 *   1) 커지면 target보다 작아질 때까지 start++
 *   2) 작으면 target보다 커질 때까지 end++
 *
 * 2. 시간복잡도
 * - O(n)
 *
 * 3. 자료구조
 * - int start, end // 투포인터
 * - int sum // 현재 subArray의 합계
 * - int min // 가장 짧은 subArray 길이 갱신
 *
 */
public class Solution_Leetcode_209_MinimumSizeSubarraySum_Medium {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        while (end < nums.length) {
            sum += nums[end++];

            while (sum >= target) {
                min = Math.min(min, end - start);
                sum -= nums[start++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
