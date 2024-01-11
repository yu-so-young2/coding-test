/**
 * 1. 아이디어
 * - 투포인터 사용하자!
 * - start, end 로 이동
 * - set으로 해당 문자가 있었는지 확인
 * - 이미 있는 문자라면 없을 때까지 start 이동
 *
 * 2. 시간복잡도
 * - O(2N) = O(N)?
 *
 * 3. 자료구조
 * - Set<Character>
 * - int start, end
 * - int maxLen
 *
 */

import java.util.HashSet;
import java.util.Set;

public class Solution_Leetcode_3_LongestSubstringWithoutRepeatingCharacters_Medium {
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int start = 0, end = 0;
        int maxLen = 0;

        while(end < s.length()) {
            char c = s.charAt(end);

            // 현재 string에 포함된 char인지 확인
            if(set.contains(c)) {
                // c가 없을 때까지 start++;
                while(set.contains(c)) {
                    char removeChar = s.charAt(start);
                    set.remove(removeChar);
                    start++;
                }
            }

            set.add(c);

            // update maxLen
            maxLen = Math.max(maxLen, end-start+1);
            end++;
        }

        return maxLen;
    }
}
