/*
1. 아이디어
- 단순 복사..
- newIndex = (i + k) % n;

2. 시간복잡도
O(N)

3. 자료구조
- int[] rotatedArray : 새로 복사할 배열
- int newIndex : 인덱스 계산
*/

public class Solution_Leetcode_189_RotateArray_Medium {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums, 3);
    }
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n; // 회전 횟수
        int[] rotatedArray = new int[n]; // 회전된 값을 저장할 새로운 배열

        // 복사
        for (int i = 0; i < n; i++) {
            int newIndex = (i + k) % n;
            rotatedArray[newIndex] = nums[i];
        }

        // rotatedArray를 다시 nums로 복사
        System.arraycopy(rotatedArray, 0, nums, 0, n);
    }
}