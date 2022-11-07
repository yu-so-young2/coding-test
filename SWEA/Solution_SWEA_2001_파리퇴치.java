import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 2001. 파리퇴치
 * DP, 누적합, 구간합
 * 
 */



public class Solution_SWEA_2001_파리퇴치 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        int N, M;
        int deadFly = 0;
        int curDeadFly;
 
        int[] start = new int[2];
        int[] end = new int[2];
         
        StringBuilder sb = new StringBuilder();
         
        for (int testcase = 1; testcase <= T; testcase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); //파리채 크기
            deadFly = 0;
             
            int[][] map = new int [N+1][N+1];
            for (int i = 0; i < map.length; i++) {
                map[0][i] = 0;
                map[i][0] = 0;
            }
             
            //map initialize, 누적합 저장
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i+1][j+1] = Integer.parseInt(st.nextToken());
                    map[i+1][j+1] = map[i+1][j+1]+map[i+1][j]+map[i][j+1]-map[i][j]; //위, 왼쪽 합 더하고 중복되는 합(대각선왼쪽) 빼기
                }
            }
             
            //시작 인덱스
            start[0] = 1; start[1] = 1;
            end[0] = M; end[1] = M;
 
            //파리채 움직이며 최대 누적합 찾기
            for (int i = 0; i <= N-M; i++) {
                for (int j = 0; j <= N-M; j++) {
                    curDeadFly = map[end[0]][end[1]] - map[start[0]-1][end[1]] - map[end[0]][start[1]-1] + map[start[0]-1][start[1]-1];
                    deadFly = Math.max(deadFly, curDeadFly);
                    start[1]++;
                    end[1]++;
                }
                start[1] = 1; end[1] = M;
                start[0]++;
                end[0]++;
            }
            sb.append("#"+testcase+" "+deadFly+"\n");
             
        }//for each testcase
        System.out.println(sb.toString());
    }
}