package Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_5670_휴대폰자판_P4 {
    public static class TrieNode {
        TrieNode[] children;
        int childNum;
        boolean isEndOfWord;
        public TrieNode() {
            children = new TrieNode[26];
            childNum = 0;
            isEndOfWord = false;
        }
    }

    public static class Trie {
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word) {
            TrieNode node = root;
            // 단어의 모든 알파벳에 대하여
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                // 트라이 현재 노드의 자식 중 만약 해당 문자가 있다면 그대로 넘어가고, 없다면 생성
                if(node.children[c-'a'] == null) {
                    node.children[c-'a'] = new TrieNode();
                    node.childNum++;
                }
                node = node.children[c-'a'];
            }
            // 단어의 마지막 표시
            node.isEndOfWord = true;
        }

        public int search(String word) {
            int cnt = 0;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if(node == root) cnt++;
                else {
                    if(node.childNum > 1) cnt++;
                    else if(node.isEndOfWord) cnt++;
                }
                node = node.children[c-'a'];
            }
            return cnt;
        }

    }

    static int N, totalCnt;
    static String[] words;
    static Trie trie;
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String input = br.readLine();
            if(input == null || input.equals("")) break; // 입력 종료
            N = Integer.parseInt(input); // 이번 테스트케이스 단어의 개수
            words = new String[N]; // 단어 저장 배열
            trie = new Trie(); // 단어 저장 트라이
            totalCnt = 0; // 버튼 횟수 누적할 변수

            for (int i = 0; i < N; i++) {
                words[i] = br.readLine(); // 단어 저장
                trie.insert(words[i]); // 트라이에 단어 추가
            }

            for (int i = 0; i < N; i++) {
                String word = words[i]; // 버튼 횟수 찾을 단어
                totalCnt += trie.search(word);
            }

            // 평균 버튼 횟수
            double res = (double)totalCnt/(double)N;
            sb.append(String.format("%.2f", res)+"\n");
        }

        System.out.print(sb);
    }
}
