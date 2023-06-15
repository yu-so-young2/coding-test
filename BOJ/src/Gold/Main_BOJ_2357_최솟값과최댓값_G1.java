package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2357_최솟값과최댓값_G1 {
    // 최대/최소 저장 Segment Tree 클래스
    static class SegmentTree {
        int[] indexTreeMin; // 최솟값 저장할 index tree
        int[] indexTreeMax; // 최댓값 저장할 index tree
        int[] data; // 실제 저장되어있는 데이터

        // leafCount: 실제 데이터 저장 노드 수(최하단, N), height: 트리 높이(=ceil(logN))
        // 트리 height에 의해 인덱스 트리의 전체 노드의 수가 결정된다.
        // totalN = 2^(h+1)-1, leafNode 시작점: 2^h
        int leafCount, height;

        SegmentTree(int[] arr){ // arr 에 해당하는 index tree 초기 생성
            this.data = arr; // data 저장
            this.leafCount = arr.length; // 실제 data 길이
            this.height = (int)Math.ceil(Math.log(this.leafCount)/Math.log(2)); // 트리 높이

            this.indexTreeMin = new int[(int)Math.pow(2, height+1)+1]; // index tree 생성
            this.indexTreeMax = new int[(int)Math.pow(2, height+1)+1]; // index tree 생성

            // +1 하는 이유: 0 인덱스 삽입하여 계산을 용이하게 하기 위하여(실제로는 1번 인덱스부터 사용할 것임)
            makeIndexTree();
        }
        void makeIndexTree(){
            makeSubTree(1, 1, leafCount); // 루트 노드(1)부터 시작, Top-down
        }
        private void makeSubTree(int treeIdx, int left, int right) { // left, right: 현재 노드가 커버하는 인덱스 범위
            if(left==right) { // 리프노드 도착, 값 넣기
                this.indexTreeMin[treeIdx] = this.data[left-1];
                this.indexTreeMax[treeIdx] = this.data[left-1];
                return;
            }

            // 리프노드 도착하지 않은 경우 왼쪽, 오른쪽으로 내려가기(중위순회)
            int mid = (left+right)/2;
            makeSubTree(treeIdx*2, left, mid); // 왼쪽 서브 트리
            makeSubTree(treeIdx*2+1, mid+1, right); // 오른쪽 서브 트리

            // 위의 코드를 통해 왼쪽, 오른쪽 서브 트리가 모두 만들어졌다
            // 최소값 세그먼트 트리 ==> 왼쪽, 오른쪽 서브 트리의 부모 노드 중 "작은 값" 구하기
            this.indexTreeMin[treeIdx] = Math.min(this.indexTreeMin[treeIdx*2], this.indexTreeMin[treeIdx*2+1]);
            this.indexTreeMax[treeIdx] = Math.max(this.indexTreeMax[treeIdx*2], this.indexTreeMax[treeIdx*2+1]);

        }

        // 구간 최솟값 구하기 O(logN)
        public long getPartialMin(int left, int right) {
            return getSubPartialMin(1, 1, leafCount, left, right);

        }
        private long getSubPartialMin(int treeIdx, int left, int right, int tleft, int tright) {
            if(left > tright || right < tleft) {
                return Integer.MAX_VALUE;
            }
            if(tleft <= left && right <= tright) { // 쏙 들어가면 더이상 내려가지 말고 리턴
                return this.indexTreeMin[treeIdx];
            }
            int mid = (left+right)/2;
            return Math.min(getSubPartialMin(treeIdx*2, left, mid,tleft,tright),getSubPartialMin(treeIdx*2+1, mid+1, right,tleft,tright));
        }

        public long getPartialMax(int left, int right) {
            return getSubPartialMax(1, 1, leafCount, left, right);

        }
        private long getSubPartialMax(int treeIdx, int left, int right, int tleft, int tright) {
            if(left > tright || right < tleft) {
                return Integer.MIN_VALUE;
            }
            if(tleft <= left && right <= tright) { // 쏙 들어가면 더이상 내려가지 말고 리턴
                return this.indexTreeMax[treeIdx];
            }
            int mid = (left+right)/2;
            return Math.max(getSubPartialMax(treeIdx*2, left, mid,tleft,tright),getSubPartialMax(treeIdx*2+1, mid+1, right,tleft,tright));
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // input
        // N M, N: 수의 개수, M: 구간 쿼리 횟수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(nums);

//        System.out.println(segmentTree.toString());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(segmentTree.getPartialMin(a,b)+" "+segmentTree.getPartialMax(a,b)+"\n");
        }
        System.out.print(sb);
    }
}
/*
3 3
1000000000
999999999
999999998
1 1


*/