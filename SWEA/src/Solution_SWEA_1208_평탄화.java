import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_SWEA_1208_평탄화 {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] results = new int[10];

		//for each test_case
		for(int test_case = 1; test_case <= 10; test_case++) {
			int dump = Integer.parseInt(br.readLine());
			int[] boxes = new int[100];
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 100; i++) //save boxes height
				boxes[i] = Integer.parseInt(tokens.nextToken());

			int max_idx = 0, min_idx = 0; //find highest and lowest box index
			
			while (dump > 0) { //while dump is valid
				//find highest, lowest box index
				for (int i = 0; i < boxes.length; i++) { 
					if(boxes[max_idx] < boxes[i]) max_idx = i;
					if(boxes[min_idx] > boxes[i]) min_idx = i;
				}
				
				//if flattening is finished, break
				if(boxes[max_idx]==boxes[min_idx]) break; 				
				
				//flattening
				boxes[max_idx]--; 
				boxes[min_idx]++;				
				dump--;
			}
			
			//find highest, lowest box index
			for (int i = 0; i < boxes.length; i++) {
				if(boxes[max_idx] < boxes[i]) max_idx = i;
				if(boxes[min_idx] > boxes[i]) min_idx = i;
			}
			results[test_case-1] = boxes[max_idx]-boxes[min_idx];

		}//for each test_case
		
		//output print
		for (int i = 1; i <= 10; i++) {
			System.out.println("#"+i+" "+results[i-1]);			
		}
	}
}