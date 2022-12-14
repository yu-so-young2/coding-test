import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2042_구간합구하기_G1 {
    // 구간합 저장 Segment Tree 클래스
    static class SegmentTree {
        long[] indexTree;        // index tree
        long[] data;             // 실제 저장되어있는 데이터
        int leafCount, height;  // leafCount: 실제 데이터 저장 노드 수(최하단, N), height: 트리 높이(=ceil(logN))
        // 트리 height에 의해 인덱스 트리의 전체 노드의 수가 결정된다.
        // totalN = 2^(h+1)-1, leafNode 시작점: 2^h
        SegmentTree() {
        }
        SegmentTree(long[] arr){ // arr 에 해당하는 index tree 초기 생성
            this.data = arr; // data 저장
            this.leafCount = arr.length; // 실제 data 길이
            this.height = (int)Math.ceil(Math.log(this.leafCount)/Math.log(2)); // 트리 높이

            this.indexTree = new long[(int)Math.pow(2, height+1)+1]; // index tree 생성
            // +1 하는 이유: 0 인덱스 삽입하여 계산을 용이하게 하기 위하여(실제로는 1번 인덱스부터 사용할 것임)
            makeIndexTree();
        }

        @Override
        public String toString() {
            return "SegmentTree{" +
                    "nodes=" + Arrays.toString(indexTree) +
                    '}';
        }

        // index tree 초기 생성 O(N)
        void makeIndexTree(){
            makeSubTree(1, 1, leafCount); // 루트 노드(1)부터 시작, Top-down
        }
        private void makeSubTree(int treeIdx, int left, int right) { // left, right: 현재 노드가 커버하는 인덱스 범위
            if(left==right) { // 리프노드 도착, 값 넣기
                this.indexTree[treeIdx] = this.data[left-1];
                return;
            }

            // 리프노드 도착하지 않은 경우 왼쪽, 오른쪽으로 내려가기(중위순회)
            int mid = (left+right)/2;
            makeSubTree(treeIdx*2, left, mid); // 왼쪽 서브 트리
            makeSubTree(treeIdx*2+1, mid+1, right); // 오른쪽 서브 트리

            // 위의 코드를 통해 왼쪽, 오른쪽 서브 트리가 모두 만들어졌다
            // 왼쪽, 오른쪽 서브 트리의 부모 노드 각각 더하기
            this.indexTree[treeIdx] = this.indexTree[treeIdx*2] + this.indexTree[treeIdx*2+1];

        }

        // index tree 중간 인덱스 값 갱신 O(logN)
        public void update(int updateIdx, long updateValue) {
            long diff = updateValue-data[updateIdx-1];
            updateSubTree(1, 1, leafCount, updateIdx, diff);
            data[updateIdx-1] = updateValue;
        }

        private void updateSubTree(int treeIdx, int left, int right, int updateIdx, long diff) {
            if(left <= updateIdx && updateIdx <= right) {
                this.indexTree[treeIdx] += diff;
                if(left==right) return;
            }

            int mid = (left+right)/2;
            if(updateIdx <= mid) {
                updateSubTree(treeIdx*2, left, mid, updateIdx, diff);
            }
            else {
                updateSubTree(treeIdx*2+1, mid+1, right, updateIdx, diff);
            }
        }


        // 구간합 구하기 O(logN)
        public long getPartialSum(int left, int right) {
            return getSubPartialSum(1, 1, leafCount, left, right);

        }
        private long getSubPartialSum(int treeIdx, int left, int right, int tleft, int tright) {
            if(left > tright || right < tleft) {
                return 0;
            }
            if(tleft <= left && right <= tright) { // 쏙 들어가면 더이상 내려가지 말고 리턴
                return this.indexTree[treeIdx];
            }
            int mid = (left+right)/2;
            return getSubPartialSum(treeIdx*2, left, mid,tleft,tright)+getSubPartialSum(treeIdx*2+1, mid+1, right,tleft,tright);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // input
        // N M K, N: 수의 개수, M: 변경 횟수, K: 구간 합 쿼리 횟수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        // segment tree make
        SegmentTree segmentTree = new SegmentTree(nums); // Segment Tree 생성
        System.out.println(segmentTree.toString());

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 1) { // tree update
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                segmentTree.update(b, c);
            } else { // prefix sum
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(segmentTree.getPartialSum(b, c)+"\n");
            }
        }
        System.out.print(sb);

    }
}