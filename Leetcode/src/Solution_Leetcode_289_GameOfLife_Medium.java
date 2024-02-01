/**
 * 1. 아이디어
 * 방법1) 이중for문 돌면서 각 cell마다 8방탐색
 * => O(N * M * 8),
 * => 불필요한 8번의 연산 중복 발생
 * 방법2) memoization!
 * sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + board[i][j];
 * neighbors = sum[i+1][j+1] - sum[i-2][j+1] - sum[i+1][j-2] + sum[i-2][j-2] - current;
 *
 * 2. 자료구조
 * int[][] sum : sum(i,j) = (i,j)까지의 직사각형의 누적합
 * int neighbors = 해당 칸 주변의 1 개수
 *
 * 3. 시간복잡도
 * O(N * M)
 *
 */

public class Solution_Leetcode_289_GameOfLife_Medium {
    public static void main(String[] args) {
        int[][] board = {{0,1,0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
    }
    static int sum[][], neighbors;
    public static void gameOfLife(int[][] board) {
        // sum[][] 생성
        sum = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
//                sum[i][j] = sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+board[i][j];
                sum[i][j] = board[i][j];
                if(i > 0) sum[i][j] += sum[i-1][j];
                if(j > 0) sum[i][j] += sum[i][j-1];
                if(i > 0 && j > 0) sum[i][j] -= sum[i-1][j-1];
            }
        }

//
//        // sum[][] 확인
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                System.out.print(sum[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        // 2중 for문을 통해 각 칸마다의 다음 스텝 구하기
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                neighbors = getNeighbors(i, j, board[i][j], board.length, board[0].length);
//                System.out.printf("(%d, %d)의 이웃 = %d\n", i, j, neighbors);
                if(board[i][j] == 0 && neighbors == 3) board[i][j] = 1;
                else if(board[i][j] == 1) {
                    if(neighbors < 2 || neighbors > 3) board[i][j] = 0;
                }
            }
        }

    }

    /**
     * sum[][] 을 기준으로 해당 (i,j) 주변의 8칸의 누적합 구하기
     * @param i
     * @param j
     * @return
     */
    private static int getNeighbors(int i, int j, int current, int n, int m) {
//         neighbors = sum[i+1][j+1] - sum[i-2][j+1] - sum[i+1][j-2] + sum[i-2][j-2] - current;

         int iPlus1 = Math.min(i+1, n-1);
         int jPlus1 = Math.min(j+1, m-1);

        int neighbors = sum[iPlus1][jPlus1];
        neighbors -= current;
        if(i-2 >= 0) neighbors -= sum[i-2][jPlus1];
        if(j-2 >= 0) neighbors -= sum[iPlus1][j-2];
        if(i-2 >= 0 && j-2 >= 0) neighbors += sum[i-2][j-2];

        return neighbors;
    }
}