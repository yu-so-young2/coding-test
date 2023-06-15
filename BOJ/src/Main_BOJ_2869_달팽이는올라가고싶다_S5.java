import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2869_달팽이는올라가고싶다_S5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int climb = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        /*
        그날 낮동안 올라가는 높이: a
        그날 하루동안 올라가는 높이: a-b

        현재높이+a < V -> 현재높이+(a-b), 하루 더함
        현재높이+a >= V -> 하루더하고 끝


        2 1 5

        5/1 = 5...0, 나머지0<1 => 몫-1

        5 1 6

        6/4 = ...2, 나머지2>1 => 몫 그대로

        100 99 1000000000
        1000000000/1 = 1000000000...0 나머지0<1 => 몫-1


        10을 올라가고 싶은데
        3올라가고 1 내려와

        3 2 | 5 4 | 7 6 | 9 8 | 11 10 끝

        하루에 2씩 올라가는 거잖아 2->4->6
        낮으로만 따지면 3->5->7->10

        낮: a + (n-1)(a-b) = an-bn +b = n(a-b) + b
        밤: n(a-b) = an-bn = n(a-b)

        (10 - 1) / (2) = 9/2 = 4 ... 1 => 나머지 1 <=

         */

        int day = (int)Math.ceil((double)(V-down)/(double)(climb-down));
        System.out.println(day);

    }
}
