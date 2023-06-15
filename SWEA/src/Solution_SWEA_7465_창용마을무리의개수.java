import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Solution_SWEA_7465_창용마을무리의개수 {
	static int T, N, M, min;
	static boolean connection[][];
	static boolean visited[];
	static ArrayList<Person> people;
	
	static class Person {
		int idx, friends;
		Person(int idx, int friends) {
			this.idx = idx;
			this.friends = friends;
		}
		
	}

	public static void dfs(int node, int depth) {
		//방문 체크
		for (Person p : people) {
			if(p.idx == node) {
				people.remove(p);
				break;
			}
		}
		

		for (int i = 0; i < connection.length; i++) {
			if(connection[node][i] && !visited[i]) {
				visited[i] = true;
//				System.out.println("dfs("+i+", "+(depth+1)+")");
				dfs(i, depth+1);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			connection = new boolean[N][N];
			//initialize connection
			people = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				people.add(new Person(i, 0));
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				//연결
				connection[a][b] = true;
				connection[b][a] = true;
				//친구 수 업데이트
				people.get(a).friends++;
				people.get(b).friends++;
			}
			//친구 없는 순서로 정렬
			Collections.sort(people, new Comparator<Person>() {
				@Override
				public int compare(Person o1, Person o2) {
					return o1.friends-o2.friends;
				}
			});
			
			//모든 아이 확인할 때까지 계속 dfs 수행
			int group = 0;
			visited = new boolean[N];
			while(!people.isEmpty()) {
				group++;
				min = people.get(0).idx; //남은 아이 중 친구가 가장 없는 아이의 번호
				visited[min] = true;
				dfs(min, 0);				
			}
			sb.append("#"+tc+" "+group+"\n");
		} //for test case
		System.out.println(sb);
	}//main
}
