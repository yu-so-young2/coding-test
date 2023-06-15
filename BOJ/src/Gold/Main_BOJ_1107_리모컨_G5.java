package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1107_리모컨_G5 {
    static boolean[] btns;
    static int target, min;

    public static void main(String[] args) throws IOException {
        //input
        //target
        //broken cnt
        //brokens...
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        target = Integer.parseInt(br.readLine().trim()); // target 채널
        btns = new boolean[10]; // 0 ~ 9 사이의 버튼 작동 여부
        for (int i = 0; i < btns.length; i++) { // 초기화
            btns[i] = true;
        }

        // 고장난 버튼 저장
        int brokenCnt = Integer.parseInt(br.readLine().trim()); // 고장난 버튼 개수
        if(brokenCnt != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenCnt; i++) {
                int broken = Integer.parseInt(st.nextToken()); // 고장난 번호
                btns[broken] = false;
            }
        }

        //combination
        //find min diff num
        min = Integer.MAX_VALUE;
        for (int i = 0; i < btns.length; i++) { // 가능한 모든 버튼에 대하여 시작
            if(btns[i]) {
                comb(i);
            }
        }


        //target == 100 => 그대로 0
        int ans = Math.min ( Math.abs(target-100), min);
        System.out.println(ans);

    }

    public static void comb(int num) {
        // 999999 넘어가면 그만
        if(num > 999999) {
            return;
        }

        // min 갱신(자릿수 + 채널 증감 '+' '-' 횟수)
        // 자릿수: (num==0?1:(int)Math.log10(num)+1)
        // 횟수: Math.abs(target-num)
        min = Math.min(min, (num==0?1:(int)Math.log10(num)+1)+Math.abs(target-num));

        for (int i = 0; i < btns.length; i++) { // 가능한 모든 버튼에 대하여
            if(btns[i]) { // 버튼을 사용할 수 있다면
                if(num==0 && i == 0) continue;
                comb(num*10+i);
            }
        }
    }
}