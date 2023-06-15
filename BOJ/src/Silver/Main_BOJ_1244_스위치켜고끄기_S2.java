package Silver;

import java.util.Scanner;

/*
 * 백준 1244. 스위치 켜고 끄기
 * 구현, 1차원 배열 시뮬레이션
 * 
 */

public class Main_BOJ_1244_스위치켜고끄기_S2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //num of switches
		int[] switches = new int[N];

		for(int i = 0; i < N; i++) { // initialize switch array
			switches[i] = sc.nextInt();
		}
		
		int M = sc.nextInt(); //num of students
		
		for(int i = 0; i < M; i++) {
			int sex = sc.nextInt(); //sex of student
			int idx = sc.nextInt(); //idx of switch

			switch(sex) {
			case 1: //boy
				int idx_remember = idx;
				while(idx <= N) {
					//toggle
					if(switches[idx-1]==0) switches[idx-1]=1;
					else switches[idx-1]=0;
					idx += idx_remember;
				}				

				break;
			default: //girl
				int idx_right = idx+1; //4
				int idx_left = idx-1; //2

				//toggle 
				if(switches[idx-1] == 0) switches[idx-1] = 1;
				else switches[idx-1] = 0;
				
				while(idx_right <= N && idx_left >= 1) {
					if(switches[idx_right-1]==switches[idx_left-1]) {
						//toggle
						if(switches[idx_right-1]==0) {
							switches[idx_right-1] = 1;
							switches[idx_left-1] = 1;
						}
						else {
							switches[idx_right-1] = 0;
							switches[idx_left-1] = 0;							
						}
                        idx_right++;
					    idx_left--;
					}
                    else break;
				}
				break;
			} //switch-case
		}//for
		
		
		//print switches
		for(int i = 0; i < N; i++) {
			if(i!=0 && i%20==0) System.out.println();
			if(i%20 != 0) System.out.print(" ");
			System.out.print(switches[i]); //0 1 1 
		}
	}
}