import java.util.*;
import java.io.*;


class Solution_Programmers_KAKAO_뉴스클러스터링_L2 {

    public static HashMap<String, Integer> set1, set2;
    public static HashSet<String> set;

    public static boolean validate(String str){
        // str 의 두 문자가 모두 알파벳인지 확인하는 함수
        for(int i = 0; i < 2; i++) {
            if(!(str.charAt(i)>='A' &&str.charAt(i)<='Z')) return false;
        }
        return true;
    }

    public int solution(String str1, String str2) {
        int answer = 0; // 유사도 결과 저장

        // Division By Zero
        if(str1.length() == 0  || str2.length() ==0) {
            return 65536;
        }

        // HashSet
        set1 = new HashMap<>();
        set2 = new HashMap<>();
        set = new HashSet<>(); // 모든 key 저장할 set

        for(int i = 0; i < str1.length()-1; i++) {
            String str = str1.substring(i,i+2);
            // 모두 대문자로 변경
            str = str.toUpperCase();

            // 해당 문자열의 유효성 검사(영문자로 된 글자쌍만 유효)
            if(!validate(str)) continue;


            if(set1.containsKey(str)) { // 해당 문자열을 가지고 있다면
                set1.put(str, set1.get(str)+1); // 개수 증가
            } else { // 아니면 개수 증가
                set1.put(str, 1);
            }

            set.add(str);
        }

        for(int i = 0; i < str2.length()-1; i++) {
            String str = str2.substring(i,i+2);

            // 모두 대문자로 변경
            str = str.toUpperCase();

            // 해당 문자열의 유효성 검사(영문자로 된 글자쌍만 유효)
            if(!validate(str)) continue;

            if(set2.containsKey(str)) { // 해당 문자열을 가지고 있다면
                set2.put(str, set2.get(str)+1); // 개수 증가
            } else { // 아니면 추가
                set2.put(str, 1);
            }
            set.add(str);
        }

        // 합집합 = 한쪽에만 있으면 그냥 추가, max
        int union = 0;
        for(String key : set) { // 모든 다중집합에 대하여
            int value1 = 0, value2 = 0;
            // 각 set 에 있다면 개수 가져오기
            if(set1.containsKey(key)) value1 = set1.get(key);
            if(set2.containsKey(key)) value2 = set2.get(key);

            // str1 에만 있는 경우
            if(value1!=0 && value2==0) {
                union += value1;
            }
            
            // str2 에만 있는 경우
            else if(value1==0 && value2!=0) {
                union += value2;
            }
            
            // str1, str2 에 모두 있는 경우
            else if(value1!=0 && value2!=0) {
                union += Math.max(value1, value2);
            }
        }

        // 교집합 = 둘다 있는 경우 min
        int intersection = 0;
        // set1 기준으로 돌면서
        for(String key : set1.keySet()) {
            int value = set1.get(key); // 해당 키의 value
            if(set2.containsKey(key)) intersection += Math.min(value, set2.get(key));
        }

        // union == 0 인 경우 보정
        if(union == 0) return 65536;

        answer = intersection * 65536 / union;
        return answer;
    }
}
