import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1967_트리의지름_G4 {
    static class Node {
        int vertex, dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

    }

    static int V, diameter, farNode;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //input
        V = Integer.parseInt(br.readLine()); // 트리 정점의 개수 V
        List<List<Node>> graph = new LinkedList<>(); // 각 정점에게 연결된 간선 정보 저장

        for (int i = 0; i < V+1; i++) { // 1~V 까지 표현
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < V-1; i++) { // 그래프 연결 정보 저장
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()); // 정점 번호(v1)
            int v2 = Integer.parseInt(st.nextToken()); // 연결된 정점(v2) 번호
            int dist = Integer.parseInt(st.nextToken()); // 연결 간선 거리

            graph.get(v1).add(new Node(v2, dist));
            graph.get(v2).add(new Node(v1, dist));

        }

        diameter = 0;
        farNode = 0;

        // 1번째 dfs => 임의의 정점에서 가장 먼 노드(farNode) 구하기
        int cur = 1; // 임의의 정점
        visited = new boolean[V+1]; // 1~V까지 저장
        visited[cur] = true;
        dfs(cur, 0, graph);


        // 2번째 dfs
        cur = farNode;
        visited = new boolean[V+1]; // 1~V까지 저장
        visited[cur] = true;
        dfs(cur, 0, graph);


        //print
        System.out.println(diameter);
    }

    private static void dfs(int vertex, int distSum, List<List<Node>> graph) {
        List<Node> curList = graph.get(vertex); // 현재 노드와 연결된 노드 정보
        for (int i = 0; i < curList.size(); i++) {
            Node conn = curList.get(i);
            if(!visited[conn.vertex]) {
                visited[conn.vertex] = true; // 방문처리

                farNode = diameter<distSum+ conn.dist?conn.vertex:farNode;
                diameter = Math.max(diameter, distSum+ conn.dist);
                dfs(conn.vertex, distSum+ conn.dist, graph);

            }
        }
    }
}