/**
 1. 아이디어
 - 모든 기록에 대하여
 - 입차시 → 주차장에 차 번호와 입차시간 기록
 - 출차시 → 주차장에서 차 번호를 통해 입차시간 가져온 후, 해당 입차시간으로부터의 주차시간 계산하여 누적
 - 출차 안된 차량 → 주차장에 남아있는 차들의 경우 23:59 를 출차시각으로 하여 주차시간 누적
 - 모든 차량을 차 번호로 오름차순 정렬
 - 누적 주차시간이 적힌 표(?)를 통해 각 차량의 주차 요금 계산

 2.  자료구조
 - 1. 주차장 관리 : HashMap<String, String> parking
 - key : 차 번호
 - value : 입차시간
 - 2. 주차 시간 관리 : HashMap<String, Integer> bills
 - key : 차 번호
 - value : 누적 주차시간(min)

 3. 시간복잡도
 - O(N)
 */

import java.util.*;

public class Solution_Programmers_KAKAO_주차요금계산_L2 {
    public static HashMap<String, String> parking;
    public static HashMap<String, Integer> bills;


    public static int calculate(String inTime, String outTime) {
        // 입-출차 시간 사이의 시간 분으로 출력
        // 출차시 요금 계산
        String[] instr = inTime.split(":");
        String[] outstr = outTime.split(":");
        int diff = (Integer.parseInt(outstr[0])*60+Integer.parseInt(outstr[1]))
                - (Integer.parseInt(instr[0])*60+Integer.parseInt(instr[1]));
        // System.out.println("차이시간 "+diff);

        return diff;
    }

    public static int calculate2(int totalTime, int[] fees) {
        int result = 0;
        // 기본시간 내
        if(totalTime <= fees[0]) {
            result = fees[1];
        } else {
            result = fees[1];
            totalTime -= fees[0];

            int cnt = totalTime / fees[2] + (totalTime%fees[2]==0?0:1);
            // System.out.println("cnt "+cnt);
            result += cnt * fees[3];
        }
        // System.out.println("result "+result);
        return result;
    }


    public int[] solution(int[] fees, String[] records) {


        parking = new HashMap<>();
        bills = new HashMap<>();

        // 모든 기록 하나씩 보기
        for(int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            String time = record[0]; // 시간
            String car = record[1]; // 차량번호
            String act = record[2]; // 입/출차 내역

            // 입차시
            if("IN".equals(act)){
                // System.out.println(car+" 차량 입차");
                // 기록에 있는지 확인하여 없다면 누적시간 0으로 넣기
                if(bills.get(car)==null) {
                    bills.put(car, 0);
                }

                // 주차장에 추가
                parking.put(car, time);
            }

            // 출차시
            if("OUT".equals(act)){
                // System.out.println(car+" 차량 출차");
                String inTime = parking.get(car); // 입차시간

                // 시간 계산
                int diff = calculate(inTime, time);
                bills.put(car, bills.get(car)+diff); // 원래 시간에 추가

                // 주차장에서 빼기
                parking.remove(car);
            }

        }

        // 출차 안된 것 처리
        String[] restCars = parking.keySet().toArray(new String[0]);
        for(int i = 0; i < restCars.length; i++) {
            String car = restCars[i];
            String inTime = parking.get(car); // 입차시간

            // 23:59 기준 주차장에서 빼기
            // 요금 계산
            int diff = calculate(inTime, "23:59");
            bills.put(car, bills.get(car)+diff);

        }



        // 요금지에 입력된 차량 정보와 요금 반환
        String[] cars = bills.keySet().toArray(new String[0]);

        // for(String car : cars) {
        //     System.out.print(car+" ");
        // }

        Arrays.sort(cars); // 오름차순 정렬

        // System.out.println();
        // System.out.println("정렬 후");
        // for(String car : cars) {
        //     System.out.print(car+" ");
        // }
        // System.out.println();


        int[] answer = new int[cars.length]; // 차량 길이별로 요금명단 생성
        for(int i = 0; i < cars.length; i++) {
            answer[i] = calculate2(bills.get(cars[i]), fees);
        }
        return answer;
    }
}