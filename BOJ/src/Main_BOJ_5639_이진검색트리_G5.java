import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_5639_이진검색트리_G5 {
    static class Node {
        int num;
        Node right, left;
        Node(int num){
            this.num = num;
            this.right = null;
            this.left = null;
        }
    }
    static class Tree {
        Node root = null;
        int cnt = 0;

        public void add(int num) {
            Node n = new Node(num);
            if(this.root == null) { // 루트가 없다면
                this.root = n;
            } else { // 루트가 있다면 이진 탐색 트리로 넣기
                //노드방문
                //나보다 작으면 왼쪽으로
                //나보다 크면 오른쪽으로
                Node cur = this.root;
                while(true) {
                    if(num < cur.num) {
                        if(cur.left != null) {
                            cur = cur.left;
                        } else {
                            cur.left = n;
                            break;
                        }
                    } else {
                        if(cur.right != null){
                            cur = cur.right;
                        } else {
                            cur.right = n;
                            break;
                        }
                    }
                }
            }
        }
    }

    static StringBuilder sb;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Tree tree = new Tree();
        String str;
        // input
        while((str=br.readLine())!=null){
            int n = Integer.parseInt(str);
            tree.add(n);
        }

        // print 후위순회
        postOrder(tree.root);

        System.out.print(sb);

    }//main

    private static void postOrder(Node node) {
        if(node != null) {
//            System.out.println("I am "+node.num);
            postOrder(node.left);
            postOrder(node.right);
            sb.append(node.num+"\n");
        }
    }
}