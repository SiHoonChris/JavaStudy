package SelfMade;
// 코드 작성 간 주의사항
// 1) 모든 요소는 객체화를 시켜서 관리한다.
// 2) 하나의 메소드에는 하나의 역할만 존재해야한다.

import java.util.Scanner;
import java.util.Arrays;

// ---------- Card ---------- //
// Only One Constructor, No Methods
class Card{
	String pattern="";
	String number="";
	
	Card(int x, int y){
		switch(x){
		case 1: pattern="SPADE"; break;
		case 2: pattern="CLOVER"; break;
		case 3: pattern="DIAMOND"; break;
		case 4: pattern="HEART"; break;
		}
		switch(y){
		case 1: number="A"; break;
		case 11: number="J"; break;
		case 12: number="Q"; break;
		case 13: number="K"; break;
		default: number=y+"";
		}
	}
	
} // end - class Card	

// ---------- cardDeck ---------- //
// Methods : cardsInTheBox(), shuffle() 
class cardDeck{
	public static Card[] Deck = new Card[52];
	
	// 1. 카드 생성
	public void cardsInTheBox(){
		int idx=0;
		for(int x=1; x<=4; x++) {
			for(int y=1; y<=13; y++) {
				Deck[idx++] = new Card(x, y);
			}
		}
	}
	
	// 2. 카드 섞기
	public void shuffle(){
		for(int i=1; i<=1000; i++) {
			int r=(int)(Math.random()*52);
			Card temp = Deck[0];
			Deck[0]=Deck[r];
			Deck[r]=temp;
		}
	}
	
} // end - class cardDeck

//---------- onTheTable ---------- //
// Methods : converter(), getYourCards(), whatIsYourAce(), hitOrStay(), whoWins();
class onTheTable{
	int[] gamingDeck = new int[52];
	int[] Dealer = new int[3];
	int[] Player = new int[12];
	int Dealer_scr;
	int Player_scr;
	int choice=0;
	int score;
	
	Scanner sc = new Scanner(System.in);
	cardDeck cd = new cardDeck();
	inTheGame G = new inTheGame();
		
	// 0. 카드 재생성
	public int[] converter() {  // private
		for(int i=0; i<cd.Deck.length; i++) {
			if(cd.Deck[i].number=="K"||cd.Deck[i].number=="Q"||cd.Deck[i].number=="J")
				{ gamingDeck[i]=10; }
			else if(cd.Deck[i].number=="A")
				{ gamingDeck[i]=1; }
			else
				{ int num = Integer.parseInt(cd.Deck[i].number);
				  gamingDeck[i]=num; }
		}
		return gamingDeck;
	}
	
	// 1. 카드 뿌리기
	public void getYourCards() {
//		converter();
		Player[0]=gamingDeck[0];
		Dealer[0]=gamingDeck[1];
		Player[1]=gamingDeck[2];
		Dealer[1]=gamingDeck[3];
		
		// 딜러 파트
		if(Dealer[0]+Dealer[1]<=11 && (cd.Deck[1].number=="A" || cd.Deck[3].number=="A"))
		{   if(cd.Deck[1].number=="A") Dealer[0]=11;
			else if(cd.Deck[3].number=="A") Dealer[1]=11;
			else if(cd.Deck[1].number=="A"&&cd.Deck[3].number=="A") Dealer[0]=11; }
		if(Dealer[0]+Dealer[1]<=16) { Dealer[2]=gamingDeck[4]; }
		if(cd.Deck[4].number=="A" && Dealer[0]+Dealer[1]<11) { Dealer[2]=11; }

		System.out.print("[Dealer] =>  ");
		System.out.print(cd.Deck[1].pattern+"-"+cd.Deck[1].number+"\t");
		System.out.print(cd.Deck[3].pattern+"-"+cd.Deck[3].number +(Dealer[2]!=0 ? "\t" : "\n"));
		if(Dealer[2] != 0) {System.out.print(cd.Deck[4].pattern+"-"+cd.Deck[4].number+"\n");} 
		System.out.printf("=> [%02d]", Arrays.stream(Dealer).sum());
		System.out.println();
		
		// 플레이어 파트
		System.out.print("[Player] =>  ");
		System.out.print(cd.Deck[0].pattern+"-"+cd.Deck[0].number+"\t");
		System.out.print(cd.Deck[2].pattern+"-"+cd.Deck[2].number+"\n");
		if(cd.Deck[0].number=="A"||cd.Deck[2].number=="A") { whatIsYourAce(); }
		
		hitOrStay();

		System.out.printf("=> [%02d]", Arrays.stream(Player).sum());
		System.out.println();
		System.out.print("*RESULT* => ");
		
		// 매치 결과
		whoWins();
	}
	
	// 2. A값 선택
	private void whatIsYourAce() {
		System.out.print("1 or 11 ? =>  ");
		int Answer = sc.nextInt();
		if(cd.Deck[0].number=="A" && Answer==11) { Player[0]=11; }
		else if(cd.Deck[2].number=="A" && Answer==11) { Player[1]=11; }
		else if(cd.Deck[0].number=="A" && cd.Deck[2].number=="A" && Answer==11)
		{ Player[0]=11; }
	}
	private void whatIsYourAce(int n) {
		System.out.print("1 or 11 ? =>  ");
		int Answer = sc.nextInt();
		if(Answer==11) {Player[n]=11;}
		else 		   { return;}
	}
	
	// 3. 플레이어 카드 추가
	private void hitOrStay() {
		if(Dealer[2]==0) {
			int num=2;
			for(int i=4 ; ; i++) {
				System.out.print("HIT or STAY ? =>  ");
				String select = sc.next();
					if(select.equals("HIT")) {		
						Player[num]=gamingDeck[i];
						System.out.print(cd.Deck[i].pattern+"-"+cd.Deck[i].number+"\n");
						if(cd.Deck[i].number=="A") { whatIsYourAce(num); }
						num++;
					} else if(select.equals("STAY")) { return; }
				if(Arrays.stream(Player).sum()>21) { return; }
			}
		}
		else {
			int num=2;
			for(int i=5 ; ; i++) {
				System.out.print("HIT or STAY ? =>  ");
				String select = sc.next();
					if(select.equals("HIT")) {						
						Player[num]=gamingDeck[i];
						System.out.print(cd.Deck[i].pattern+"-"+cd.Deck[i].number+"\n");
						if(cd.Deck[i].number=="A") { whatIsYourAce(num); }
						num++;
					} else if(select.equals("STAY")) { return; }
				if(Arrays.stream(Player).sum()>21) { return; }
			}
		}
	}
	
	// 4. 승무패 결과
	private void whoWins() {
		Dealer_scr=Arrays.stream(Dealer).sum();
		Player_scr=Arrays.stream(Player).sum();
		
		if(Dealer_scr<=21 && Player_scr<=21) {
			if(Dealer_scr>Player_scr) { System.out.println("[Dealer] WIN!"); }
			else if(Dealer_scr<Player_scr) { System.out.println("[Player] WIN!"); }
			else { System.out.println("- DRAW -"); }
		}
		else {
			if(Dealer_scr>21 && Player_scr<=21) { System.out.println("[Player] WIN!"); }
			else if(Player_scr>21 && Dealer_scr<=21) { System.out.println("[Dealer] WIN!"); }
			else { System.out.println("- DRAW -"); }
		}
	}
	
} // end - class onTheTable

//---------- game ---------- //
// Methods : cardsLeftInTheDeck(), renewTheCards()
class inTheGame{
	int cardRemain = cardDeck.Deck.length;
	int cnt = 0; // renewTheCards()에 사용, 한 턴에 사용한 카드 장 수 누적
	onTheTable T;
	
	// 0. 남은 카드 장 수 계산
	public int cardsLeftInTheDeck(int[] Dealer, int[] Player) {
		int dealerUsed=0;
		int playerUsed=0;
		for(int i=0; i<Dealer.length; i++) { if(Dealer[i]!=0) dealerUsed++; }
		for(int i=0; i<Player.length; i++) { if(Player[i]!=0) playerUsed++; }
		
		// 메인메서드에서의 반복문 사용으로 인해, Dealer배열과 Player배열의 Length가 고정되어 변하지 않는 문제 발생. 해결
		for(int i=0; i<Dealer.length; i++) { Dealer[i]=0; }
		for(int i=0; i<Player.length; i++) { Player[i]=0; }

		cardRemain -= (dealerUsed+playerUsed);
		System.out.println("( 남은 카드 : "+cardRemain+" )");	
		
		return dealerUsed+playerUsed;
	}
	
	// 1. 카드 덱 갱신
	public void renewTheCards(int[] ar1, Card[] ar2, int num) { // 세번째 매개변수는 한 턴에 사용한 카드 장 수 
		cnt += num;
		for(int i=0; i<ar1.length; i++) {
			if(i<ar1.length-cnt) {
				ar1[i]=ar1[i+num];
				ar2[i]=ar2[i+num]; }
			else { 
				ar1[i]=0;
				ar2[i]=null;}
		}
	}
	
} // end - public class gameMustGoOn

// ---------- Play Here ---------- // Main Class
public class BlackjackADV{
	
	public static void main(String[] args){
		cardDeck cd = new cardDeck();
		onTheTable T = new onTheTable();
		inTheGame G = new inTheGame();
		
		cd.cardsInTheBox();
		cd.shuffle();
		T.converter();
		int totalCards = cd.Deck.length;
		int matchCnt = 0;
		
		while(true) {		
			matchCnt++;
			System.out.println("==================== Match "+matchCnt+" ====================");
			T.getYourCards();
			int cardsUsed = G.cardsLeftInTheDeck(T.Dealer, T.Player);
			G.renewTheCards(T.gamingDeck, cd.Deck, cardsUsed);
			if(totalCards-cardsUsed<4) {
				System.out.println("\n(카드 수 부족 또는 전체 카드 사용) 게임 종료!");
				break;
			}
		}
	}
} // end - public class BlackjackADV