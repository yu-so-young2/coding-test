import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
이분탐색 구현
 */
public class Solution_SWEA_최장증가부분수열2_D3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T, N;
        int nums[];
        ArrayList<Integer> lis;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            lis = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());

                if(lis.isEmpty()) {
                    lis.add(num);
                } else{

                    if(num > lis.get(lis.size()-1)) {
                        lis.add(num);
                    } else { //작으면
                        int idx = binarySearch(lis, 0, lis.size(), num);
                        lis.remove(idx);
                        lis.add(idx, num);
                    }

                }
            }

            int max = lis.size();
            sb.append("#"+tc+" "+max+"\n");
        }

        System.out.println(sb);
    }

    //주어진 배열에서 해당 수가 몇 번째 인덱스에 위치하는지 리턴
    public static int binarySearch(ArrayList<Integer> nums, int start, int end, int num) {
        while(start < end) {
            int mid = (start+end)/2;
            if(nums.get(mid) > num) end = mid;
            else start = mid+1;
        }
        return end;
    }
}