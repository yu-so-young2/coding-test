package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_BOJ_2407_조합_S3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        m = Math.min(m, n-m);
        boolean[] mList = new boolean[m+1];

        BigInteger ans = BigInteger.ONE;
        for (int i = n; i > n-m; i--) {
            ans = ans.multiply(new BigInteger(String.valueOf(i)));
            for (int j = mList.length-1; j > 1; j--) {
                if(!mList[j] && ans.mod(new BigInteger(String.valueOf(j))).equals(BigInteger.ZERO)) {
                    mList[j] = true;
                    ans = ans.divide(new BigInteger(String.valueOf(j)));
                }
            }
        }

        System.out.println(ans);
    }
}