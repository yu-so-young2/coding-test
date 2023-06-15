package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_12015_LIS_BS_G2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // LIS
        List<Integer> lis = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int cur = nums[i];
            // LIS list 갱신
            if(lis.isEmpty() || cur > lis.get(lis.size()-1)) {
                lis.add(cur);
            } else if (cur < lis.get(lis.size()-1)) {
                //lis 에 cur 넣기
                binarySearch(lis, 0, lis.size()-1, cur);
            }



        }

        System.out.println(lis.size());

    }

    private static void binarySearch(List<Integer> lis, int left, int right, int cur) {
        if(left>=right) {
            lis.set(left, cur);
            return;
        }

        int mid = (left+right)/2;
        if(lis.get(mid) < cur) {
            binarySearch(lis, mid+1, right, cur);
        } else {
            binarySearch(lis, left, mid, cur);
        }

    }
}