package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main_BOJ_5430_AC_G5 {
    public static final boolean FORWARD = true;

    public static void main(String[] args) throws IOException {
        //input
        // T
        // p(function), 1<=p<=100,000
        // array length <= 100,000
        // array
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            //input
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            int[] nums = {};
            if(n > 0){
                arr = arr.substring(1,arr.length()-1);
                nums = Stream.of(arr.split(",")).mapToInt(Integer::parseInt).toArray();
            }


            // start, end 포인터
            int start = 0;
            int end = nums.length-1;
            boolean dir = FORWARD;

            for (int i = 0; i < p.length(); i++) {
                char function = p.charAt(i);
                switch (function) {
                    case 'R': // dir 반대로
                        dir = !dir;
                        break;
                    case 'D': // 삭제(포인터 옮기기)
                        if(dir==FORWARD) {
                            start++;
                        } else {
                            end--;
                        }
                        break;
                } // switch case
            }

            //output print
            if(start > end+1) { // 비어있는데 D를 한 경우 => 포인터 위치 전복 => error
                sb.append("error\n");
            } else {
                sb.append("[");
                // array print
                if(dir==FORWARD) {
                    while(start <= end) {
                        sb.append(nums[start]);
                        start++;
                        if(start<=end){
                            sb.append(",");
                        }
                    }
                } else{
                    while(start <= end) {
                        sb.append(nums[end]);
                        end--;
                        if(start<=end){
                            sb.append(",");
                        }
                    }
                }
                sb.append("]\n");
            }

        }// for each test case
        System.out.println(sb);

    }
}
