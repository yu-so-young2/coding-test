package Silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BOJ_22233_가희와키워드_S2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // N, M 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N개의 키워드 입력
        HashSet<String> set = new HashSet<>(); // 키워드 저장할 공간
        for (int i = 0; i < N; i++) {
            set.add(br.readLine()); // 키워드 저장
        }

        // M개의 글마다 출력
        for (int tc = 0; tc < M; tc++) {
            // 현재 키워드 사전에 등재된 키워드 개수 - hit한 키워드 개수
            // hit한다면 set에서 삭제
            int num = set.size();
            int hit = 0;
            String[] keywords = br.readLine().split(",");
            for (int i = 0; i < keywords.length; i++) {
                String keyword = keywords[i];
                if(set.contains(keyword)) {
                    hit++;
                    set.remove(keyword);
                }
            }
            sb.append(num-hit).append("\n");
        }

        System.out.print(sb);
    }
}
