import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.LinkedList;

public class Main_BOJ_2847_알고스팟어_P4 {
    static int N, indegree[];
    static boolean[] alpha;
    static String[] dictionary;
    static Queue<Integer> queue;
    static HashSet<Integer>[] edges;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 단어의 개수
        alpha = new boolean[27]; // 등장한 알파벳
        dictionary = new String[N];

        // 입력
        // 사전에 모든 단어 기록 및 등장 알파벳 저장
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            dictionary[i] = word; // 사전에 추가

            // 등장하는 알파벳 저장
            for (int j = 0; j < word.length(); j++) {
                int c = word.charAt(j)-'a';
                if(!alpha[c]) {
                    alpha[c] = true;
                }
            }
        }

        // 필요 자료구조 준비
        // 1. 모든 간선 저장할 HashSet
        edges = new HashSet[27];
        for (int i = 0; i < 27; i++) {
            edges[i] = new HashSet<>();
        }
        // 2. 각 노드의 차수 저장
        indegree = new int[27];

        // 알파벳 간 위상 입력
        // 두 번째 단어부터 탐색
        // 이전 단어와 가장 처음 달라지는 문자 인덱스 찾아서 간선 저장
        for (int i = 1; i < N; i++) {
            String cur = dictionary[i]; // 현재 단어
            String prev = dictionary[i-1]; // 이전 단어

            int idx = 0;
            while (idx < prev.length()) {
                // 순서가 잘못 들어온 경우 "!" 출력하고 종료
                // "abcd" -> "abc" 등
                if (idx == cur.length()) {
                    System.out.println("!");
                    return;
                }

                // 다른 알파벳이라면 stop
                if (cur.charAt(idx) != prev.charAt(idx)) {
                    break;
                }

                // 같은 알파벳이라면 continue
                idx++;
            }

            // 만약 ab ab 라면 간선 생성하지 말고 넘어가
            if(idx == cur.length()) continue;
            // 만약 ab abc 라면 간선 생성하지 말고 넘어가
            if(idx == prev.length()) continue;

            // 간선 추가 및 차수 업데이트
            // prev.charAt(idx) -> cur.charAt(idx) edge 추가

            int src = prev.charAt(idx)-'a';
            int dest = cur.charAt(idx)-'a';
            if(!edges[src].contains(dest)) {
                // 역방향 확인
                if(edges[dest].contains(src)) {
                    System.out.println("!");
                    return;
                }

                edges[src].add(dest); // 간선 추가
                indegree[dest]++; // 차수 업데이트
            }
        }

        // topological sort
        // queue를 사용하여 find
        boolean find = false;
        StringBuilder sb = new StringBuilder();
        queue = new LinkedList<>();
        // 사용되었고 차수가 0인 알파벳으로 시작
        for (int i = 0; i < 27; i++) {
            if(alpha[i] && indegree[i]==0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            // 만약 큐의 사이즈가 2이상이라면 하나의 순서를 정할 수가 없으므로 ? 출력
            if(queue.size() > 1) {
                System.out.println("?");
                return;
            }

            // queue.pop
            int curChar = queue.poll();
            sb.append((char)(curChar+'a'));
            List<Integer> ends = new ArrayList<>(edges[curChar]);
            for (int i = 0; i < ends.size(); i++) {
                // curChar 과 연결된 아이들의 indegree 빼면서 0 되면 queue에 넣기
                int end = ends.get(i);
                indegree[end]--;
                if(indegree[end] == 0) queue.add(end);
            }
        }

        // indegree > 0 인 알파벳 남아있으면 사이클 존재 -> "!" 출력
        for (int i = 0; i < 27; i++) {
            if(alpha[i] && indegree[i] > 0) {
                System.out.println("!");
                return;
            }
        }

        System.out.print(sb);
    }
}