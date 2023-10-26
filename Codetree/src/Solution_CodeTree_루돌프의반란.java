/*

1. 아이디어
- 그냥 빡구현
// 1. 소 돌진
    // 1) 가장 가까운 사람 찾기 : 거리가 가장 가까우면서 r -> c 우선순위로 더 큰 사람
    // 2) 그 사람과 가까워지는 방향 찾기(8방 탐색)
    // 3) 그 방향으로 이동하기 : 만약 이동한 곳에 사람이 있다면 상호작용
// 2. 각 사람들 돌진
    // 1) 기절했거나 이미 게임에서 탈락했다면 무시
    // 2) 소에게 가까워지는 방향 찾기(4방 탐색, 상우하좌 우선순위) : 그 방향에 사람이 있다면 무시, 거리가 무조건 가까워져야함
    // 3) 그 방향으로 이동하기 : 만약 이동한 곳에 소가 있다면 상호작용
// 3. 남아있는 사람들 : 보드 순회하면서 남아있는 노드에게 1점씩 부여

// 이동 시 주의 : 아래 세 과정이 모두 일어나야 함
- 원래 있던 자리 null
- node.r, node.c 갱신
- board 새로운 자리에 node 넣기

2. 시간복잡도
?

3. 자료구조
- Node class : 산타(사람), 루돌프(소) - r,c,n, isOut, downUntil, score, isCow
- Node[][] board
- List<Node> personList : n을 기준으로 오름차순 정렬 필요
- Node cow


+) 추가 : 이전 코드에서 놓친 부분
1. 소 이동하기 위해 8방탐색할 때 최소 거리 갱신 안해줌 (minDistance = curDistance 를 안하고 넘어감)
2. 사람 이동시킬 때 null 처리 + 사람의 r, c만 갱신하고, 보드 새로운 위치에 사람 놓는 거 안함(board[ny][nx] = person 안함)
3. 사람 튕겨질 때 그 전 위치 null 안함
4. time <= M 해야 하는데 time < M 했음(마지막 턴 안돌고 끝나버림)

*/

import java.io.*;
import java.util.*;

public class Solution_CodeTree_루돌프의반란 {
    static int N, M, P, C, D;
    static int r, c, n, ny, nx;
    static List<Node> personList;
    static Node cow;
    static Node[][] board;

    public static class Node implements Comparable<Node> {
        int n, r, c, downUntil, score;
        boolean isOut, isCow;

        // Person contructor
        public Node(int n, int r, int c) {
            this.n = n;
            this.r = r;
            this.c = c;
            this.downUntil = 0;
            this.isOut = false;
            this.isCow = false;
            this.score = 0;
        }

        // Cow constructor
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.isCow = true;
        }

        @Override
        public int compareTo(Node o) {
            return this.n-o.n;
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new Node[N][N];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        cow = new Node(r, c);
        board[r][c] = cow;

        personList = new LinkedList<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            Node newPerson = new Node(n, r, c);
            personList.add(newPerson);
            board[r][c] = newPerson;
        }
        Collections.sort(personList);  // 오름차순 정렬

        simulation();

        for (int i = 0; i < P; i++) {
            Node person = personList.get(i);
            sb.append(person.score+" ");
        }

        System.out.println(sb);
    }

    static int[] c_dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] c_dx = {-1,  0,  1, 1, 1, 0, -1, -1};
    static int[] p_dy = {-1, 0, 1, 0};
    static int[] p_dx = {0, 1, 0, -1};

    public static void simulation() {
        int time = 1;
        boolean isAllOut = false;
        while(time <= M && !isAllOut) {
            // 1. 소 돌진
            // 1) 가장 가까운 사람 찾기 : 거리가 가장 가까우면서 r -> c 우선순위로 더 큰 사람
            // 2) 그 사람과 가까워지는 방향 찾기(8방 탐색)
            // 3) 그 방향으로 이동하기 : 만약 이동한 곳에 사람이 있다면 상호작용
            int minDistance = Integer.MAX_VALUE;
            int minR = -1, minC = -1;
            for (int i = 0; i < P; i++) {
                Node person = personList.get(i);
                if(person.isOut) continue;
                int curDistance = (int)Math.pow(cow.r-person.r,2)+(int)Math.pow(cow.c-person.c,2);
                if(curDistance < minDistance) {
                    minR = person.r;
                    minC = person.c;
                    minDistance = curDistance;
                } else if(curDistance == minDistance) {
                    if(person.r > minR) {
                        minR = person.r;
                        minC = person.c;
                    } else if (person.r == minR){
                        if(person.c > minC) {
                            minC = person.c;
                        }
                    }
                }
            }
            // minR, minC
            int minDir = -1;
            minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                ny = cow.r + c_dy[i];
                nx = cow.c + c_dx[i];
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                int curDistance = (int)Math.pow(ny-minR,2)+(int)Math.pow(nx-minC,2);
                if(curDistance < minDistance) {
                    minDistance = curDistance;
                    minDir = i;
                }
            }

            // 소가 이동할 곳
            ny = cow.r+c_dy[minDir];
            nx = cow.c+c_dx[minDir];


            if(board[ny][nx] == null) { // 사람이 없음 -> 소 이동
                board[cow.r][cow.c] = null;
                cow.r = ny;
                cow.c = nx;
                board[ny][nx] = cow;
            } else { // 사람이 있음 -> 상호작용
                // 소는 이동
                board[cow.r][cow.c] = null;
                cow.r = ny;
                cow.c = nx;

                // 사람 튕겨짐
                Node person = board[ny][nx];
                board[ny][nx] = cow;
                person.downUntil = time+1;
                person.score += C;

                ny = ny + c_dy[minDir]*C;
                nx = nx + c_dx[minDir]*C;

                // 튕겨진 곳이 밖이면 아웃
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    person.isOut = true;
                }
                // 튕겨진 곳에 사람없으면 안착
                else if(board[ny][nx] == null) {
                    person.r = ny;
                    person.c = nx;
                    board[ny][nx] = person;
                }
                // 사람 튕겨진 곳에 사람있으면 한칸씩 밀리기
                else { // board[ny][nx] != null
                    Node now = person; // 자리를 뺏긴 아이
                    Node next = board[ny][nx]; // 그 아이가 가야 할 새로운 곳

                    while(now != null) {
                        //갈 곳이 없어진 now 를 next로 보내기
                        now.r = ny;
                        now.c = nx;
                        board[ny][nx] = now;


                        // 갈 곳이 없어진 next의 다음 곳을 찾기
                        ny = ny + c_dy[minDir];
                        nx = nx + c_dx[minDir];
                        // 튕겨진 곳이 밖이면 아웃
                        if(ny < 0 || nx < 0 || ny >= N || nx >= N) {
                            if(next != null) {
                                next.r = ny;
                                next.c = nx;
                                next.isOut = true;
                            }
                            break;
                        }

                        now = next;
                        next = board[ny][nx];
                    }

                }
            } // 소 -> 사람 상호작용 끝


            // 2. 각 사람들 돌진
            // 1) 기절했거나 이미 게임에서 탈락했다면 무시
            // 2) 소에게 가까워지는 방향 찾기(4방 탐색, 상우하좌 우선순위) : 그 방향에 사람이 있다면 무시, 거리가 무조건 가까워져야함
            // 3) 그 방향으로 이동하기 : 만약 이동한 곳에 소가 있다면 상호작용
            for (int i = 0; i < P; i++) {
                Node person = personList.get(i);
                if(person.isOut || person.downUntil >= time) continue;
                int curDistance = (int)Math.pow(person.r-cow.r,2)+(int)Math.pow(person.c-cow.c,2);
                minDir = -1;

                for (int j = 0; j < 4; j++) {
                    ny = person.r + p_dy[j];
                    nx = person.c + p_dx[j];
                    if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                    if(board[ny][nx] != null && !board[ny][nx].isCow) continue;
                    int curDirDistance = (int)Math.pow(ny-cow.r,2)+(int)Math.pow(nx-cow.c,2);
                    if(curDirDistance < curDistance) {
                        curDistance = curDirDistance;
                        minDir = j;
                    }
                }


                // 가까워질 수 없다면 움직이지 않기
                if(minDir == -1) continue;


                // 3) 그 방향으로 이동하기
                // 사람이 이동할 곳
                board[person.r][person.c] = null;
                ny = person.r+p_dy[minDir];
                nx = person.c+p_dx[minDir];

                if(board[ny][nx] == null) { // 소가 없음 -> 그냥 이동
                    board[person.r][person.c] = null;
                    person.r = ny;
                    person.c = nx;
                    board[ny][nx] = person;
                } else { // 소가 있음 -> 상호작용
                    // 사람 튕겨짐
                    // 사람 튕겨진 곳에 사람있으면 한칸씩 밀리기

                    int reverseDir = -1;
                    if (minDir == 0) reverseDir = 2;
                    else if (minDir == 1) reverseDir = 3;
                    else if (minDir == 2) reverseDir = 0;
                    else if (minDir == 3) reverseDir = 1;

                    // 사람 튕겨짐
                    person.downUntil = time + 1;
                    person.score += D;
                    ny = ny + p_dy[reverseDir] * D;
                    nx = nx + p_dx[reverseDir] * D;


                    // 튕겨진 곳이 밖이면 아웃
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                        person.isOut = true;
                    }
                    // 튕겨진 곳에 사람없으면 안착
                    else if (board[ny][nx] == null) {
                        person.r = ny;
                        person.c = nx;
                        board[ny][nx] = person;
                    }
                    // 사람 튕겨진 곳에 사람있으면 한칸씩 밀리기
                    else { // board[ny][nx] != null
                        Node now = person;
                        Node next = board[ny][nx];

                        while (now != null) { // 다음 갈 곳이 비어있는 곳까지
                            //갈 곳이 없어진 now 를 next로 보내기
                            now.r = ny;
                            now.c = nx;
                            board[ny][nx] = now;


                            // 갈 곳이 없어진 next의 다음 곳을 찾기
                            ny = ny + p_dy[reverseDir];
                            nx = nx + p_dx[reverseDir];
                            // 튕겨진 곳이 밖이면 아웃
                            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                                if(next != null) {
                                    next.r = ny;
                                    next.c = nx;
                                    next.isOut = true;
                                }
                                break;
                            }

                            now = next;
                            next = board[ny][nx];

                        }


                    } // 사람 -> 소 상호작용 끝
                }
            }


            // 3. 남아있는 사람들 : 보드 순회하면서 남아있는 노드에게 1점씩 부여
            isAllOut = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Node node = board[i][j];

                    if(node == null || node.isCow == true) continue; // 아무도 없거나 그게 소라면 무시
                    node.score += 1; // 1점 부여
                    isAllOut = false; // 남아 있는 사람 있음
                }
            }

            time++;
        }
    }

}