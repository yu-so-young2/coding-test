package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_17472_다리만들기2_G1 {
	static int[][] map, mst; //지도 정보, mst 거리 저장 배열
	static int H, W;
	
	static int[] parent;
	
	static boolean visited[][];

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		
		//map 정보 받기
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//로직
		//1. 섬 번호 부여(각 섬을 구분하기 위함) <= dfs 활용, O(N^2)
		//2. 각 섬의 최단 거리 구하기 
		//3. MST(최소 스패닝 트리) 이용하여 최소 간선 거리 구하기
		
		
		//섬 번호 부여(dfs 수행)
        visited = new boolean[H][W];
        int islandNum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                //섬 + 아직 방문 안한 곳으로만 진행
                if(map[i][j]==1 && !visited[i][j]) {
                    islandNum++;
                    visited[i][j] = true;
                    setIslandNum(i, j, islandNum);
                }
            }
        }
		
  
		//각 섬 최소 거리 구하기
        //일단 모든 거리 무한으로 초기화
        //각 섬의 점에서 상하좌우로 가며 섬까지의 거리 갱신
		mst = new int[islandNum+1][islandNum+1];
		for (int i = 0; i <= islandNum; i++) {
			for (int j = 0; j <= islandNum; j++) {
				mst[i][j] = Integer.MAX_VALUE;
			}
		}
        
        for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==0) continue; //바다라면 그냥 건너기
				
				int startIsland = map[i][j]; //현재 섬 번호
				
				//각 섬 한 칸마다 상하좌우에 대하여 다리 지어보기
				for (int k = 0; k < 4; k++) { //상하좌우
					int ny = i+dy[k];
					int nx = j+dx[k];
					int distance = 0;
					
					//다른 섬에 도달하거나 인덱스 밖으로 나갈 때까지
					while(true) {
						//인덱스 밖 점검 => 현재 방향 탐색 중단
						if(ny<0||nx<0||ny>=H||nx>=W) break;
						
						//자기 자신으로 향하는 다리 점검 => 현재 방향 탐색 중단
						if(map[ny][nx]==startIsland) break;
					
						//바다라면 한 칸 전진
						if(map[ny][nx]==0) {
							distance++;
						
							ny += dy[k];
							nx += dx[k];

							continue;
						}
						
						
						//다른 섬에 도달했다면
						//거리가 1이하면 무한으로 취급, 다리 건설X
						if(distance<2) break;
						
						//각 섬 사이의 다리 거리 갱신
						int destIsland = map[ny][nx];
						
						mst[startIsland][destIsland] = Math.min(mst[startIsland][destIsland], distance);
						break;
						
					}//각 방향에서의 다리 건설
				}//네 가지 방향 다리 건설 끝
			}
		}//for
		
		
        //mst -> pq에 넣기
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        for (int i = 1; i <= islandNum; i++) {
			for (int j = i; j <= islandNum; j++) {
				//무한 거리 그냥 무시
				if(mst[i][j] == Integer.MAX_VALUE) continue;
				
				pq.add(new Bridge(i,j,mst[i][j]));
			}
		}
        
        if(pq.size() < islandNum-1) {
        	System.out.println(-1);
        	return;
        }
        
        
		//mst 최소 간선 길이 구하기
        //간선의 개수가 작기 때문에 크루스칼 이용
        int edges = 0;
        int total = 0;
        
        parent = new int[islandNum+1];
        for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
        
        while((edges < islandNum-1)&&!pq.isEmpty()) {
            Bridge cur = pq.poll(); //가중치가 가장 작은 간선
            // 부모노드가 다를때만 (사이클X)
            if(find(cur.start) != find(cur.dest)) {
            	union(cur.start, cur.dest); //연결(합집합)
                total += cur.distance; //다리 길이 추가
                edges++; //간선 카운트
            }
        }
        
        //불가능 경우 보정
        total = edges==islandNum-1?total:-1;
        
        System.out.println(total);
		
	}//main
	

	
    //합집합
    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        
        if(p1 < p2)
            parent[p2] = p1;
        else
            parent[p1] = p2;
    }
    
    //부모 노드 찾기
    public static int find(int n) {
        if(parent[n] == n) {
        	return n;
        }   
        return parent[n] = find(parent[n]);
    }
	
	static class Bridge implements Comparable<Bridge>{
		int start, dest, distance;

		public Bridge(int start, int dest, int distance) {
			super();
			this.start = start;
			this.dest = dest;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Bridge b) {
	        return distance - b.distance;
	    }

		@Override
		public String toString() {
			return "Bridge [start=" + start + ", dest=" + dest + ", distance=" + distance + "]";
		}
		
		
	}

    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};

    //dfs 수행하며 섬 번호 부여하는 함수
    public static void setIslandNum(int r, int c, int islandNum){
        //섬 번호 부여
        map[r][c] = islandNum;

        for (int i = 0; i < 4; i++) {
            int ny = r+dy[i];
            int nx = c+dx[i];

            //IndexOutOfBounds 검사
            if(ny<0 || nx<0 || ny >= H || nx>= W) continue;

            //섬 + 아직 방문 안한 곳으로만 진행
            if(map[ny][nx]==1 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                setIslandNum(ny, nx, islandNum);
            }

        }
    }
}
