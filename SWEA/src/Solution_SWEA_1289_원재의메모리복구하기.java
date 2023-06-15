import java.util.Scanner;


class Solution_SWEA_1289_원재의메모리복구하기 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[] results = new int[T];

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int toggle = 0;
			int current = 0;
			String bit = sc.next(); // bit 받아오기
			//0에서 시작해서 몇 번 바뀌는지 확인
			for (int i = 0; i < bit.length(); i++) {
				if(bit.charAt(i)-'0' != current) {
					current = bit.charAt(i)-'0';
					toggle++;
				}
			}
			results[test_case-1] = toggle; //결과값 저장
		}
		
		//output 출력
		for (int i = 1; i <= T; i++) {
			System.out.println("#"+i+" "+results[i-1]);
		}
		sc.close();	
	}
}