
import java.util.ArrayList;
import java.util.Scanner;

/*
 * ��ĳ�� ���(�ڵ� ���� ��)
 * => ���: ���۵� ����ϴϱ� 1000->1300 ����
 */

public class Solution_SWEA_9229_�Ѻ��̿�SpotMart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt(); //�׽�Ʈ���̽� ��
		for (int tc = 1; tc <= T; tc++) {
			int numSnack = sc.nextInt();
			int maxWeight = sc.nextInt(); //���� �� ����
			
			ArrayList<Integer> snacks = new ArrayList<>();				
			for (int i = 0; i < numSnack; i++) { //�� ���� �Է�
				snacks.add(sc.nextInt());
			}
			
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
		sc.close();
	}
}
