/**
 * 1. 아이디어
 *   1) 비트마스킹 => 중복된 알파벳 체킹 불가!!
 *   2) 문자대로 정렬 후 HashMap에 저장
 *
 * 2. 시간복잡도
 *
 * 3. 자료구조
 * - HashMap<String, List<String>> map
 * - String sortedStr // 문자 정렬
 */

import java.util.*;
import java.util.stream.Collectors;

public class Solution_Leetcode_49_GroupAnagrams_Medium {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs){
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);

            if(!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(s);
        }

        return map.values().stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }
}
