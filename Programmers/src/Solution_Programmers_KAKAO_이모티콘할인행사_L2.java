/*
1. 아이디어
- 모든 이모티콘에 대해 10, 20, 30, 40 중복순열 만들기 => 각 이모티콘의 할인율 저장할 int[] 필요
- 중복순열 : 각 이모티콘에 대해서 10, 20, 30, 40 호출, 마지막 단계에서 확인

2. 시간복잡도
- 순열 만들기 O(4^m) = O(4^7)
- 각 사람에 대해 조사 O(n)= O(100)
- 각 사람마다 모든 이모티콘에 대해 조사 O(m) = 7
=> O(4^7 * 100 * 7)

3. 자료구조
- 각 이모티콘의 할인율 저장 int[], 할인율 설정을 위한 enum int[]
- 정답 이모티콘 가입자수, 판매액 (static)
- 현재 이모티콘 가입자수, 판매액 (local)
 */

public class Solution_Programmers_KAKAO_이모티콘할인행사_L2 {
    public static int subsNum, totalSale, sales[], salesEnum[];

    public static int[] solution(int[][] users, int[] emoticons) {
        // 초기화
        subsNum = 0;
        totalSale = 0;
        sales = new int[emoticons.length];
        salesEnum = new int[]{10, 20, 30, 40};

        // 할인율 중복순열 생성
        perm(0, users, emoticons);

        // 결과 출력
        int[] answer = {subsNum, totalSale};
        return answer;
    }

    private static void perm(int cnt, int[][] users, int[] emoticons) {
        if(cnt == emoticons.length) { // 순열 생성 완료
            int curSubsNum = 0;
            int curTotalSale = 0;

            // 각 사용자들에 대해 구매/구독 여부 조사
            for (int i = 0; i < users.length; i++) {
                boolean isSubscribe = false;
                int curUserSale = 0; // 현재 유저의 구매액
                int userRate = users[i][0]; // 현재 유저의 목표 할인 비율
                int userPrice = users[i][1]; // 현재 유저의 목표 구매액

                // 각 이모티콘에 대해 원하는 할인율 이상이면 일단 구매
                // 원하는 구매 비용 이상이면 당장 구독 및 탐색 중단
                for (int j = 0; j < emoticons.length; j++) {
                    if(sales[j] >= userRate) curUserSale += emoticons[j]-emoticons[j]*sales[j]/100;
                    if(curUserSale >= userPrice) {
                        isSubscribe = true;
                        break;
                    }
                }

                // 구독 했다면 구독만 ++
                // 구독 안했다면 구매가격 업데이트
                if(isSubscribe) curSubsNum++;
                else curTotalSale += curUserSale;
            }

            // 결과값 갱신
            // 현재 구독자수 비교하여
            if(curSubsNum > subsNum) { // 초과면 둘다 업데이트
                subsNum = curSubsNum;
                totalSale = curTotalSale;
            } else if(curSubsNum == subsNum) { // 같으면 둘 중 구매가격 더 높은 것으로 업데이트
                totalSale = Math.max(curTotalSale, totalSale);
            } // 작으면 무시하고 리턴

            return;
        }

        // 중복 순열 생성
        // sales[cnt]를 10, 20, 30, 40으로 각각 변경한 후 다음 호출
        for (int i = 0; i < 4; i++) {
            sales[cnt] = salesEnum[i]; // 10, 20, 30, 40%의 할인율
            perm(cnt+1, users, emoticons);
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900},{23, 10000}, {11, 5200}, {5,5900},{40,3100},{27,9200},{32,6900}};
        int[] emoticons = {1300,1500,1600,4900};
        int[] answer = solution(users, emoticons);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

}