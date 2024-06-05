import java.util.HashMap;
import java.util.Map;

public class Solution_Programmers_KAKAO_숫자문자열과영단어_더빠른풀이_L1 {
    public int solution(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            // 숫자라면 바로 넣기
            if(Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            } else {
                // 아니라면 map에 hit할 때까지 가기
                int j = i+1;
                while(map.get(s.substring(i, j+1))==null) j++;
                int number = map.get(s.substring(i, j+1));
                sb.append(number);
                i = j;
            }
        }

        return Integer.parseInt(sb.toString());
    }
}