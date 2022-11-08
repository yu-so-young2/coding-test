import java.util.LinkedList;

public class Solution_Programmers_KAKAO_캐시 {
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        //캐시 저장 자료구조: LinkedList
        //1) 자료의 조회(contain()) 빈번하게 발생
        //2) 자료의 중간 삭제 및 삽입이 빈번하게 발생 -> Array 비효율적
        //3) 적재된 자료의 순서(오래된 순서) 기억 -> Hash 비효율적
        LinkedList<String> cache = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase(); //우선 모두 소문자로 변경하여 작업 진행

            //캐시 안에 있는지 확인
            boolean cacheHit = false; //캐시히트 여부 저장
            //캐시 데이터 순회(캐시히트 확인)
            for (int j = 0; j < cache.size(); j++) {
                if(cache.get(j).equals(city)) { //캐시히트 발생
                    cacheHit = true;
                    answer += 1;

                    cache.remove(j);
                    cache.add(city);
                    break; //더이상 찾지 않고 나가기(나가지 않으면 방금 add한 캐시 데이터 뒤에서 또 발견)
                }//캐시히트 처리
            }
            if(!cacheHit) { //캐시미스 발생
                answer += 5;
                if(cache.size() < cacheSize) { //캐시에 데이터 더 추가할 수 있음
                    cache.add(city);
                } else if(cache.size() > 0) { //추가할 수 없고 삭제할 수 있는 데이터가 있다면
                        cache.remove(0);
                        cache.add(city);
                }
            }//캐시미스 처리
        }
        return answer;
    }
}