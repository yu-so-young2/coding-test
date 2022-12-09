public class Solution_Programmers_KAKAO_불량사용자_L3 {

        static int answer;
        static boolean[] visited;

        /**
         * idx: 매칭되는 응모자 아이디를 찾기 위한 불량 사용자 아이디 인덱스
         * curCase: 현재 매칭된 응모자 아이디 목록(bitmasking)
         *
         // 매개변수(전달) how? which?
         1) user_id 중 어떤 것을 check 했는지 boolean?
         순서 상관없이 동일하다면 같은 것으로 처리해야함 -> 비트마스킹 하자 00000000 ~ 11111111 = 2^8-1 = 255
         2) 응모자 아이디에서 매칭시킬 다음 banned_id <- idx 를 넘기자
         *
         */
        public void dfs(String[] user_id, String[] banned_id, int idx, int curCase){
            //가능한 경우의 수 완성-> answer++ 후 리턴
            if(idx == banned_id.length) { //모든 불량사용자에 대하여 탐색이 끝났다면
                //System.out.println(Integer.toBinaryString(curCase));
                //curCase 방문했었는지 확인
                if(!visited[curCase]) {
                    //방문안했다면 answer++, visit 처리
                    answer++;
                    visited[curCase] = true;
                }

                return;
            }

            //System.out.println("find "+banned_id[idx]);
            //가능한 경우 찾기
            for(int i = 0; i < user_id.length; i++) { //모든 응모자 아이디에 대하여
                //banned_id[idx] 에 대한 매칭 검사
                //해당 아이디 방문체크 되어있으면 검사안하고 바로 감
                if((curCase & (1<<i)) != 0) continue;

                //매칭되고 curCase 에 체크되어있지않다면((curCase & (1<<i))==0)
                //해당 아이디 방문 체크하고(curCase | (1<<i))
                if(matching(user_id[i], banned_id[idx])) {
                    //System.out.println("find! at "+i+" and "+user_id[i]);

                    //해당 아이디 방문체크하고 다음 불량사용자로 넘어가기
                    //다음 경우의수로 전진(dfs)
                    dfs(user_id, banned_id, idx+1, curCase | (1<<i));

                    //다음 for문 continue를 통해 현재 아이디 체크 안하고 다음 아이디로 넘어가기

                } //if matching
            } //for

        } //dfs()


        /**
         * 특수문자 *로 가려져 있는 banned_id가 user_id가 될 수 있는지 여부 boolean type 리턴
         */
        public static boolean matching(String user_id, String banned_id) {
            //일단 글자수가 같아야 함 다르면 바로 false 리턴
            if(user_id.length() != banned_id.length()) return false;

            //한글자씩 보면서
            //별이면 다음 글자로 넘어가고
            //다르면 false 리턴
            //끝까지 문제없으면 true 리턴
            for(int i = 0; i < banned_id.length(); i++) {
                char c1 = banned_id.charAt(i);
                char c2 = user_id.charAt(i);

                if(c1=='*') continue; //별이면 넘어가
                if(c1!=c2) return false; //다르면 바로 false
            }
            return true;
        }


        public int solution(String[] user_id, String[] banned_id) {
            answer = 0;
            visited = new boolean[256]; //0~255 저장 가능

            dfs(user_id, banned_id, 0, 0);

            return answer;
        }
    }
