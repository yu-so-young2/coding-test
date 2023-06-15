import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


class Charger {
	public int x, y, range, power;
	public boolean used;
	
	Charger(int x, int y, int range, int power){
		this.x = x;
		this.y = y;
		this.range = range;
		this.power = power;
		this.used = false;
	}
	
	public boolean isRange(int i, int j) { //해당 위치가 해당 충전기의 범위에 해당하는지
		int distance = Math.abs(this.x-i)+Math.abs(this.y-j);
		if(distance <= this.range) return true;
		return false;
	}

}

public class Solution_SWEA_5644_무선충전 {
	static int mapSize = 10;
	static int T, totalT, nCharger, result;
	static int x, y, range, power;
	static int ax, ay, bx, by;
	static int userMap[][];
	static Charger[] chargers;
	
	static int userA[], userB[];
	
	
	public static void moveA(int cmd) {
		//이동, 이전 위치는 (x, y)에 저장되어있음
		switch(cmd) {
		case 0: //이동하지 않음
			break;
		case 1: //위로 이동
			ax--;
			break;
		case 2: //오른쪽으로 이동
			ay++;
			break;
		case 3: //아래로 이동
			ax++;
			break;
		case 4: //왼쪽으로 이동
			ay--;
			break;
		}
	}
	public static void moveB(int cmd) {
		//이동, 이전 위치는 (x, y)에 저장되어있음
		switch(cmd) {
		case 0: //이동하지 않음
			break;
		case 1: //위로 이동
			bx--;
			break;
		case 2: //오른쪽으로 이동
			by++;
			break;
		case 3: //아래로 이동
			bx++;
			break;
		case 4: //왼쪽으로 이동
			by--;
			break;
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0; //테케 초기화
			
			//맵 초기화
			st = new StringTokenizer(br.readLine());
			totalT = Integer.parseInt(st.nextToken()); //총 이동시간
			nCharger = Integer.parseInt(st.nextToken()); //충전기 개수

			//유저 경로 받아오기
			userA = new int[totalT+1];
			userB = new int[totalT+1];
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= totalT; k++) {
				userA[k] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= totalT; k++) {
				userB[k] = Integer.parseInt(st.nextToken());
			}
			userA[0] = 2;//오른쪽으로
			userB[0] = 2;//오른쪽으로
			
		
			
			
			//충전기 맵 초기화
			chargers = new Charger[nCharger];
			for (int i = 0; i < nCharger; i++) {
				//충전기 정보 받아오기(위치, 범위, 성능)
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				range = Integer.parseInt(st.nextToken());
				power = Integer.parseInt(st.nextToken());
				Charger bc = new Charger(x, y, range, power);
				chargers[i] = bc;
			}

			
			ax = 1; ay = 0; bx = 10; by = 9; //한칸 왼쪽에서 시작
			//이동 및 충전
			for (int k = 0; k <= totalT; k++) {				
				//사용자 둘 다 이동(ax, ay, bx, by)
				moveA(userA[k]);
				moveB(userB[k]);
				//A충전기 선택
				//현재 위치에서 접속 가능한 배터리 목록 저장
				ArrayList<Charger> qA = new ArrayList<>();
				//접속 가능한 배터리 모두 가져오기
				for (int i = 0; i < nCharger; i++) {
					if(chargers[i].isRange(ax, ay)) { //현재 위치가 충전 범위라면 추가
						qA.add(chargers[i]);
					}
				}
				
				//접속 가능한 배터리가 있다면 충전할 것
				//성능 큰 순서대로 정렬
				Collections.sort(qA, new Comparator<Charger>() {
					@Override
					public int compare(Charger o1, Charger o2) {
						// TODO Auto-generated method stub
						return o2.power-o1.power;
					}
				});
				
				
				//B충전기 선택
				//현재 위치에서 접속 가능한 배터리 목록 저장
				ArrayList<Charger> qB = new ArrayList<>();
				//접속 가능한 배터리 모두 가져오기
				for (int i = 0; i < nCharger; i++) {
					if(chargers[i].isRange(bx, by)) { //현재 위치가 충전 범위라면 추가
						qB.add(chargers[i]);
					}
				}
				Collections.sort(qB, new Comparator<Charger>() {
					@Override
					public int compare(Charger o1, Charger o2) {
						// TODO Auto-generated method stub
						return o2.power-o1.power;
					}
				});

				
				if(!qA.isEmpty() && qB.isEmpty()) result += qA.get(0).power;
				else if(qA.isEmpty() && !qB.isEmpty()) result += qB.get(0).power;
				else if(!qA.isEmpty() && !qB.isEmpty()) {
					//충전기 겹침 보정
					//다른 걸 선택했다면 그대로 더하기
					if(!qA.get(0).equals(qB.get(0))) {
						result += (qA.get(0).power+qB.get(0).power);
					}
					//만약 서로 같은 걸 선택했다면
					else {
						//둘 다 선택권이 그것 뿐이면 절반씩
						if(qA.size()==1 && qB.size()==1) {
							result += qA.get(0).power;
						}
						else if(qA.size()==1 && qB.size()!=1) {
							result += qA.get(0).power;
							result += qB.get(1).power;
						}
						else if(qA.size()!=1 && qB.size()==1) {
							result += qB.get(0).power;
							result += qA.get(1).power;
						}
						//둘다 차선책이 있으면 둘 중 2순위가 높은 애가 1순위 양보해줌
						else if(qA.size()!=1 && qB.size()!=1) {
							if(qA.get(1).power >= qB.get(1).power) {
								result += (qA.get(1).power + qB.get(0).power);
							} else {								
								result += (qA.get(0).power + qB.get(1).power);
							}
						}						
					}
				}
				
				
			}
			
			
			bw.write("#"+tc+" "+result+"\n");
		}
		bw.flush();
		bw.close();
	}

}
