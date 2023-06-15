import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Person {
	int idx, friends;
	Person(int idx, int friends) {
		this.idx = idx;
		this.friends = friends;
	}
	
}

public class Main_BOJ_13023_ABCDE_G5 {
	static int N, M, myFriends[], min;
	static boolean find = false;
	static ArrayList<Person> people;
	
	public static void dfs(int node, int depth, boolean[] visited, ArrayList<Integer>[] connection) {
		//찾으면 탈출
		if(depth == 4) {
			find = true;
			System.out.println(1);
			System.exit(0);
			return;
		}
		
		for (int i = 0; i < connection[node].size(); i++) {
			if(!visited[connection[node].get(i)]) {
				visited[connection[node].get(i)] = true;
				dfs(connection[node].get(i), depth+1, visited, connection);
				visited[connection[node].get(i)] = false;
			}
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//initialize connection
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		myFriends = new int[N];
		ArrayList<Integer>[] connection = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			connection[i] = new ArrayList<Integer>();
		}
		
		people = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			people.add(new Person(i, 0));
		}
		
		for (int i = 0; i < M; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//연결
			connection[a].add(b);
			connection[b].add(a);
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
		
				
		boolean[] visited = new boolean[N];

		//친구 없는 순서대로 dfs 확인(찾으면 함수 내에서 바로 종료)
		for (int i = 0; i < people.size(); i++) {
			visited[people.get(i).idx] = true;
			dfs(people.get(i).idx, 0, visited, connection);
			visited[people.get(i).idx] = false;
		}

		
		//결과 출력 
		System.out.println(0);
		
		
	}//main
}
