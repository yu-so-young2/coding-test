import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1167_트리의지름_G2 {
    static class Node {
        int vertex, dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    static int V, diameter, farNode;
    static List<List<Node>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //input
        V = Integer.parseInt(br.readLine()); // 트리 정점의 개수 V
        graph = new ArrayList<>(); // 각 정점에게 연결된 간선 정보 저장

        for (int i = 0; i < V+1; i++) { // 1~V 까지 표현
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) { // 그래프 연결 정보 저장
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()); // 정점 번호
            // 해당 정점(v1)과 연결된 정점(v2) 정보
            while(true){
                int v2 = Integer.parseInt(st.nextToken()); // 연결된 정점(v2) 번호
                if(v2 == -1) break; // 종료조건
                int dist = Integer.parseInt(st.nextToken()); // 연결 간선 거리
                graph.get(v1).add(new Node(v2, dist));
            }
        }


        // 각 정점 당 연결된 개수가 가장 적은 순으로 정렬
        // 최소의 연결 수를 가지고 있는 정점들만 따로 모아서 dfs 시행
        // dfs 시행하며 diameter 갱신
        diameter = 0;
        farNode = 0;

        // 1번째 dfs
        int cur = 1;
        visited = new boolean[V+1]; // 1~V까지 저장
        visited[cur] = true;
        dfs(cur, 0);

        // 2번째 dfs
        cur = farNode;
        visited = new boolean[V+1]; // 1~V까지 저장
        visited[cur] = true;
        dfs(cur, 0);


        //print
        System.out.println(diameter);
    }

    // 해당 정점에서 dfs 하며 dist 누적
    private static void dfs(int vertex, int distSum) {
        List<Node> curList = graph.get(vertex); // 현재 노드와 연결된 노드 정보
        for (int i = 0; i < curList.size(); i++) {
            Node conn = curList.get(i);
            if(!visited[conn.vertex]) {
                visited[conn.vertex] = true;

                farNode = diameter<distSum+ conn.dist?conn.vertex:farNode;
                diameter = Math.max(diameter, distSum+ conn.dist);
                dfs(conn.vertex, distSum+ conn.dist);

            }
        }
    }
}
