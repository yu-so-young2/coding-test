import java.util.LinkedList;
import java.util.List;

/**
 1. 아이디어
 N*M 순회하면서 0을 발견하면 해당 행/열 기록
 기록된 모든 행/열에 대하여 0으로 세팅


 2. 시간복잡도
 O(N+M)?

 3. 자료구조
 boolean[n] row
 boolean[m] column
 */

public class Solution_Leetcode_73_SetMatrixZeros_Medium {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

//        List<Integer> row = new LinkedList<>();
//        List<Integer> column = new LinkedList<>();

        boolean[] row = new boolean[n];
        boolean[] column = new boolean[m];

        // 0 이 있는 row, column 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j]==0) {
//                    row.add(i);
//                    column.add(j);
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(row[i]) {
                for (int j = 0; j < m; j++) {
                   matrix[i][j] = 0;
              }
            }
        }

        for (int i = 0; i < m; i++) {
            if(column[i]) {
                for (int j = 0; j < n; j++) {
                    if(row[j]) continue; // 해당 열을 이미 방문한 적 있다면 넘어가기
                    matrix[j][i] = 0;
                }
            }
        }


//        // row 0 세팅
//        for (int i = 0; i < row.size(); i++) {
//            int r = row.get(i);
//            for (int j = 0; j < m; j++) {
//                matrix[r][j] = 0;
//            }
//        }
//
//        // column 세팅
//        for (int i = 0; i < column.size(); i++) {
//            int c = column.get(i);
//            for (int j = 0; j < n; j++) {
//                matrix[j][c] = 0;
//            }
//        }

    }
}
