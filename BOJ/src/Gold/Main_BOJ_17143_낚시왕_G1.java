package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 낚시왕
 * 
 */

public class Main_BOJ_17143_낚시왕_G1 {
	
	static final int UP = 1;
	static final int DOWN = 2;
	static final int RIGHT = 3;
	static final int LEFT = 4;
	
	static class Shark {
		int r, c, dir, velocity, size;

		public Shark(int r, int c, int velocity, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.velocity = velocity;
			this.dir = dir;
			this.size = size;
		}
		
		//이동하면 바뀌는 거: 방향, 자리
		public void move() {
			int ny = this.r;
			int nx = this.c;
			int ndir = this.dir;
			
			
			int toggle = 0;
			switch(this.dir) {

			//상, 하: ny만 변경
			case UP:
				ny -= this.velocity;
				while(!(ny >= 1 && ny <= H)) {
					toggle++;
					if(ny > H) {
						ny = 2*H-ny;
					} else if(ny < 1) {
						ny = 2-ny;
					}
				}
				if((toggle)%2==1) ndir = DOWN;

				break;
			case DOWN:
				ny += this.velocity;
				while(!(ny >= 1 && ny <= H)) {
					toggle++;
					if(ny > H) {
						ny = 2*H-ny;
					} else if(ny < 1) {
						ny = 2-ny;
					}
				}
				if((toggle)%2==1) ndir = UP;

				break;
				
			//좌, 우: nx만 변경
			case LEFT:
				nx -= this.velocity;
				while(!(nx >= 1 && nx <= W)) {
					toggle++;
					if(nx > W) {
						nx = 2*W-nx;
					} else if(nx < 1) {
						nx = 2-nx;
					}
				}
				if((toggle)%2==1) ndir = RIGHT;
				break;
			case RIGHT:
				nx += this.velocity;
				while(!(nx >= 1 && nx <= W)) {
					toggle++;
					if(nx > W) {
						nx = 2*W-nx;
					} else if(nx < 1) {
						nx = 2-nx;
					}
				}
				if((toggle)%2==1) ndir = LEFT;

				break;
			}
			
			this.r = ny;
			this.c = nx;
			this.dir = ndir;
		}

	}
	 
	
	static int H, W, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Shark> sharks = new ArrayList<>();
		Shark[][] sea = new Shark[H+1][W+1];
		
		//상어 정보 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r, c, s, d, z;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			sharks.add(shark);
			sea[r][c] = shark;
		}
		
		
		//W초동안 움직임
		int sizeSum = 0;
		for (int t = 1; t <= W; t++) {
//			System.out.println("try "+ t);
//			for (int i = 1; i < sea.length; i++) {
//				for (int j = 1; j < sea[0].length; j++) {
//					if(sea[i][j] != null)
//					System.out.print(sea[i][j].size+" ");
//					else {
//						System.out.print("0 ");
//					}
//				}
//				System.out.println();
//			}
			
			//1. 낚시왕 이동 및 가까운 상어 잡기
			//현재 낚시왕이 있는 열: t
			for (int i = 1; i <= H; i++) {
				if(sea[i][t] != null) { //가장 위에 있는 상어 발견
					sizeSum += sea[i][t].size;
//					System.out.println("fishing! "+sea[i][t].size);
					sharks.remove(sea[i][t]); //리스트에서 삭제
					sea[i][t] = null; //바다에서 삭제
					break;
				}
			}
			
			//2. 상어 이동
			for (Shark cur : sharks) {

				int i = cur.r;
				int j = cur.c;
//				System.out.println("size: "+cur.size+" 이동");
//					System.out.println("원래: "+i+", "+j+", 방향:"+cur.dir);
				
				//원래 있던 자리 null
				sea[i][j] = null;
					
				//상어 이동
				cur.move();
				
//					System.out.println("바뀐 후: "+cur.r+", "+cur.c+", 방향:"+cur.dir);
			}
			
			
//			System.out.println("here2222");

			ArrayList<Shark> remove = new ArrayList<>();
			//3. 이동한 상어로 맵 다시 변경
			for (Shark cur : sharks) {
				int i = cur.r;
				int j = cur.c;
				
				//배열 이동
				//누군가 있는지 확인
				if(sea[i][j] != null) { //있으면
					if(sea[i][j].size < cur.size) { //내가 더 큰 경우 바꾸기
//						System.out.println("eat!!! "+sea[i][j].size);
						remove.add(sea[i][j]);
						sea[i][j] = cur;
					} else { //내가 더 작으면 나 삭제
//						System.out.println("eaten!!! "+cur.size);
						remove.add(cur);
					}
				} else { //없으면 그대로 추가							
					sea[i][j] = cur;
				}
			}
			
			//잡아먹기
			for (Shark shark : remove) {
				sharks.remove(shark);
			}

		}
		
		System.out.println(sizeSum);
		
	}

}
