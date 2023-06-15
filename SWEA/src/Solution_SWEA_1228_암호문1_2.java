import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * ��������
 * 1) ��ũ��� ����
 * 2) 10���� ����
 */

public class Solution_SWEA_1228_��ȣ��1_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N; //��ȣ�� ����
		int x, y; //x: �߰� ������ �ε���, y: ������ ���� ����
		
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList<Integer> pwd = new LinkedList<>();
			N = Integer.parseInt(br.readLine()); //N ������
			st = new StringTokenizer(br.readLine()); //���� ��ȣ��
			for (int i = 0; i < N ; i++) { //10�������� �ޱ�
				pwd.add(Integer.parseInt(st.nextToken()));
				if(i == 10) break;
			}
			while(st.hasMoreTokens()) st.nextToken();
			
			br.readLine(); //��ɾ� ���� ������
			st = new StringTokenizer(br.readLine()); //��ɾ�
			
			while(st.hasMoreTokens()) {
				st.nextToken(); // I ������
				x = Integer.parseInt(st.nextToken()); //���� ���� �ε���
				y = Integer.parseInt(st.nextToken()); //������ ���� ����

				if(x >= 10) { //������ 10�������� ����� ���̹Ƿ� 10 ���� �ε��� ������ ���� 
					for (int i = 0; i < y; i++) {
						st.nextToken();
					}
				}
				else {
					for (int i = 0; i < y; i++) {
						pwd.add(x++, Integer.parseInt(st.nextToken()));
					}
				}
			} //��ɹ� ����

			sb.append("#"+tc+" ");
			for (int i = 0; i < 10; i++) {
				sb.append(pwd.get(i)+" ");
			}
			sb.append("\n");
		} //for each test case
		System.out.println(sb);
	} //main
}
