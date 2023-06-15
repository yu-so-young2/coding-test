import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 1873. 상호의 배틀필드
 * 단순 구현, 2차원 배열 시뮬레이션
 * 
 */

public class Solution_SWEA_1873_상호의배틀필드 {
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];
			
			//map initialize
			int[] tank = new int[3]; //tank_i, tank_j, tank_dir;
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					//if find tank, save its location and direction
					if(str.charAt(j) == '^') { tank[0] = i; tank[1] = j; tank[2] = UP;}
					if(str.charAt(j) == '>') { tank[0] = i; tank[1] = j; tank[2] = RIGHT;}
					if(str.charAt(j) == 'v') { tank[0] = i; tank[1] = j; tank[2] = DOWN;}
					if(str.charAt(j) == '<') { tank[0] = i; tank[1] = j; tank[2] = LEFT;}
				}
			}
			
			//command perform
			int N = Integer.parseInt(br.readLine());
			String command = br.readLine();
			for (int i = 0; i < N; i++) { // for every command
				switch(command.charAt(i)) {
				case 'U':
					map[tank[0]][tank[1]] = '^';
					tank[2] = UP;
					if( tank[0] > 0 && isGound(map, tank[0]-1, tank[1])) {
						tank = advance(map, tank);
					}
					break;
				case 'R':
					map[tank[0]][tank[1]] = '>';
					tank[2] = RIGHT;
					if( tank[1] < W-1 && isGound(map, tank[0], tank[1]+1)) {
						tank = advance(map, tank);
					}
					break;
				case 'D':
					map[tank[0]][tank[1]] = 'v';
					tank[2] = DOWN;
					if( tank[0] < H-1 && isGound(map, tank[0]+1, tank[1])) {
						tank = advance(map, tank);
					}
					break;
				case 'L':
					map[tank[0]][tank[1]] = '<';
					tank[2] = LEFT;
					if( tank[1] > 0 && isGound(map, tank[0], tank[1]-1)) {
						tank = advance(map, tank);
					}
					break;
				case 'S':
					shoot(map, tank);
					break;
				default:
				} //switch-case end
			} //for each command perform
			
			//map 출력
			bw.write("#"+(testcase+1)+" ");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					bw.write(map[i][j]);									
				}
				bw.write("\n");
			}
		}// for each Test Case
		bw.close();
	} //main
	
	
	public static boolean isGound(char[][] map, int i, int j) {
		if(map[i][j] == '.') return true;
		return false;
	}

	//현재 전차 위치를 평지로 바꾸고 해당 방향으로 전차 이동
	public static int[] advance(char[][] map, int[] tank) {
		map[tank[0]][tank[1]] = '.'; //평지로 바꾸기'
		switch(tank[2]) {
		case UP:
			map[tank[0]-1][tank[1]] = '^'; //전차 이동
			tank[0]--;
			break;
		case RIGHT:
			map[tank[0]][tank[1]+1] = '>'; //전차 이동
			tank[1]++;
			break;
		case DOWN:
			map[tank[0]+1][tank[1]] = 'v'; //전차 이동
			tank[0]++;
			break;
		case LEFT:
			map[tank[0]][tank[1]-1] = '<'; //전차 이동
			tank[1]--;
			break;
		}
		return tank;
	}

	public static void shoot(char[][] map, int[] tank) {
		int dx = 0, dy = 0;
		switch(tank[2]) {
		case 0: dx = -1; dy = 0; //up
			break;
		case 1: dx = 0; dy = 1;//right
			break;
		case 2: dx = 1; dy = 0;//down
			break;
		case 3: dx = 0; dy = -1;//left
		}

		//밖으로 나가거나 벽돌에 만나거나 강철을 만날 때까지 이동	
		while(tank[0]+dx >= 0 && tank[0]+dx < map.length
				&& tank[1]+dy >= 0 && tank[1]+dy < map[0].length
				&& (map[tank[0]+dx][tank[1]+dy] != '#' && map[tank[0]+dx][tank[1]+dy] != '*')) {
			if(tank[2] == 0) dx--;
			else if(tank[2] == 1) dy++;
			else if(tank[2] == 2) dx++;
			else dy--;		
		}
		
		//벽돌 파괴 및 평지로 변경
		if(tank[0]+dx >= 0 && tank[0]+dx < map.length
				&& tank[1]+dy >= 0 && tank[1]+dy < map[0].length)
		if(map[tank[0]+dx][tank[1]+dy] == '*') {
			map[tank[0]+dx][tank[1]+dy] = '.';
		}
	}
}