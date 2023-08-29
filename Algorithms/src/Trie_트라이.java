public class Trie_트라이 {

    class Trie {
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            // 1. root에서 시작
            TrieNode node = root;

            // 2. 단어의 모든 알파벳에 대하여
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                // 트라이 현재 노드의 자식 중 만약 해당 문자가 있다면 그대로 넘어가고, 없다면 생성
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }

            // 3. 단어의 끝임을 표시
            node.isEndOfWord = true;
        }

        public boolean search(String word) {
            // 1. root에서 시작
            TrieNode node = root;

            // 2. 단어의 모든 알파벳에 대하여
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                // 트라이 현재 노드의 자식 중 만약 해당 문자가 있다면 그대로 넘어가고, 없다면 false
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }

            // 3. 마지막 노드가 EndOfWord인지 확인하여 true / false 리턴
            return node.isEndOfWord;
        }

        // 특정 단어 지우기
        public void delete(String word) {
            delete(this.root, word, 0); // 최초로 delete 던지는 부분
        }

        private void delete(TrieNode thisNode, String word, int index) {

            char c = word.charAt(index);
            TrieNode childNode = thisNode.children[c - 'a'];

            // 아예 없는 단어인 경우(자식을 찾아 내려갈 수 없는 경우) 에러 출력
            if (childNode == null)
                throw new Error("There is no [" + word + "] in this Trie.");

            index++;

            // 마지막 노드(삭제를 시작하는 첫 노드)에 도달했을 때
            if (index == word.length()) {
                // 삭제조건 2번 항목 확인
                // EndOfWord가 아닌 경우(트라이가 가지고 있는 단어가 아닌 경우) 에러 출력
                if (!childNode.isEndOfWord) throw new Error("There is no [" + word + "] in this Trie.");

                // 삭제조건 1번 항목 확인
                // 자식 노드가 없으면(이 단어를 포함하는 더 긴 단어가 없으면) 노드 삭제
                if (!childNode.hasChild()) {
                    thisNode.children[c] = null;
                } else { // 자식 노드가 있다면 이 노드가 단어의 마지막이었다는 것만 false로 변경
                    childNode.isEndOfWord = false;
                }
            }

            // 아직 마지막 노드가 아니라면
            else {
                // 콜백 함수
                delete(childNode, word, index);

                // 삭제조건 1,3번 항목
                // 삭제 중, 자식 노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 이 노드 삭제
                if (!childNode.isEndOfWord && !childNode.hasChild())
                    thisNode.children[c] = null;
            }
        }
    }

    class TrieNode {
        TrieNode children[];
        boolean isEndOfWord;

        public TrieNode(){
            this.children = new TrieNode[26]; // 알파벳 개수
            this.isEndOfWord = false;
        }

        public boolean containsKey(char ch) {
            if (children[ch - 'A'] != null) return true; // 해당 알파벳을 자식 노드로 가지고 있다면 true
            return false;
        }

        public TrieNode get(char ch) {
            return children[ch - 'A']; // 해당 알파벳 자식 노드 return, 만일 없다면 null
        }

        public void setChildren(char ch, TrieNode node) {
            children[ch - 'A'] = node;
        }

        public void setEndOfWord() {
            this.isEndOfWord = true;
        }

        public boolean hasChild() {
            for (int i = 0; i < 26; i++) {
                if(children[i] != null) {
                    return true;
                }
            }
            return false;
        }
    }
}