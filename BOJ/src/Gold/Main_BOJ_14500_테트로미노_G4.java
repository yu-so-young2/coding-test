package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_테트로미노_G4 {
    static int N, M, maxSum;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //input
        // N M
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check(i, j);
            }
        }
        System.out.println(maxSum);
    }

    // (i, j) 위치에 테트로미노 놓았을 경우 합 구하기
    private static void check(int i, int j) {
        //총 19개의 모양..
        int sum = 0;
        /* I */
        if(j+3 < M) {
            sum = 0;
            for (int k = j; k <= j+3; k++) {
                sum += map[i][k];
            }
            maxSum = Math.max(maxSum, sum);
        }
        if(i+3 < N) {
            sum = 0;
            for (int k = i; k <= i+3; k++) {
                sum += map[k][j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        /* O */
        if(i+1 < N && j+1 < M) {
            sum = 0;
            for (int k = i; k <= i+1; k++) {
                for (int l = j; l <= j+1; l++) {
                    sum += map[k][l];
                }
            }
            maxSum = Math.max(maxSum, sum);
        }

        /* L */
        if(i+2 < N && j-1 >= 0) { // 아래로 2칸, 왼쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i+2][j-1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+2 < N && j+1 < M) { // 아래로 2칸, 오른쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i+2][j+1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+2 < N && j-1 >= 0) { // 아래로 2칸, 왼쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i][j-1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+2 < N && j+1 < M) { // 아래로 2칸, 오른쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i][j+1];
            maxSum = Math.max(maxSum, sum);
        }
        //
        if(i-1 >= 0 && j+2 < M) { // 오른쪽으로 2칸, 위로 1칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i-1][j+2];
            maxSum = Math.max(maxSum, sum);
        }
        if(i-1 >= 0 && j+2 < M) { // 오른쪽으로 2칸, 위로 1칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i-1][j];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+1 < N && j+2 < M) { // 오른쪽으로 2칸, 아래로 1칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i+1][j+2];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+1 < N && j+2 < M) { // 오른쪽으로 2칸, 아래로 1칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i+1][j];
            maxSum = Math.max(maxSum, sum);
        }



        /* S */
        if(i+2 < N && j+1 < M) { // 아래로 2칸, 오른쪽으로 1칸
            sum = 0;
            for (int k = 0; k <= 1; k++) {
                sum += map[i+k][j];
            }
            for (int k = 0; k <= 1; k++) {
                sum += map[i+k+1][j+1];
            }
            maxSum = Math.max(maxSum, sum);
        }

        if(i+2 < N && j-1 >= 0) { // 아래로 2칸, 왼쪽으로 1칸
            sum = 0;
            for (int k = 0; k <= 1; k++) {
                sum += map[i+k][j];
            }
            for (int k = 0; k <= 1; k++) {
                sum += map[i+k+1][j-1];
            }
            maxSum = Math.max(maxSum, sum);
        }

        if(i+1 < N && j+2 < M) { // 아래로 1칸, 오른쪽으로 2칸
            sum = 0;
            for (int k = 0; k <= 1; k++) {
                sum += map[i][j+k];
            }
            for (int k = 0; k <= 1; k++) {
                sum += map[i+1][j+k+1];
            }
            maxSum = Math.max(maxSum, sum);
        }
        if(i+1 < N && j-2 >= 0) { // 아래로 1칸, 왼쪽으로 2칸
            sum = 0;
            for (int k = 0; k <= 1; k++) {
                sum += map[i][j-k];
            }
            for (int k = 0; k <= 1; k++) {
                sum += map[i+1][j-k-1];
            }
            maxSum = Math.max(maxSum, sum);
        }

        /* T */
        if(i+2 < N && j+1 < M) { // 아래로 2칸, 오른쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i+1][j+1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i-1 >= 0 && j+2 < M) { // 위로 1칸, 오른쪽으로 2칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i-1][j+1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+2 < N && j-1 >= 0) { // 아래로 2칸, 왼쪽으로 1칸
            sum = 0;
            for (int k = i; k <= i+2; k++) {
                sum += map[k][j];
            }
            sum += map[i+1][j-1];
            maxSum = Math.max(maxSum, sum);
        }
        if(i+1 < N && j+2 < M) { // 아래로 1칸, 오른쪽으로 2칸
            sum = 0;
            for (int k = j; k <= j+2; k++) {
                sum += map[i][k];
            }
            sum += map[i+1][j+1];
            maxSum = Math.max(maxSum, sum);
        }
    }
}