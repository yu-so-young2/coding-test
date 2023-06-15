package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BOJ_1253_좋다_G4 {
    public static final int PRESET = 1000000000;
    public static void main(String[] args) throws IOException {
        //입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>(); //goodNum 확인해야 하는 숫자 리스트
        HashMap<Integer, Integer> hashMap = new HashMap<>(); //해당 숫자가 존재하는지, 몇 개 있는지 저장

        /*
        왜 HashMap ?
        존재하는지 확인해야 하므로 인덱스 랜덤 접근 가능하도록 하려면 길이 1,000,000,000 의 배열을 선언해야 함
        그러나 int 형으로 해당 길이만큼 선언하면 힙 메모리 초과 -> **존재여부** 확인할 수 있는 다른 저장방법
        -> boolean 형으로 선언 -> 4byte->1byte 로 줄일 수 있지만 해당 숫자가 **몇개** 존재하는지 확인하려면
        배열 여러개 선언해야 하므로 메모리 관점에서 역시 부적합

        => HashMap: containsKey() 메소드로 해당 숫자의 존재여부 확인, value 로 해당 숫자 개수 count
         */


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken())); //goodNum Check 할 숫자 리스트 저장

            //HashMap 에 존재여부&개수 저장
            if(!hashMap.containsKey(list.get(i))) hashMap.put(list.get(i), 1);
                else hashMap.put(list.get(i), hashMap.get(list.get(i))+1);

        }

        //좋은 수 개수 count
        //  i번째수와 (나-i번째수) 가 모두 boolean true 인지 확인
        //  둘다 트루면 카운트++
        //  나보다 작은 수까지 조회 후 없으면 다음 수로 넘어감
        int goodNumCount = 0;
        Collections.sort(list); //효율적 탐색을 위해 정렬   (<- 정말 효율적인가?)

        //모든 수에 대하여 goodNum Check
    a:    for (int i = 0; i < list.size(); i++) {
            int curNum = list.get(i);

            for (int j = 0; j < list.size(); j++) {
                if(i==j) continue; //내가 아닌 수

                int num1 = list.get(j);
                int num2 = curNum - list.get(j);

                if(hashMap.containsKey(num1) && hashMap.containsKey(num2)) {
                    //충족 조건: 1)내가 아닌 2)서로 다른 두 개의 수

                    //1) 내가 아닌
                    if(curNum == num1 && curNum == num2) {
                        if(hashMap.get(curNum) < 3) continue ;
                    } else if (curNum == num2) {
                        if(hashMap.get(curNum) < 2) continue ;
                    }

                    //2) 서로 다른 두 개의 수
                    if(num1 == num2) {
                         if(hashMap.get(num1) < 2) continue ;
                    }

                    goodNumCount++;
                    continue a;
                }
            } //for j
        } //for list

        System.out.println(goodNumCount);

    }
}
