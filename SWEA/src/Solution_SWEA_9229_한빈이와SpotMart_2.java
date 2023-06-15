import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_SWEA_9229_�Ѻ��̿�SpotMart_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); //�׽�Ʈ���̽� ��
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			st.nextToken();
			int maxWeight = Integer.parseInt(st.nextToken()); //���� �� ����
			
			st = new StringTokenizer(br.readLine()); //�� ���� �Է�
			ArrayList<Integer> snacks = new ArrayList<>();
			while(st.hasMoreTokens()) snacks.add(Integer.parseInt(st.nextToken()));
			//�������� ����
			Collections.sort(snacks, Collections.reverseOrder());
			
			int result = -1; //default -1
			for (int i = 0; i < snacks.size(); i++) {
				for (int j = i+1; j < snacks.size(); j++) {
					int sum = snacks.get(i)+snacks.get(j);
					if(sum <= maxWeight && sum > result) {
						result = sum;
					}
				}
			}
			
			sb.append("#"+tc+" "+result+"\n");
		}//for each test case
		System.out.println(sb.toString());
	}
}
