/**
 *
 * int r, c // idx
 * for(row = 0 ~ =n/2) {
 *      r, c = row 에서 시작
 *      while(c < last index of this shell) // last index of this shell = n-1-r
 *
 *
 *
 *
 *          c++
*      }
 * }
 */
public class Solution_Leetcode_48_RotateImage_Medium {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int lastIdxOfShell;
        int r, c, nextR, nextC, curValue, prevValue;
        for (int row = 0; row <= n/2; row++) {
            r = row; c = row; // 현재 껍질의 시작점
            lastIdxOfShell = n - row - 1;
            System.out.printf("r, c = %d, %d, lastIdxOfShell = %d\n", r, c, lastIdxOfShell);

            while(c < lastIdxOfShell) {
                // matrix[r,c]를 기준으로 한바퀴 돌자~
                prevValue = matrix[r][c];

                for (int dir = 0; dir < 4; dir++) {
                    // r,c -> nextR, nextC 구하기
//                    nextR = getNextR(r, c, dir);
//                    nextC = getNextC(r, c, dir);

                    // value 값 저장
//                    curValue = matrix[nextR][nextC];

//                    matrix[nextR][nextC] = prevValue;
//                    r = nextR;
//                    c = nextC;
                }

                c++;
            }
        }
    }


    public static int[][] matrix;
    public static void main(String[] args) {
        matrix = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}