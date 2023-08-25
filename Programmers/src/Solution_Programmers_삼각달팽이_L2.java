/**
 * 1. 아이디어
 * - 그대로 구현 및 출력
 * - n*n 이차원 배열에 삼각형 형태로 숫자 채우기
 * - 삼각형 형태가 되도록 인덱스 이동
 * - 이차원 배열 채우기 : while(value <= len) {
 *     // 1. 어레이 채우기
 *     arr[r][c] = value;
 *     // 2. 인덱스 이동
 *     // 2-1. 방향 전환 확인
 *          // 지금 방향 그대로 갔을 때 1) 어레이 범위 밖인지 / 2) 이미 채워져 있는지
 *     // 2-2. 새로운 방향(혹은 기존 방향)으로 인덱스 이동
 * }
 *
 * 2. 시간복잡도
 * - 넣어야 하는 숫자만큼(len) = n(n+1)/2 ~= n^2
 * - O(n^2)
 *
 * 3. 자료구조
 * - 길이 : int len
 * - 이차원 배열 : int[n][n] arr
 * - 출력할 정답 배열 : int[len] ans
 * - 인덱스 이동 dy, dx
 * - 이차원 배열 채울 때 필요한 현재방향/현재위치(r,c)
 */

class Solution_Programmers_삼각달팽이_L2 {
    int[] dy = {1, 0, -1}; // 아래->오른->위 순서
    int[] dx = {0, 1, -1};

    public int[] solution(int n) {
        int len = n*(n+1)/2; // 전체 길이
        int[][] arr = new int[n][n]; // 삼각달팽이 형태로 숫자 저장할 2차원 배열
        int[] ans = new int[len]; // 출력할 배열

        // arr 채우기
        int dir = 0;
        int r = 0, c = 0;
        int value = 1;

        while(value <= len) {
            // 어레이 채우기
            arr[r][c] = value;

            // 방향 전환 확인
            int ny = r+dy[dir];
            int nx = c+dx[dir];
            // 어레이 밖이거나 이미 채워져 있다면 방향 전환
            if(ny<0 || nx<0 || ny==n || nx==n || arr[ny][nx]!=0) {
                dir = dir+1==3?0:dir+1;
            }

            // 인덱스 이동
            r += dy[dir];
            c += dx[dir];
            value++;
        }

        // answer 채우기
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                ans[idx] = arr[i][j];
                idx++;
            }
        }

        return ans;
    }
}
