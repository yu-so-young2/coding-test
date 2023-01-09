import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1806_부분합_G4_최적화전 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = (i!=0?dp[i-1]:0) + arr[i];
        }
//
//        for (int i = 0; i < N; i++) {
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();

        // 누적합
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int j = i;
            int curLen = Integer.MAX_VALUE;
            while(j<N) {
                if(dp[j]-(i==0?0:dp[i-1]) >= S) {

                    curLen = j-i+1;
//                    System.out.println(i+" "+j+" "+curLen);
                    break;
                }
                j++;
            }
            minLen = Math.min(minLen, curLen);
        }


        System.out.println(minLen==Integer.MAX_VALUE?0:minLen);

    }
}
