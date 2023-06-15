package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15961_회전초밥_G4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, d, k, c;
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        int[] sushi = new int[N];
        
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        //최대 접시 수 구하기
        int[] check = new int[d+1];
        int plates = 0;
        int maxPlate = 0;

        //최초 슬라이드 구성
        for (int i = 0; i < k; i++) {
            if(check[sushi[i]]==0) { //먹은 적 없다면
                plates++;
            }
            check[sushi[i]]++;
        }

        for (int i = 1; i < N; i++) {
            //그 전 초밥 없애기
            if(check[sushi[i-1]] == 1) {
                plates--;
            }
            check[sushi[i-1]]--;


            //다음 초밥 먹기
            if(check[sushi[(i+k-1)%N]]==0) {
                plates++;
            }
            check[sushi[(i+k-1)%N]]++;


            //쿠폰 초밥 유무 확인
            if(check[c]==0) {
                maxPlate = Math.max(maxPlate, plates+1);
            }
            else {
                maxPlate = Math.max(maxPlate, plates);
            }
        }

        System.out.println(maxPlate);
    }
}
