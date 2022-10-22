package SelfMade;
// 코드 작성 간 주의사항
// 1) 모든 요소는 객체화를 시켜서 관리한다.
// 2) 하나의 메소드에는 하나의 역할만 존재해야한다.

// To do...
// 플레이어의 Hit&Stay 선택 시, 딜러의 카드가 2장인지 3장인지에 따라 코드 달라져야 할 것 - 아니면 다른 무언가를 더 하거나
// 카드가 52장 다 소모될 때까지 반복하기
// 게임 종료 멘트 & 게임 중 현황 표시(몇 번째 턴인지, 현재 전적, 남은 카드 장 수)

import java.util.Scanner;
import java.util.Arrays;

// ---------- Card ---------- //
// Only One Constructor, No Methods
class Card{
	String pattern=""; // SPADE, CLOVER, DIAMOND, HEART (4)
	String number="";  // A, 2~10, J, Q, K             (13)
	
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
// Methods : converter(), getYourCards(), whatIsYourAce()
class onTheTable{
	int[] gamingDeck = new int[52];
	int[] Dealer = new int[12];
	int[] Player = new int[12];
	int Dealer_scr;
	int Player_scr;
	
	Scanner sc = new Scanner(System.in);
	cardDeck cd = new cardDeck();
	int choice=0;
	int score;
	
	// 0. 카드 재생성
	// 점수 계산에 pattern 무쓸모. 그렇기에 number 내용만 가지고 덱(gamingDeck) 재생성
	private void converter() {
		for(int i=0; i<cd.Deck.length; i++) {
			if(cd.Deck[i].number=="K"||cd.Deck[i].number=="Q"||cd.Deck[i].number=="J")
				{ gamingDeck[i]=10; }
			else if(cd.Deck[i].number=="A")
				{ gamingDeck[i]=1; } // A에 대해서는 따로 작업 더 들어갈 예정(사용자 입력)
			else
				{ int num = Integer.parseInt(cd.Deck[i].number);
				  gamingDeck[i]=num; }
		}	
	}
	// 1. 카드 뿌리기
	public void getYourCards() {
		// 카드 전환 : Deck => gamingDeck
		converter();
		// 카드는 순서대로 번갈아 지급
		Player[0]=gamingDeck[0];
		Dealer[0]=gamingDeck[1];
		Player[1]=gamingDeck[2];
		Dealer[1]=gamingDeck[3];
		
		// 딜러 파트
		// A에 대한 값 선택은 최대한 유리하게.
		if(Dealer[0]+Dealer[1]<=11 && (cd.Deck[1].number=="A" || cd.Deck[3].number=="A"))
		{   if(cd.Deck[1].number=="A") Dealer[0]=11;
			else if(cd.Deck[3].number=="A") Dealer[1]=11;
			else if(cd.Deck[1].number=="A"&&cd.Deck[3].number=="A") Dealer[0]=11; }
		// A값 설정 후 2장의 카드 점수 합계가 16점 이하이면 1장 추가(Hit), 17점 이상이면 Stay
		if(Dealer[0]+Dealer[1]<=16) { Dealer[2]=gamingDeck[4]; }
		// 세 번째 카드가 A인 경우
		if(cd.Deck[4].number=="A" && Dealer[0]+Dealer[1]<=11) { Dealer[2]=11; }
		// 카드 내용 출력
		System.out.print("[Dealer] =>  ");
		System.out.print(cd.Deck[1].pattern+"-"+cd.Deck[1].number+"\t");
		System.out.print(cd.Deck[3].pattern+"-"+cd.Deck[3].number +(Dealer[2]!=0 ? "\t" : "\n"));
		if(Dealer[2] != 0) {System.out.print(cd.Deck[4].pattern+"-"+cd.Deck[4].number+"\n");} 
		System.out.printf("=> [%02d]", Arrays.stream(Dealer).sum());
		System.out.println();
		
		// 플레이어 파트  *게이머(플레이어) : 얼마든지 카드를 추가로 뽑을 수 있다.(HIT or STAY)
		// 카드 내용 출력
		System.out.print("[Player] =>  ");
		System.out.print(cd.Deck[0].pattern+"-"+cd.Deck[0].number+"\t");
		System.out.print(cd.Deck[2].pattern+"-"+cd.Deck[2].number+"\n");
		// A값 설정
		if(cd.Deck[0].number=="A"||cd.Deck[2].number=="A") { whatIsYourAce(); }
		// 카드 내용 출력
		System.out.printf("=> [%02d]", Arrays.stream(Player).sum());
		System.out.println();
		
		// 게임 결과 출력
		System.out.println("===============================================");
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
	
	// 3. 승무패 결과
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

// ---------- Play Here ---------- // Main Class
public class BlackjackADV{
	
	public static void main(String[] args){
		cardDeck cd = new cardDeck();
		onTheTable T = new onTheTable();
		cd.cardsInTheBox();
		cd.shuffle();
		
		System.out.println("===============================================");
		T.getYourCards();
		
	}
} // end - public class BlackjackADV


// 카드 확인용 코드(필요시에만 사용, main class에 넣어서 코드 실행)
//T.converter();  //*접근제어자 public으로 전환 후 사용
//for(int i=0; i<=51; i++) {
//	System.out.print(cd.Deck[i].pattern);
//	System.out.print("-");
//	System.out.print(cd.Deck[i].number);
//	System.out.println(" "+T.gamingDeck[i]);
//}




