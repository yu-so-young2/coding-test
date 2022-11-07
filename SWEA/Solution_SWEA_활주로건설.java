import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_활주로건설 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T, N, X;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;

            //가로줄 탐색
            for (int i = 0; i < N; i++) {
//                System.out.println("row: "+i);
                int[] row = map[i];
                if(check(row, X)) cnt++;
            }

            //세로줄 탐색
            for (int j = 0; j < N; j++) {
                int[] row = new int[N];
                for (int i = 0; i < N; i++) {
                    row[i] = map[i][j];
                }

                if(check(row, X)) cnt++;
            }

            sb.append("#"+tc+" "+cnt+"\n");
        }//test case
        System.out.println(sb);
    }

    public static boolean check(int[] row, int x) {
        boolean possible = false;
        boolean[] slope = new boolean[row.length];

        for (int i = 0; i < row.length-1; i++) {
//            System.out.println("check "+row[i]);
            int diff = row[i+1] - row[i]; //다음 칸과 현재 칸의 차이
            switch(diff) {
                case 0: //동일한 높이
                    break;
                case 1: //1칸 올라감
                    if(checkUp(row, x, i, slope)) {
                        setUpSlope(x, i, slope);
                    } else {
//                        System.out.println("return false");
                        return false;
                    }
                    break;
                case -1: //1칸 내려감
                    if(checkDown(row, x, i+1, slope)) {
                        setDownSlope(x, i+1, slope);
                    } else {
//                        System.out.println("return false");
                        return false;
                    }
                    break;
                default: //2칸 이상 차이 => 불가능
//                    System.out.println("return false");
                    return false;
            }
        }
        return true;
    }

    private static void setDownSlope(int x, int cur, boolean[] slope) {
        int idx = cur;
        for (int i = 0; i < x; i++) {
            slope[idx] = true;
            idx++;
        }
    }

    private static boolean checkDown(int[] row, int x, int cur, boolean[] slope) {
        int height = row[cur];
        int idx = cur;
        for (int i = 0; i < x; i++) {
            if(idx == row.length) return false; //index 부족
            if(slope[idx]) return false; //이미 slope 지어진 경우
            if(row[idx] != height) return false; //높이가 다를 경우
            idx++;
        }
        return true;
    }

    //올라가는 경사로 설치
    private static void setUpSlope(int x, int cur, boolean[] slope) {
        int idx = cur;
        for (int i = 0; i < x; i++) {
            slope[idx] = true;
            idx--;
        }
    }

    //현재 위치에서 x길이만큼의 올라가는 경사로 설치 가능 여부 확인
    public static boolean checkUp(int[] row, int x, int cur, boolean[] slope) {
        int height = row[cur];
        int idx = cur;
        for (int i = 0; i < x; i++) {
            if(idx < 0) return false; //index 부족
            if(slope[idx]) return false; //이미 slope 지어진 경우
            if(row[idx] != height) return false; //높이가 다를 경우
            idx--;
        }
        return true;
    }


}