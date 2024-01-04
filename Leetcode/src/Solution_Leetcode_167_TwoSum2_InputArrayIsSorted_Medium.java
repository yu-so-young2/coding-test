/*
1. 아이디어
- 투포인터 p1, p2
- 두 수의 합 < 타겟 => p1++
- 두 수의 합 > 타겟 => p2--
- 문제에서 무조건 1개의 정답이 있다고 했으므로 그 외의 경우(정답 페어가 없거나 여러개)는 고려하지 않아도 됨

2. 시간복잡도
O(n)

3. 자료구조
int p1, p2

 */
public class Solution_Leetcode_167_TwoSum2_InputArrayIsSorted_Medium {

    public static void main(String[] args) {
        int[] numbers = {-1,0};
        int target = -1;

        int[] result = twoSum(numbers, target);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]+ " ");
        }
    }

    public static int[] twoSum(int[] numbers, int target) {
        int p1 = 0, p2 = numbers.length-1;
        while(true){
            int sum = numbers[p1] + numbers[p2];
            if(sum < target) p1++;
            else if(sum > target) p2--;
            else return new int[]{p1+1, p2+1};
        }
    }
}
