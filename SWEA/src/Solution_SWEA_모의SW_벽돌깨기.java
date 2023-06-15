import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_모의SW_벽돌깨기 {
    public static int T, N, W, H, min;
    public boolean visited[];

    public static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[][] map;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            //input
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //simulation
            min = Integer.MAX_VALUE;

            //W^N의 중복순열 생성하여 시뮬레이션, min 갱신
            perm(N, map);


            //print result
            min = min == Integer.MAX_VALUE? 0:min;
            sb.append("#"+tc+" "+min+"\n");

        } //test cases

        System.out.println(sb);
    }//main

    public static void perm (int marble, int[][] map) {
        //permutation end, update min num of blocks
        if(marble == 0){

            int blocks = countBlocks(map);
            min = Math.min(min, blocks);
            return;
        }

        //중복순열 생성
        boolean tops[] = isTopEmpty(map);


        for (int j = 0; j < W; j++) {
            if(tops[j]) {

                perm(marble-1, bomb(j, map));
            }
        }
        return;
    } //dfs

    //j열에 구슬을 떨어뜨렸을 때 시뮬레이션 결과 출력
    public static int[][] bomb(int j, int[][] map) {
        int[][] result = copy(map);

        Queue<Node> q = new LinkedList<>();

        //j열 구슬 투하
        for (int i = 0; i < H; i++) {
            if(result[i][j] != 0) {
                q.add(new Node(i, j));
                break;
            }
        }

        //해당 Block과 부술 수 있는 모든 block bfs로 부수기
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int range = result[r][c];

            //해당 블록 삭제
            result[r][c] = 0;

            //상
            for (int i = r; i > r-range; i--) {
                //인덱스 범위에 있고 0이 아니면
                if(i >= 0 && result[i][c] != 0) {
                    q.add(new Node(i, c));
                }
            }
            //하
            for (int i = r; i < r+range; i++) {
                //인덱스 범위에 있고 0이 아니면
                if(i < H && result[i][c] != 0) {
                    q.add(new Node(i, c));
                }
            }
            //좌
            for (int i = c; i > c-range; i--) {
                //인덱스 범위에 있고 0이 아니면
                if(i >= 0 && result[r][i] != 0) {
                    q.add(new Node(r, i));
                }
            }
            //우
            for (int i = c; i < c+range; i++) {
                //인덱스 범위에 있고 0이 아니면
                if(i < W && result[r][i] != 0) {
                    q.add(new Node(r, i));
                }
            }
        }

        //남아있는 블록들 밑으로 내리기
        //k번째 열 정리
        for (int k = 0; k < W; k++) {
            int bottom = H-1;
            for (int i = H-1; i >= 0; i--) {
                if (result[i][k] != 0) {
                    if (bottom != i) {
                        result[bottom][k] = result[i][k];
                        result[i][k] = 0;
                    }
                    bottom--;
                }
            }
        }

        return result;
    }

    //2차원 배열 복사
    public static int[][] copy(int origin[][]) {
        int dest[][] = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                dest[i][j] = origin[i][j];
            }
        }
        return dest;
    }

    //주어진 맵의 가장 위에 있는 블록의 유무를 세는 함수
    public static boolean[] isTopEmpty(int map[][]) {
        boolean visited[] = new boolean[W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(visited[j]) continue;
                if(map[i][j]!=0) {
                    visited[j] = true;
                }
            }
        }
        return visited;
    }

    //주어진 맵 안의 블록 수를 세는 함수
    public static int countBlocks(int map[][]) {
        int blocks = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] != 0) blocks++;
            }
        }
        return blocks;
    }
}
