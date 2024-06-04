/**
 * 1. 아이디어
 * - Map 활용하여 유형 별 만료기간 저장
 * - Date끼리의 비교, 연산, Formatter사용법
 */

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.*;

public class Solution_Programmers_KAKAO_개인정보수집유효기간_L1 {

    public static Map<String, Integer> types;

    public int[] solution(String today, String[] terms, String[] privacies) {

        // 약관
        types = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            String type = term[0];
            int range = Integer.parseInt(term[1]);

            types.put(type, range);
        }

        // privacies돌면서
        // 개인정보수집일자 + 약관의 유효기간이 < 현재날짜 전인지 확인
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            String dateString = privacy[0];
            String type = privacy[1];

            // 파기 목록에 추가
            if(isPassed(today, dateString, type))
                answer.add(i+1);
        }

        // List -> int[]
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static boolean isPassed(String todayString, String dateString, String type) {
        int month = types.get(type);

        //string => date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate today = LocalDate.parse(todayString, formatter);
        LocalDate expiration = LocalDate.parse(dateString, formatter).plus(month, ChronoUnit.MONTHS);

        return !expiration.isAfter(today);
    }

}