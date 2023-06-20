package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1208_부분수열의합2_G1 {
    static int N, S, nums[];
    static List<Integer> left, right;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정수의 개수
        S = Integer.parseInt(st.nextToken()); // 목표 합

        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums); // 정렬

        // 반으로 나누어 부분합 만들기
        left = new ArrayList<>();
        right = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = nums[i];

            if(i < N/2) {
                int size = left.size();
                for (int j = 0; j < size; j++) {
                    left.add(left.get(j)+num);
                }
                left.add(num);
            } else {
                int size = right.size();
                for (int j = 0; j < size; j++) {
                    right.add(right.get(j)+num);
                }
                right.add(num);
            }
        }

        // 정렬
        left.add(0);
        right.add(0);
        Collections.sort(left);
        Collections.sort(right);

        // 투포인터
        long res = 0;
        int pointL = 0;
        int pointR = right.size()-1;
        while(pointL < left.size() && pointR >= 0) {
            int valL = left.get(pointL);
            int valR = right.get(pointR);

            if(valL + valR == S) {
                long cntL = 0;
                long cntR = 0;

                //왼쪽리스트에서 같은 수 찾기
                while(pointL < left.size() && valL == left.get(pointL)) {
                    cntL++;
                    pointL++;
                }
                //오른쪽리스트에서 같은 수 찾기
                while(pointR >= 0 && valR == right.get(pointR)) {
                    cntR++;
                    pointR--;
                }
                System.out.println(cntL);
                System.out.println(cntR);
                res += cntL*cntR;
            }
            if(valL + valR < S) {
                pointL++;
            }
            if(valL + valR > S) {
                pointR--;
            }
        }

        if(S == 0) res--;

        System.out.println(res);
    }
}
