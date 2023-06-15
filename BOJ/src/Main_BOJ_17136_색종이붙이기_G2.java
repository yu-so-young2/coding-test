import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
BOJ 17136
색종이 붙이기
 */
public class Main_BOJ_17136_색종이붙이기_G2 {
    public static int minValue;

    public static void main(String[] args) throws IOException {
        //입력받기
        //10*10 배열
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[][] paper = new boolean[10][10]; //메모리 아끼기 위해 boolean으로 선언(0, 1 구분)

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                //0이면 false, 1이면 true
                paper[i][j] = Integer.parseInt(st.nextToken())==0?false:true;
            }
        }


        //백트래킹 시뮬레이션(재귀 구현)
        //minValue 초기화(Integer.MAX_VALUE)
        //재귀함수 매개변수 전달: 색종이 덮음으로써 바뀐 종이 상태, 현 상태에서 사용한 각 색종이 개수 int[]
        //현 상태에서 사용할 수 있는 색종이가 더이상 없으면 리턴
        //완료하면 minValue 갱신
        minValue = Integer.MAX_VALUE;
        int[] used = new int[5]; //사용한 색종이 개수
        dfs(paper, used);


        //불가능 경우 보정
        //minValue==MAX?-1:min; ~~
        minValue = minValue==Integer.MAX_VALUE?-1:minValue;
        System.out.println(minValue);
    }

    private static void dfs(boolean[][] paper, int[] used) {

        //아직 안덮힌(이번 함수에서 덮어야 할) 종이 위치 저장
        int targetY = -1, targetX = -1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(targetY==-1 && paper[i][j]) {
                    targetY = i;
                    targetX = j;
                }
            }
        }

        //더이상 덮어야 할 종이가 없다면(덮기 완료!) 사용한 종이 숫자 더하기
        if(targetY==-1) {
            int sum = 0;
            for (int i = 0; i < used.length; i++) {
                sum += used[i];
            }
            //minValue 갱신
            minValue = Math.min(sum, minValue);
//            System.out.println("END!!!!!!!  " + sum + " "+minValue);
            return;
        }

        //가능한 모든 크기의 색종이에 대하여 dfs 호출
        for (int x = 0; x < 5; x++) { //모든 크기의 색종이에 대하여
//            System.out.println("---------------");
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print((paper[i][j]?1:0)+" ");
//                }
//                System.out.println();
//            }
//            System.out.print("I used ");
//            for (int i = 0; i < used.length; i++) {
//                System.out.print(used[i]+" ");
//            }
//            System.out.println();
//            System.out.println("check "+(x+1)+" at ("+targetY+", "+targetX+")");
            //해당 사이즈 색종이 남아있는지 확인, 5장 다 썼으면 넘어가
            if(used[x]==5) continue;

            //해당 크기의 색종이가 덮을 수 있는지 여부 확인
            boolean possible = true;
   find:         for (int i = 0; i < x+1; i++) {
                for (int j = 0; j < x+1; j++) {
                    if(targetY+i >= 10 || targetX+j >= 10) {
                        possible = false;
                        break find;
                    }

//                    System.out.println("("+(targetY+i)+","+(targetX+j)+") is "+(paper[targetY+i][targetX+j]?1:0));
                    if(!paper[targetY+i][targetX+j]) {
                        possible = false;
                        break find;
                    }
                }
            }
//            System.out.println(possible);
            //덮을 수 있으면 해당 크기의 색종이로 덮고 dfs 호출
            if(possible) {
//                System.out.println("I put "+x+" size paper at (" +targetY+", "+targetX+")");
                //덮기
                //2차원 종이 배열 복사 후 작업 시작
                boolean[][] newPaper = new boolean[10][10];
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        newPaper[i][j] = paper[i][j];
                    }
                }

                for (int i = 0; i < x+1; i++) {
                    for (int j = 0; j < x+1; j++) {
                        newPaper[targetY+i][targetX+j] = false;
                    }
                }

                //색종이 사용 처리
                int[] newUsed = new int[5];
                for (int i = 0; i < 5; i++) {
                    newUsed[i] = used[i];
                }
                newUsed[x]++;

                //dfs 호출
                dfs(newPaper, newUsed);

            } else { //덮을 수 없다면 더 큰 색종이로도 덮을 수 없을 것임
                break;
            }

        } //for every size of paper

    }
}
