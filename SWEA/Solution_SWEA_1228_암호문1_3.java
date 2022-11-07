import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * ��ũ�帮��Ʈ�� ����
 */

public class Solution_SWEA_1228_��ȣ��1_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N;
		int x, y; //x: �߰� ������ �ε���, y: ������ ���� ����
		
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList<Integer> pwd = new LinkedList<>();
			N = Integer.parseInt(br.readLine()); //���� ��ȣ���� ���� N
			
			st = new StringTokenizer(br.readLine()); //���� ��ȣ��
			for (int i = 0; i < N; i++) {
				pwd.add(Integer.parseInt(st.nextToken()));
			}
			
			br.readLine(); //��ɾ��� ����(5~10)
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				st.nextToken(); // I ������
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				for (int i = 0; i < y; i++) {
					pwd.add(x++, Integer.parseInt(st.nextToken()));
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
