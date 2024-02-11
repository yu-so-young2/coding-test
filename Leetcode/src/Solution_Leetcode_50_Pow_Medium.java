/**
 * 1. 아이디어
 * - for(0~n) 거듭제곱하면 시간터짐!
 * - 분할정복을 사용하여 logN으로 나누기!
 * - Memory 초과로 인해 메모이제이션은 불가ㅠㅠ
 *
 * 2. 자료구조
 * - X
 *
 * 3. 시간복잡도
 * - O(logN)
 */
public class Solution_Leetcode_50_Pow_Medium {
    public static double myPow(double x, int n) {
        // 만약 n==0 이면 1 리턴
        if(n==0) return 1;

        if(n > 0) {

            double temp = myPow(x, n/2);
            if(Math.abs(n) % 2 == 0) {
                return temp * temp;
            } else { // 2^5 = 2^2 * 2^2 * 2
                return temp * temp * x;
            }
        } else {
            double temp = myPow(x, n/2*-1);
            if(Math.abs(n) % 2 == 0) {
                return 1 / (temp * temp);
            } else {
                return 1 / (temp * temp * x);
            }
        }
    }
}
