/**
 * 1. 아이디어
 * - 4를 기준으로 절대값 계산
 * - 0이상이라면 charAt(0), 음수라면 charAt(1)
 */

import java.util.*;

class Solution_Programmers_KAKAO_성격유형검사하기_L1 {
    public static String reverse(String type) {
        if("TR".equals(type)) return "RT";
        else if("CF".equals(type)) return "CF";
        else if("MJ".equals(type)) return "JM";
        return "AN";
    }

    public static String solution(String[] survey, int[] choices) {

        Map<String, Integer> results = new HashMap<>();
        String[] types = {"RT", "CF", "JM", "AN"};
        for(String type : types) {
            results.put(type, 0);
        }


        for(int i = 0; i < survey.length; i++) {
            // 있다면 == 정순 => 4-choice
            if(results.get(survey[i]) != null) {
                results.put(survey[i], results.get(survey[i]) + (4 - choices[i]));
            } else {
                String reverseType = reverse(survey[i]);
                results.put(reverseType, results.get(reverseType) + (choices[i] -4));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String type : types) {
            int result = results.get(type);
            if(result >= 0) sb.append(type.charAt(0));
            else sb.append(type.charAt(1));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] survey = {"RT", "RT", "RT"};
        int[] choices = {7,5,2};
        String result = solution(survey,choices);
        System.out.println(result);
    }
}