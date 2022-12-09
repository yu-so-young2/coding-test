import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_3025_돌던지기_P4 {
    public static class Node {
        int r, c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" +
                    r +
                    ", " + c +
                    ')';
        }
    }

    public static void main(String[] args) throws IOException {
        //입력받기
        //R C
        //맵 만들고 받아오기
        //시작열에 따라 경로 저장할 스택 배열
        //Stack[] route = new Stack[C];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        Stack<Node>[] route = new Stack[C];
        for (int i = 0; i < C; i++) {
            route[i] = new Stack<>();
        }
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        //  시뮬레이션
        /*
            //startX 인풋으로 받기
            //시작 노드 찾기
            해당 열 route[c] 스택 확인하기
            - 비어있으면 (0,c)
            - 안비어있으면 하나씩 빼면서 유효한지 확인하기 -> 유효한 애 찾으면 걔가 시작 노드
            //시작노드 기준으로 내려가면서 다음노드로 이동(이동 시 이전 노드를 route[startX]스택에 추가)
            //도착하면 맵에 돌 표시
         */


        //돌 던지기 횟수 N
        //for(N)
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int startX = Integer.parseInt(br.readLine())-1;

            //시작노드찾기
            Node node = new Node(0, startX);
            while(!route[startX].isEmpty()) {
                Node n = route[startX].pop();
                if(map[n.r][n.c]=='.') {
                    node = n;
                    break;
                }
            }

            //더이상 갈 수 없을 때까지 다음 노드로 이동
            while(true) {
                int r = node.r;
                int c = node.c;

                //check 1) 비어있다면 계속 내려갈 것
                if(r+1 < R && map[r+1][c] == '.') {
                    route[startX].push(node);
                    node = new Node(r+1, c);
                    continue;
                }

                //check 2) 아래칸이 굳은 화산탄이라면
                if(r+1 < R && map[r+1][c] == 'O') {
                    //왼쪽 확인 후 갈 수 있다면 왼쪽으로 열 변경
                    if(c-1 >= 0 && map[r][c-1] == '.' && map[r+1][c-1]=='.') {
                        route[startX].push(node);
                        node = new Node(r+1, c-1);
                        continue;
                    }
                    //오른쪽 확인 후 갈 수 있다면 오른쪽으로 열 변경
                    else if(c+1 < C && map[r][c+1] == '.' && map[r+1][c+1]=='.') {
                        route[startX].push(node);
                        node = new Node(r+1, c+1);
                        continue;
                    }
                }
                //check 3) 만약 더이상 갈 수 없다면(땅이거나, 장애물이거나, 왼쪽/오른쪽으로 변경 불가능 시)
                map[r][c] = 'O';
                break;

            } //내려가기
        } //각 돌 시뮬레이션

        //맵 출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}