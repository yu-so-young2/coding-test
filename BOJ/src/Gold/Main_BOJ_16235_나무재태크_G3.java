package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16235_나무재태크_G3 {
    static int N, M, K, ans;
    static int[][] ground, deadTree, additional;
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static PriorityQueue<Tree> treeList;



    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age-o.age;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, M, K 값
        N = Integer.parseInt(st.nextToken()); // 땅의 크기
        M = Integer.parseInt(st.nextToken()); // 나무 개수
        K = Integer.parseInt(st.nextToken()); // 제한기간

        // A[][] 값
        additional = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                additional[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무의 정보
        treeList = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(r, c, age));
        }

        // K년만큼 진행
        ground = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] = 5;
            }
        }
        for (int year = 0; year < K; year++) {
            deadTree = new int[N][N];
            // 봄
            // 어린 순서대로 정렬
            PriorityQueue<Tree> filteredTreeList = new PriorityQueue<>();
            // 나무 꺼내고 자신의 나이만큼 해당 칸의 양분을 먹음
            // 먹을 수 있으면 -> 양분 먹고, 나이 +1 증가
            // 먹을 수 없다면 -> 사라지고 양분 (나이/2)만큼 추가
            int size = treeList.size();
            for (int k = 0; k < size; k++) {
                Tree cur = treeList.poll();
                // 양분 먹을 수 있는지 확인
                if(cur.age <= ground[cur.r][cur.c]) {
                    // 먹을 수 있다면 양분 먹고 나이 증가
                    ground[cur.r][cur.c] -= cur.age;
                    cur.age++;
                    filteredTreeList.add(cur);
                } else {
                    // 먹을 수 없다면
                    deadTree[cur.r][cur.c] += cur.age/2;
                }
            }
            treeList = filteredTreeList;

            // 여름
            // 죽은 나무로 인한 양분 추가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ground[i][j] += deadTree[i][j];
                }
            }

            // 가을
            // 번식
            PriorityQueue<Tree> generatedTreeList = new PriorityQueue<>();
            size = treeList.size();
            for(int k = 0; k < size; k++) {
                Tree cur = treeList.poll();
                if(cur.age%5==0) {
                    for (int i = 0; i < 8; i++) {
                        int ny = cur.r+dy[i];
                        int nx = cur.c+dx[i];
                        if(ny<0||nx<0||ny>=N||nx>=N) continue;
                        generatedTreeList.add(new Tree(ny,nx,1));
                    }
                }
                generatedTreeList.add(cur);

            }
            treeList = generatedTreeList;



            // 겨울
            // 땅에 양분 추가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ground[i][j] += additional[i][j];
                }
            }
        }

        // 살아있는 나무의 개수
        ans = treeList.size();
        System.out.println(ans);
    }
}
