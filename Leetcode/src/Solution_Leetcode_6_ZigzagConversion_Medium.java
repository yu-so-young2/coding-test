/**
 * 1. 아이디어
 * - 첫번째 시도) Length * numRows 크기의 배열로 직접 지그재그
 *      --> 생각해보니 배열 모양이 꼭 지그재그일 필요가 없잖아? 너무 메모리 낭비얌
 * - 두번째 시도) Queue[]로 각 row 구현
 * - 세번째 시도) Queue[] -> StringBuilder[]로 구현
 * - 네번째 시도) 문자열순서대로 지그재그 구현이 아닌, 애초에 지그재그 순서로 문자열 탐색
 *
 * 2. 시간복잡도
 * O(n)
 *
 * 3. 자료구조
 * int cycleLen = 2 * numRows - 2
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class Solution_Leetcode_6_ZigzagConversion_Medium {
    public static void main(String[] args) {
        String s = "AB";
        int numRows = 1;
        System.out.println(convert(s, numRows));
    }

//    public static String convert(String s, int numRows){
//        char[][] map = new char[s.length()][s.length()];
//
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//               map[i][j] = '0';
//            }
//        }
//
//        int r = 0, c = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//
//            System.out.println("r, c = "+r+", "+c);
//            map[r][c] = ch;
//
//            // r, c 이동
//            // c % (numRows-1) == 0 이라면 아래로 내려감
//            if(c % (numRows-1) == 0 && r < numRows-1) {
//                r++;
//            } else {
//                r--;
//                c++;
//            }
//
//            // 아니라면 우상단으로 이동
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//                char ch = map[i][j];
//                if(ch != '0')
//                    sb.append(ch);
//            }
//        }
//
//        return sb.toString();
//    }

//        public static String convert(String s, int numRows){
//            StringBuilder[] queues = new StringBuilder[numRows];
//            for (int i = 0; i < numRows; i++) {
//                queues[i] = new StringBuilder();
//            }
//
//            int idx = 0;
//            boolean up = true;
//
//            for (int i = 0; i < s.length(); i++) {
//                char ch = s.charAt(i);
//                queues[idx].append(ch);
//
//                // idx 이동
//                if(numRows != 1) {
//                    if(up) {
//                        if(idx == numRows-1) {
//                            idx--;
//                            up = !up;
//                        } else idx++;
//                    }
//                    else { // down
//                        if(idx == 0) {
//                            idx++;
//                            up = !up;
//                        } else idx--;
//                    }
//                }
//
//            }
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < numRows; i++) {
//                //while (!queues[i].isEmpty()) {
//                    sb.append(queues[i].toString());
//                //}
//            }
//
//            return sb.toString();
//        }

    public static String convert(String s, int numRows) {
        // 행의 수가 1이거나 s가 numRows보다 짧으면 지그재그 변환은 필요하지 않습니다.
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int cycleLen = 2 * numRows - 2;

        for (int row = 0; row < numRows; row++) {
            for (int j = 0; j + row < s.length(); j += cycleLen) {
                sb.append(s.charAt(j + row));
                if (row != 0 && row != numRows - 1 && j + cycleLen - row < s.length()) {
                    sb.append(s.charAt(j + cycleLen - row));
                }
            }
        }

        return sb.toString();
    }
}
