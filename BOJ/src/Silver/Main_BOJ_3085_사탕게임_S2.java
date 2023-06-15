package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 사탕게임
 */

public class Main_BOJ_3085_사탕게임_S2 {

    final static int C = 0; //빨강
    final static int P = 1; //파랑
    final static int Z = 2; //초록
    final static int Y = 3; //노랑

    public static int color(char c) {
        int result=0;
        switch(c) {
            case 'C': result = C; break;
            case 'P': result = P; break;
            case 'Z': result = Z; break;
            case 'Y': result = Y; break;
        }

        return result;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] board = new char[n][n];
        String line;

        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }

//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board.length; j++) {
//                System.out.print(board[i][j]);
//            }
//            System.out.println();
//        }

        int maxlen = 0;
        int dp_0[];
        int dp_1[][];
        int dp_2[];

//        System.out.println("===============");
//        System.out.println("가로줄 탐색입니다");
//        System.out.println("===============");
        //가로줄 확인
        for (int i = 0; i < n; i++) {
//            System.out.println("<<<"+i+"번 가로줄>>>");
            dp_0 = new int[n];
            dp_1 = new int[n][4]; //현 상황 각 색깔 누적 값 저장

            //해당 줄에서 바꾸지 않았을 때 연속 문자 수 탐색
            for (int j = 0; j < n; j++) {
                //dp_0[j][0] 갱신
                if(j==0) {
                    dp_0[j] = 1;
                    continue;
                }

                if(board[i][j]==board[i][j-1]) { //같으면 +1
                    dp_0[j] = dp_0[j-1]+1;
                } else { //다르면 1
                    dp_0[j] = 1;
                }
            } //각 줄 연속 문자 수 탐색 끝

//            for (int j = 0; j < dp_0.length; j++) {
//                System.out.print(dp_0[j]+" ");
//            }
//            System.out.println();


            //dp 탐색 실행

            //1. 위와 변경 - i==0 이면 안하기
            if(i != 0) {
//                System.out.println("위와 변경합니다");
                for (int j = 0; j < n; j++) {
//					System.out.println("my color is "+ color(board[i][j]));
                    //dp_1[][] 갱신
                    if(j==0) {
                        dp_1[j][color(board[i-1][j])] = 1;
                        continue;
                    }


                    //너 변경 시(나 그대로, 앞은 dp_1[j][4] 전부 확인)
                    //나랑 같은 연속된 애가 있다면
                    if(dp_1[j-1][color(board[i][j])] > 0) {
                        dp_1[j][color(board[i][j])] = dp_1[j-1][color(board[i][j])]+1;
                    }

                    //나 변경 시(나 바꾸고 앞은 그대로)

                    //바꾸는 애랑 색 똑같으면 탐색 안함
                    if(board[i][j] != board[i-1][j]) {
                        //바뀐 나랑 앞의 문자가 같으면 origin 에서 가져오기
                        if(board[i-1][j] == board[i][j-1]) {
                            dp_1[j][color(board[i-1][j])] = dp_0[j-1]+1;
                        }
                        //바뀐 나랑 앞의 문자가 다르면 1 새로시작
                        else {
                            dp_1[j][color(board[i-1][j])] = 1;
                        }

                    }

                    maxlen = Math.max(maxlen, Math.max(dp_0[j], Math.max(dp_1[j][C], Math.max(dp_1[j][P], Math.max(dp_1[j][Z], dp_1[j][Y])))));


                }
//                for (int j = 0; j < 4; j++) {
//                    for (int j2 = 0; j2 < dp_1.length; j2++) {
//                        System.out.print(dp_1[j2][j]+" ");
//                    }
//                    System.out.println();
//                }

            }


            //2. 아래와 변경 - i==n-1 이면 안하기
            if(i != n-1) {
//                System.out.println("아래와 변경합니다");
                for (int j = 0; j < n; j++) {
//					System.out.println("my color is "+ color(board[i][j]));
                    //dp_1[][] 갱신
                    if(j==0) {
                        dp_1[j][color(board[i+1][j])] = 1;
                        continue;
                    }

                    //너 변경 시(나 그대로, 앞은 dp_1[j][4] 전부 확인)
                    //나랑 같은 연속된 애가 있다면
                    if(dp_1[j-1][color(board[i][j])] > 0) {
                        dp_1[j][color(board[i][j])] = dp_1[j-1][color(board[i][j])]+1;
                    }


                    //나 변경 시(나 바꾸고 앞은 그대로)
                    //바꾸는 애랑 색 똑같으면 탐색 안함
                    if(board[i][j] != board[i+1][j]) {

                        //바뀐 나랑 앞의 문자가 같으면 origin 에서 가져오기
                        if(board[i+1][j] == board[i][j-1]) {
                            dp_1[j][color(board[i+1][j])] = dp_0[j-1]+1;
                        }
                        //바뀐 나랑 앞의 문자가 다르면 1 새로시작
                        else {
                            dp_1[j][color(board[i+1][j])] = 1;
                        }
                    }




                    maxlen = Math.max(maxlen, Math.max(dp_0[j], Math.max(dp_1[j][C], Math.max(dp_1[j][P], Math.max(dp_1[j][Z], dp_1[j][Y])))));

                }


//                for (int j = 0; j < 4; j++) {
//                    for (int j2 = 0; j2 < dp_1.length; j2++) {
//                        System.out.print(dp_1[j2][j]+" ");
//                    }
//                    System.out.println();
//                }
            }


            //오른쪽과 변경 (n-1까지만)
            dp_2 = new int[n];
//            System.out.println("오른쪽과 변경합니다");
            for (int j = 0; j < n-1; j++) {
//                System.out.println("my color is "+ color(board[i][j]));
                //dp_2[] 갱신
                if(j==0) {
                    dp_2[j] = 1;
                    continue;
                }

                //뒤랑 바꾸고 앞과 비교(다르면 1, 같으면 앞+1)
                if(board[i][j-1] == board[i][j+1]) {
                    //뒤랑 앞의 문자가 같으면 origin+1
                    dp_2[j] = dp_0[j-1]+1;
                } else {
                    dp_2[j] = 1;
                }
                maxlen = Math.max(maxlen, Math.max(dp_0[j],dp_2[j]));
            }
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp_2[j]+" ");
//            }
//            System.out.println();


        }

//        System.out.println("===============");
//        System.out.println("세로줄 탐색입니다");
//        System.out.println("===============");
        //세로줄 확인
        for (int j = 0; j < n; j++) {
//            System.out.println("<<<"+j+"번 세로줄>>>");
            dp_0 = new int[n];
            dp_1 = new int[n][4]; //현 상황 각 색깔 누적 값 저장

            //해당 줄에서 바꾸지 않았을 때 연속 문자 수 탐색
            for (int i = 0; i < n; i++) {
                //dp_0[i][0] 갱신
                if(i==0) {
                    dp_0[i] = 1;
                    continue;
                }

                if(board[i][j]==board[i-1][j]) { //같으면 +1
                    dp_0[i] = dp_0[i-1]+1;
                } else { //다르면 1
                    dp_0[i] = 1;
                }

            }//각 줄 연속 문자 수 탐색 끝


            //dp 탐색 실행

            //1. 왼쪽과 변경 - j==0 이면 안하기
            if(j != 0) {
//                System.out.println("왼쪽과 변경합니다");
                for (int i = 0; i < n; i++) {
//					System.out.println("my color is "+ color(board[i][j]));
                    //dp[i][1] 갱신
                    if(i==0) {
                        dp_1[i][color(board[i][j-1])] = 1;
                        continue;
                    }

                    //너 변경 시(나 그대로, 앞은 dp_1[j][4] 전부 확인)
                    //나랑 같은 연속된 애가 있다면
                    if(dp_1[i-1][color(board[i][j])] > 0) {
                        dp_1[i][color(board[i][j])] = dp_1[i-1][color(board[i][j])]+1;
                    }

                    //나 변경 시(나 바꾸고 앞은 그대로)
                    //바꾸는 애랑 색 똑같으면 탐색 안함
                    if(board[i][j] != board[i][j-1]) {
                        //바뀐 나랑 앞의 문자가 같으면 origin 에서 가져오기
                        if(board[i][j-1] == board[i-1][j]) {
                            dp_1[i][color(board[i][j-1])] = dp_0[i-1]+1;
                        }
                        //바뀐 나랑 앞의 문자가 다르면 1 새로시작
                        else {
                            dp_1[i][color(board[i][j-1])] = 1;
                        }
                    }

                    maxlen = Math.max(maxlen, Math.max(dp_0[i], Math.max(dp_1[i][C], Math.max(dp_1[i][P], Math.max(dp_1[i][Z], dp_1[i][Y])))));

                }

//                for (int i = 0; i < 4; i++) {
//                    for (int j2 = 0; j2 < dp_1.length; j2++) {
//                        System.out.print(dp_1[j2][i]+" ");
//                    }
//                    System.out.println();
//                }


            } //if


            //2. 오른쪽과 변경 - j==n-1 이면 안하기
            if(j != n-1) {
//                System.out.println("오른쪽과 변경합니다");
                for (int i = 0; i < n; i++) {
//					System.out.println("my color is "+ color(board[i][j]));
                    //dp[i][1] 갱신
                    if(i==0) {
                        dp_1[i][color(board[i][j])] = 1;
                        continue;
                    }

                    //너 변경 시(나 그대로, 앞은 dp_1[j][4] 전부 확인)
                    //나랑 같은 연속된 애가 있다면
                    if(dp_1[i-1][color(board[i][j])] > 0) {
                        dp_1[i][color(board[i][j])] = dp_1[i-1][color(board[i][j])]+1;
                    }


                    //나 변경 시(나 바꾸고 앞은 그대로)
                    //바꾸는 애랑 색 똑같으면 탐색 안함
                    if(board[i][j] != board[i][j+1]) {
                        //바뀐 나랑 앞의 문자가 같으면 origin 에서 가져오기
                        if(board[i][j+1] == board[i-1][j]) {
                            dp_1[i][color(board[i][j+1])] = dp_0[i-1]+1;
                        }
                        //바뀐 나랑 앞의 문자가 다르면 1 새로시작
                        else {
                            dp_1[i][color(board[i][j+1])] = 1;
                        }

                    }

                    maxlen = Math.max(maxlen, Math.max(dp_0[i], Math.max(dp_1[i][C], Math.max(dp_1[i][P], Math.max(dp_1[i][Z], dp_1[i][Y])))));

                }

//                for (int i = 0; i < 4; i++) {
//                    for (int j2 = 0; j2 < dp_1.length; j2++) {
//                        System.out.print(dp_1[j2][i]+" ");
//                    }
//                    System.out.println();
//                }

                //아래쪽과 변경 (n-1까지만)
                dp_2 = new int[n];
//                System.out.println("아래쪽과 변경합니다");
                for (int i = 0; i < n-1; i++) {
//                System.out.println("my color is "+ color(board[i][j]));
                    //dp_2[] 갱신
                    if(i==0) {
                        dp_2[i] = 1;
                        continue;
                    }

                    //뒤랑 바꾸고 앞과 비교(다르면 1, 같으면 앞+1)
                    if(board[i-1][j] == board[i+1][j]) {
                        //뒤랑 앞의 문자가 같으면 origin+1
                        dp_2[i] = dp_0[i-1]+1;
                    } else {
                        dp_2[i] = 1;
                    }
                    maxlen = Math.max(maxlen, Math.max(dp_0[i],dp_2[i]));
                }

//                for (int i = 0; i < n; i++) {
//                    System.out.print(dp_2[i]+" ");
//                }
//                System.out.println();
            }
        }

        System.out.println(maxlen);
    }
}
