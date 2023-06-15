import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_SWEA_1861_정사각형방 {
	private static int[][] room;
	private static Deque<Integer> deque = new ArrayDeque<Integer>();
	
	private static int[] di = { -1, 1, 0, 0 };
	private static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine()); //num of test case
		for (int tc = 1; tc <= TC; tc++) { //for each test case
			//get input, initialize rooms map
			int N = Integer.parseInt(br.readLine());
			room = new int[N + 2][N + 2]; //padding with 0(사방탐색)
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
					}
			}
			
			//travel the room map
			int[] cache = new int[N * N + 1]; //connection length that can be reached from each index
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int num = room[i][j]; //current room
					//this room is already visited
					if (cache[num] > 0) continue;
					
					//if didn't visit this room
					int cnt = 1; //nums of connected room(-> conti. updated, will be length)
					deque.clear();
					deque.addLast(num);
					
					int ni = i, nj = j;
					
					search: //Move rooms until you can't climb any more (num & cnt is updated) 
					while (true) {
						for (int k = 0; k < 4; k++) {
							int r = ni + di[k];
							int c = nj + dj[k];
							if (room[r][c] == num + 1) { //if two rooms are connected, move to connected room
								ni = r;
								nj = c;
								num++;
								if (cache[num] > 0) { //if num is connected before
									cnt += cache[num];
									break search;
								} else {
									deque.add(num); //remember last room num
									cnt++;
									continue search;
								}
							}//if
						}//for
						break;
					}
					
					//update length that can be reached from each index
					for (int k : deque) {
						cache[k] = cnt--;
					}
				}
			}
			
			//find maxLen and its room num and print output
			int maxLen = -1;
			int minRoom = -1;
			for (int i = 1, lim = N * N; i <= lim; i++) {
				if (cache[i] > maxLen) {
					maxLen = cache[i];
					minRoom = i;
					}
				}
			sb.append("#"+tc+" "+minRoom+" "+maxLen+"\n");
			}//for each test case
		System.out.println(sb.toString());
	}//main
}