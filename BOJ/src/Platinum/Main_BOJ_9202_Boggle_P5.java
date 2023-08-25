package Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_BOJ_9202_Boggle_P5 {

    static char[][] board;
    static boolean[][] visited;
    static Trie trie;
    static String maxWord;
    static int W, B, maxScore, findCnt, totalScore;
    static int boardScore[] = {0,0,0,1,1,2,3,5,11};
    static int[] dy = {-1,-1,-1,0,1,1,1,0};
    static int[] dx = {-1,0,1,1,1,0,-1,-1};
    static HashSet<String> set;

    public static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode(){
            children = new TrieNode[26]; // A~Z 알파벳 자식 저장
            isEndOfWord = false;
        }

        public boolean containsKey(char ch) {
            if(children[ch-'A'] != null) return true; // 해당 알파벳을 자식 노드로 가지고 있다면 true
            return false;
        }

        public void setChildren(char ch, TrieNode node) {
            children[ch-'A'] = node;
        }

        public TrieNode get(char ch) {
            return children[ch-'A']; // 해당 알파벳 자식 노드 return, 만일 없다면 null
        }
    }



    public static class Trie {
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) { // 단어의 각 알파벳에 대하여
                char current = word.charAt(i);
                if(!node.containsKey(current)) {
                    node.setChildren(current, new TrieNode());
                }
                node = node.get(current);
            }
            node.isEndOfWord = true;
        }

        public void search(int r, int c, TrieNode node, String path) {
            visited[r][c] = true;

            // 해당 알파벳 자식이 없으면 이 경로는 버리기
            if(!node.containsKey(board[r][c])) {
                visited[r][c] = false;
                return;
            }

            if(node.get(board[r][c]).isEndOfWord) {
                set.add(path+board[r][c]); // 중복 제거하며 찾음 체크
            }

            node = node.get(board[r][c]);

            for(int i = 0; i < 8; i++) {
                int ny = r+dy[i];
                int nx = c+dx[i];
                if(ny>=0 && nx>=0 && ny<4 && nx<4 && !visited[ny][nx]) {
                    search(ny,nx,node,path+board[r][c]);
                }
            }

            visited[r][c] = false;

        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine()); // 단어의 개수
        String[] words = new String[W];
        trie = new Trie(); // 새로운 트라이 생성
        for(int i = 0; i < W; i++) {
            trie.insert(br.readLine()); // 트라이에 단어 삽입
        }
        br.readLine(); // 줄띄우기
        B = Integer.parseInt(br.readLine()); // 보드의 개수
        for(int tc = 0; tc < B; tc++) { // 각 보드마다
            // 새로운 맵 받기
            board = new char[4][4];
            set = new HashSet<>();
            for(int i = 0; i < 4; i++) {
                String str = br.readLine();
                for(int j = 0; j < 4; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
            if(tc < B-1) br.readLine(); // 줄띄우기



            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    visited = new boolean[4][4];
                    trie.search(i, j, trie.root, "");
                }
            }

            // 단어 찾기 시작
            totalScore = 0;
            findCnt = 0;
            maxScore = -1;
            maxWord = "";

            for (String word : set) {
                if(maxScore == boardScore[word.length()]) maxWord = maxWord.compareTo(word) < 0? maxWord:word;
                else if(maxScore < boardScore[word.length()]) {
                    maxScore = boardScore[word.length()];
                    maxWord = word;
                }
                totalScore += boardScore[word.length()];
                findCnt++;
            }

            System.out.println(totalScore+" "+maxWord+" "+findCnt);
        }
    }
}