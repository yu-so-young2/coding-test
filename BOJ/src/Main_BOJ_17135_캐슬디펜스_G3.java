import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_BOJ_17135_캐슬디펜스_G3 {
	static int R, C, D, maxKill = 0;
	static int temp[];
	static ArrayList<Enermy> saveEnermies, enermies;
	static ArrayList<Archer> archers;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		//적 정보(위치) 저장
		saveEnermies = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				if(Integer.parseInt(st.nextToken())==1) { //적 발견, 객체 생성
					saveEnermies.add(new Enermy(i, j));
				}
			}
		}
		
		temp = new int[3]; //0~C-1 중에 3개 골라서 저장할 배열
		comb(0, 0);
		System.out.println(maxKill);
		
	}//main
	
	//궁수 3명의 위치(열) 생성
	public static void comb(int idx, int cnt) throws CloneNotSupportedException {
		if(cnt == 3) {
			//조합 생성 끝
			//현재 생성된 조합으로 시뮬레이션 돌렸을 때 kill 값 계산 후 맥스값 갱신
			maxKill = Math.max(maxKill, simulation(temp));
			return;
		}
		for (int i = idx; i < C; i++) {
			temp[cnt] = i;
			comb(i+1, cnt+1);
		}
	}//combination
	
	
	public static int simulation(int[] temp) throws CloneNotSupportedException {
		int curKill = 0;
		//battle field setting!
		//1) saveEnermies 따라서 적 생성
		enermies = new ArrayList<>();
		for (Enermy e : saveEnermies) {
			enermies.add(e.clone());
		}
		//2) temp 순서대로 궁수 생성
		archers = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			archers.add(new Archer(R, temp[i]));
		}
		
		//배틀필드에 적이 아무도 안남아있을때까지
		while(!enermies.isEmpty()) {
			//A. 궁수 공격
			for (Archer a : archers) {
				//사정거리 내에 있는 가장 가까운 적 찾기
				Enermy e = closestEnermy(a);
				//그 적 공격
				if(e == null) continue; //사정거리 내 적 못찾았으면 공격 포기
				e.attacked = true;
			}//A
			
			//B. 적 처리 (remove를 할 것이므로 iterator 사용)
			for (Iterator<Enermy> itr = enermies.iterator(); itr.hasNext();) {
				Enermy e = itr.next();
				//공격받은 애들 삭제하고 kill++
				if(e.attacked) {
					itr.remove();
					curKill++;
				}
				//공격 안 받은 애들 한 칸씩 아래로
				else {
					e.r++;
					//필드 밖으로 밀려났으면 제거(kill 갱신은 안함)
					if(e.r == R) {
						itr.remove();;
					}
				}
			}//B
			
		}
		
		return curKill;
	}
	
	public static int distance(Person a, Person b) {
		return Math.abs(a.r-b.r)+Math.abs(a.c-b.c);
	}
	
	public static Enermy closestEnermy(Archer a) {
		Enermy closest = null;
		int d = Integer.MAX_VALUE;
		for (Enermy e : enermies) {
			if(distance(a, e) > D) continue;
			
			//현재 가장 가까운 적보다 더 가까운 적 발견 -> 아묻따 갱신
			if(distance(a, e) < d) {
				closest = e;
				d = distance(a, e);
			}
			//현재 가장 가까운 거리와 동일한 위치의 적 발견 -> 새로 발견한 애가 더 왼쪽에 있을 때만 갱신
			else if(distance(a, e)==d) {
				if(e.c < closest.c) {
					closest = e;
					d = distance(a, e);
				}
			}
		}
		
		return closest;
	}

}//class

class Person{
	public int r, c;
	Person(int r, int c) {
		this.r = r;
		this.c = c;
	}

}


class Enermy extends Person implements Cloneable {
	public boolean attacked;
	Enermy(int r, int c){
		super(r, c);
		attacked = false;
	}
    @Override
    protected Enermy clone() throws CloneNotSupportedException {
    	return (Enermy) super.clone();    
    }
}

class Archer extends Person {
	Archer(int r, int c) {
		super(r, c);
	}
}


/* 
 * 1) 적 정보 저장
 * 2) 궁수 위치 조합 생성
 * 3) 각 조합마다 시뮬레이션 돌려서 maxKill 갱신
 *   ~ inside simulation ~
 *   1 적 위치 초기화(저장된 정보 불러오기), 궁수 위치 초기화
 *   2 궁수마다 사정거리 내&&가장 가까이에 있는 적 공격
 *   3 적 상태 갱신(공격받은 애들 삭제하면서 kill++, 공격 안 받은 애들 이동, 인덱스 벗어난 애들 삭제)
 *   4 필드에 적이 남아있는 한 2->3 반복
 *   5 반복 끝나고 현재 시뮬의 결과: kill
 * 4) 각 시뮬레이션 끝났으면 Math.max(maxKill, kill) 갱신
*  5) 모든 시뮬 끝났으면 maxKill 출력
 */