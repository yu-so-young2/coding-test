package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BOJ_1764_듣보잡_S4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hashMap = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>();

        //듣도 못한 사람
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            hashMap.put(name, 1);
        }

        //보도 못한 사람
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if(hashMap.containsKey(name)) {
                nameList.add(name);
            }
        }

        Collections.sort(nameList);
        System.out.println(nameList.size());
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i));
        }

    }
}
