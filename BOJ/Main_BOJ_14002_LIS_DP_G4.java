import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_14002_LIS_DP_G4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }


        List<List<Integer>> dp = new ArrayList<>();
        int maxLength = 0;
        int maxIdx = 0;

        for (int i = 0; i < N; i++) {
            int len = 0;
            int idx = 0;

            for (int j = 0; j < i; j++) { // 이전에 있는 것들 중 나보다 작으면서 가장 긴 것 탐색

                if(nums[j] < nums[i] && dp.get(j).size() > len) {
                    len = dp.get(j).size();
                    idx = j;
                }

            }

            // idx번째 수열 복사해오기
            List<Integer> cur = new ArrayList<>();
            if(len != 0){
                for (int j = 0; j < dp.get(idx).size(); j++) {
                    cur.add(dp.get(idx).get(j));
                }
            }
            cur.add(nums[i]);
            dp.add(cur);

            if(cur.size() > maxLength){
                maxLength = cur.size();
                maxIdx = i;
            }
        }

        System.out.println(maxLength);
        for (Integer num:dp.get(maxIdx)) {
            System.out.print(num+" ");
        }

    }

}