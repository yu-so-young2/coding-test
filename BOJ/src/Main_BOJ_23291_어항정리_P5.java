import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_23291_어항정리_P5 {

    static int N, K, bowls[][];
    static int max, min, step;
    static int boxHeight, boxWidth, curLen, boxStart;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bowls = new int[25][N];
        st = new StringTokenizer(br.readLine());
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        step = 0;
        for (int i = 0; i < N; i++) {
            bowls[24][i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, bowls[24][i]);
            min = Math.min(min, bowls[24][i]);
        }


        while(max-min > K) {
            step++;
//            System.out.println("step "+step+"===========");
            addFish();
            stack1();
            flatten();
            serialize();
            stack2();
            flatten();
            serialize();
        }

        System.out.print(step);
    }

    private static void addFish() {
        // bowls[][] 는 24행에 정렬되어있다고 가정
        // min 값도 갱신되어 있다고 가정
        for (int i = 0; i < N; i++) {
            if(bowls[24][i] == min) {
                bowls[24][i]++;
            }
        }

//        System.out.println("addFish============");
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(bowls[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void stack1() {
        boxHeight = 1; // 다음에 올릴 박스 높이
        boxWidth = 1; // 다음에 올릴 박스 너비
        curLen = N; // 현재 1층에 있는 박스 수
        boxStart = 0;

        int nextLen = curLen-boxWidth; // 올리고 나면 남는 1층 박스 수

        while(nextLen >= boxHeight) { // 박스 올릴 시 남을 1층이 더 길 경우 올리기
            // 박스 올리기
            for (int j = 0; j < boxWidth; j++) {
                for (int i = 0; i < boxHeight; i++) {
                    int nx = boxStart+boxWidth+i;
                    int ny = 24-boxWidth+j;
                    bowls[ny][nx] = bowls[24-i][boxStart+j];
                    bowls[24-i][boxStart+j] = 0;
                }
            }

            // 각종 변수 갱신

            boxStart += boxWidth;
            curLen = nextLen; // 현재 1층에 있는 박스 수 갱신
            if(boxWidth==boxHeight) { // 올릴 박스가 정사각형이면 다음엔 1층 높아질 것임
                boxHeight++;
            } else{
                boxWidth++; // 정사각형 아니면 width 늘리기
            }
            nextLen = curLen-boxWidth;

        }

//        System.out.println("stack1============");
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(bowls[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void stack2() {
        // 2번 올리기
        for(int j = 0; j < N/2; j++) {
            bowls[23][N-1-j] = bowls[24][j];
            bowls[24][j] = 0;
        }

        boxStart = N/2;

        for (int j = 0; j < N/4; j++) {
            for (int i = 0; i < 2; i++) {
                bowls[21+i][N-1-j] = bowls[24-i][boxStart+j];
                bowls[24-i][boxStart+j] = 0;
            }
        }

        boxHeight = 4; // 다음에 올릴 박스 높이
        boxWidth = N/4; // 다음에 올릴 박스 너비
        boxStart = N-N/4;


//        System.out.println("stack2============");
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(bowls[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void flatten() {
        int[][] change = new int[25][N];
        boolean[][] visited = new boolean[25][N];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < N; j++) {
                if(bowls[i][j] != 0) { // 물고기가 있다면
                    visited[i][j] = true;
                    // 상하좌우 확인
                    for (int k = 0; k < 4; k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||nx>=N||ny>=25) continue; // OutOfIndexException
                        if(bowls[ny][nx] == 0) continue;
                        if(visited[ny][nx]) continue;


                        int diff = (bowls[i][j] - bowls[ny][nx])/5;
                        if(Math.abs(diff) > 0) { // 차이만큼 diff 저장
                            change[i][j] -= diff;
                            change[ny][nx] += diff;
                        }
                    }
                } // if
            }
        } //for

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < N; j++) {
                bowls[i][j] += change[i][j];
            }
        }

//        System.out.println("flatten==========");
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(bowls[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void serialize() {
        int col = 0;
        for (int j = 0; j < boxWidth; j++) {
            for (int i = 0; i < boxHeight; i++) {
                bowls[24][col] = bowls[24-i][boxStart+j];
                bowls[24-i][boxStart+j] = 0;
                col++;
            }
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            max = Math.max(max, bowls[24][j]);
            min = Math.min(min, bowls[24][j]);
        }

//        System.out.println("serialize=========");
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(bowls[i][j]+" ");
//            }
//            System.out.println();
//        }
    }


}