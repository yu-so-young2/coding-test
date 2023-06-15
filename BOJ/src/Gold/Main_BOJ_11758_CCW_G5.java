package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11758_CCW_G5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] point = new int[4][2];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken()); // x
            point[i][1] = Integer.parseInt(st.nextToken()); // y
        }

        // 외적을 위하여 마지막만 반복
        point[3][0] = point[0][0]; // x
        point[3][1] = point[0][1]; // y

        // outer product: 외적 음수(-):시계방향, 양수(+):반시계방향, 0:일직선
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += (point[i][0]*point[i+1][1]);
        }
        for (int i = 1; i < 4; i++) {
            sum -= (point[i][0]*point[i-1][1]);
        }

        System.out.println(sum==0?0:sum/Math.abs(sum));
    }
}
