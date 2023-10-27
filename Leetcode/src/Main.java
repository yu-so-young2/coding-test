/**
 * 1. 아이디어
 * for(i)
 *    i가 0 ~ end 사이에 있으면 i의 이동거리만큼 end 갱신
 *      => 갱신한 end가 마지막 인덱스보다 같거나 크면 return true
 *    i가 0 ~ end 사이에 없으면 return false
 *
 * 생각) spread jump이기 때문에 가능한 방법이라고 생각함, 만약 거리가 "여기부터 5~9 사이만 뛸 수 있음" 이런 식이라면 불가능했을듯?
 * => 요러면 dfs, bfs 등의 완탐을 해야 했을 것 같음
 *
 * 2. 시간복잡도
 * O(N)
 *
 * 3. 자료구조
 * int end : last reachable index
 *
 */

class Solution_Leetcode_55_JumpGame_Medium {
    public static void main(String[] args) {
//        int[] nums = {2,3,1,1,4};
        int[] nums = {3,2,1,0,4};
        boolean answer = canJump(nums);
        System.out.println(answer);
    }
    public static boolean canJump(int[] nums) {
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            // i가 0~end 사이에 있음 == i번째 인덱스에 접근할 수 있음
            if(i <= end) {
                end = Math.max(i+nums[i], end);
                // 만약 end가 마지막 인덱스보다 같거나 커지면 마지막 인덱스를 방문할 수 있다는 뜻
                if(end >= nums.length) return true;
            }
            // i가 0~end 사이에 없음 == 더이상 갈 수 없음
            else return false;
        }
        return true;
    }
}