import java.util.HashMap;
import java.util.Map;

public class Solution_Programmers_KAKAO_숫자문자열과영단어_더간단한풀이L1 {
    public int solution(String s) {
        String[] num = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for (int i = 0; i < 10; i++){
            s = s.replace(num[i],Integer.toString(i));
        }
        return Integer.parseInt(s);
    }
}