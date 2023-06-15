import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_SW역테_알고스탁 {
	
	//각 월마다 구매 시 이득을 보는 주식의 정보 저장
	static class Info implements Comparable<Info>{
		int stockNo, profit, price;

		public Info(int stockNo, int profit, int price) {
			super();
			this.stockNo = stockNo;
			this.profit = profit;
			this.price = price;
		}


		public int compareTo(Info o) {
			//해당 주식을 매수함으로써 얻는 이득이 같다면 가격이 더 낮은 것 순서로 구매
			if(o.profit == this.profit) {
				return this.price - o.price;
			}
			return o.profit - this.profit;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int Ms = Integer.parseInt(st.nextToken()); //초기 예치금
			int Ma = Integer.parseInt(st.nextToken()); //월별 불입금액

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //주식 종목 수
			int L = Integer.parseInt(st.nextToken()); //데이터 기간
			
			int origin = Ms + Ma*L; //총 투자 원금(주식 투자 하지 않았을 시)
			
			int[][] stocks = new int[N][L+1]; //과거 데이터 저장
			
			//매 달마다 구매해야 할(구매 시 이득을 얻는)주식 정보 저장할 리스트
			ArrayList<ArrayList<Info>> buyList = new ArrayList<>();
			for (int i = 0; i < L+1; i++) { //개월 수만큼 리스트 생성
				buyList.add(new ArrayList<>());
			}

			//과거 데이터 받아오기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < L+1; j++) {
					stocks[i][j] = Integer.parseInt(st.nextToken());
					if(j != 0) { //주가가 오르는 경우 구매리스트에 넣기
						if(stocks[i][j-1] < stocks[i][j]) {
							int profit = stocks[i][j] - stocks[i][j-1];
							buyList.get(j-1).add(new Info(i, profit, stocks[i][j-1]));
						}
					}
				}//행 입력 완료, 다음 행으로				
			}//과거 데이터 입력 끝

			//구매할 주식 정보 리스트 확인 프린트
//			for (int i = 0; i < buyList.size(); i++) {
//				System.out.print(i+"개월: ");
//				for (Info info : buyList.get(i)) {
//					System.out.print("종목"+info.stockNo+" 이득: "+info.profit+" 가격: "+info.price+")");
//				}
//			}
			
			for (int month = 0; month < L+1; month++) {
				//1개월 이후부터 불입금액 추가
				if(month != 0) {
					Ms += Ma;				
				}
				
				//매수
//				System.out.println(month+"개월 매수 전 잔고: "+Ms);
				//현재 달 기준 구매할 주식 목록 확인 및 정렬(1순위: 이득 높은 순, 2순위: 금액 낮은 순)				
				ArrayList<Info> candidates = buyList.get(month);
				Collections.sort(candidates);
				
				int[] bought = new int[N]; //현재 구매한 주식 종목과 구매 개수  => 매도 시 필요
				
				//가장 높은 이득의 주식 순으로 구매
				for (int j = 0; j < candidates.size(); j++) {
					Info stock = candidates.get(j);
					int buyNum = Ms/stocks[stock.stockNo][month]; //구매 개수
					if(buyNum > 0) { //구매 할 것이라면
//						System.out.println(stock.stockNo+"주식을 "+buyNum+"개 매수");
						Ms -= buyNum * stocks[stock.stockNo][month]; //구매
						bought[stock.stockNo] += buyNum; //구매 내역 저장
					}
				}
				
//				System.out.println(month+"개월 매수 후 잔고: "+Ms);
				
				//매도
//				System.out.println((month+1)+"개월 매도 전 잔고");
				for (int stock = 0; stock < bought.length; stock++) {					
//					System.out.println(stock+"번 주식을 "+brought[stock]+"개 매도");
					if(bought[stock] > 0) {
						Ms += bought[stock]*(stocks[stock][month+1]);						
					}
				}
//				System.out.println((month+1)+"개월 매도 후 잔고");
			}
		
			
			//최대수익: 최대 수익 시 잔액 - 원금
			int totalProfit = Ms - origin;
			
			sb.append("#"+tc+" "+totalProfit+"\n");
		} //test case
		System.out.println(sb);
	}//main

}


