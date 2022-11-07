
/*
스도쿠
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_4056_스스스도쿠_G4 {
    static boolean find;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            find = false;
            int board[][] = new int[9][9];
            for (int i = 0; i < 9; i++) {
                str = br.readLine();
                for (int j = 0; j < 9; j++) {
                    board[i][j] = str.charAt(j)-'0';
                }
            }

            //simulation
            sudoku(0,0,board);

            //couldn't find answer
            if(!find) {
                System.out.println("Could not complete this grid.");
            }
        }
    }

    public static void sudoku(int r, int c, int[][] board) {
       if(board[r][c] == 0) {
            boolean isUsed[] = new boolean[10];
            //할 수 있는 숫자 찾기
            //가로
            for (int j = 0; j < 9; j++) {
                if(board[r][j] != 0) {
                    isUsed[board[r][j]] = true;
                }
            }
            //세로
            for (int i = 0; i < 9; i++) {
                if(board[i][c] != 0) {
                    isUsed[board[i][c]] = true;
                }
            }
            //구역
            int i_start = (r/3)*3;
            int i_end = i_start + 3;
            int j_start = (c/3)*3;
            int j_end = j_start + 3;
            for (int i = i_start; i < i_end; i++) {
                for (int j = j_start; j < j_end; j++) {
                    if(board[i][j] != 0) {
                        isUsed[board[i][j]] = true;
                    }
                }
            }

            for (int i = 1; i <= 9; i++) {
                if(!isUsed[i]) {
                    board[r][c] = i;
                    if(c < 8) {
                        sudoku(r, c+1, board);

                    } else { // c == 8
                        if(r < 8) {
                            sudoku(r+1, 0, board);
                        } else { // c == 8, r == 8
                            find = true;
                            printBoard(board);
                        }
                    }
                    board[r][c] = 0;
                }
            } //isUsed 쫙 돌기
        }

        else {

            if(c < 8) {
                sudoku(r, c+1, board);
            } else { // c == 8
                if(r < 8) {
                    sudoku(r+1, 0, board);
                } else { // c == 8, r == 8
                    find = true;
                    printBoard(board);
                }
            }
        }

    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

}
