import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_6806_규영이와인영이의카드게임 {
	static final int WIN = 1;
	static final int DRAW = 0;
	static final int LOSE = -1;

	static boolean[] deck;
	static int[] opponent; //opponent's card deck
	static int[] my; //my card deck
	
	static boolean[] visited; //for check if visited
	static int[] temp; //temp permutation array
	static int win, lose; //count win, lose
	
	public static void permutation(int depth) {	
		//permutation end, match
        if (depth == 9){
        	switch(match(opponent, temp)) {
        	case WIN: win++; break;
        	case LOSE: lose++; break;
        	}
        	return;
	}

        //continue making permutation
        for (int i = 0; i < my.length; i++){
        	if (!visited[i]){
        		temp[depth]=my[i];
        		visited[i] = true;
        		permutation(depth+1);
        		visited[i] = false;
        	}
        }
	}
	
		
	public static int match(int[] arr1, int[] arr2) {
		int sum1 = 0, sum2 = 0;
		int a, b;

		for (int i = 0; i < arr1.length; i++) {
			a = arr1[i];
			b = arr2[i];
			if(a>b) sum1 += a+b;
			else sum2 += a+b;
		}
		if(sum1>sum2) return WIN;
		else if (sum1 < sum2) return LOSE;
		return DRAW;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
				
		int T = Integer.parseInt(br.readLine()); //num of test case
		
		for (int tc = 1; tc <= T; tc++) {
			win = 0; lose = 0;
			deck = new boolean[19];
			opponent = new int[9];
			my = new int[9];

			//opponent's 9 cards
			st = new StringTokenizer(br.readLine()); 
			for (int i = 0; i < 9; i++) {
				int newCard = Integer.parseInt(st.nextToken());
				opponent[i] = newCard;
				deck[newCard] = true;
			}
			
			//setting my deck
			for (int i = 1, b = 0; i <= 18; i++) {
				if(!deck[i]) my[b++] = i;
			}
			
			//get permutation
			visited = new boolean[9];
			temp = new int[9];
			permutation(0);

			//print output
			sb.append("#"+tc+" "+win+" "+lose+"\n");
		}//for each test case
		System.out.println(sb.toString());
	}//main
}//end class