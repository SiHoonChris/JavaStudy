package SelfMade;
// 이전 블랙잭(BlackJack.java)보다 발전된
// 1 on 1 ; 딜러 vs. 게이머
// 카드는 조커 제외, 52장. 2~10은 숫자 그대로 , K/Q/J는 10 , A는 1이나 11 / 1-Deck Game
// 딜러와 게이머는 순차적으로 카드를 하나씩 뽑아 각자 2개의 카드를 소지한다.
// 게이머 : 얼마든지 카드를 추가로 뽑을 수 있다.
// 딜러 : 2카드의 합계 점수가 16점 이하이면 반드시 1장 추가, 17점 이상이면 No more card
// 소유한 카드의 합이 21에 가장 가까운 쪽이 승리한다. 단 21을 '초과'하면 초과한 쪽이 진다(Bust)
// 코드 작성 간 주의사항
// 1) 모든 요소는 객체화를 시켜서 관리한다.
// 2) 하나의 메소드에는 하나의 역할만 존재해야한다.

import java.util.Scanner;
import java.util.Arrays;

// ---------- Card ---------- //
// Only One Constructor
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
// Methods : cardsInTheBox , shuffle 
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
class onTheTable{
	Scanner sc = new Scanner(System.in);
	cardDeck cd = new cardDeck();
	int choice=0;
	int score;
	
	// 0. 점수 변환
	private void conversion(String number) {
		switch(number) { 
			case "2" : score=2  ; break;
			case "3" : score=3  ; break;
			case "4" : score=4  ; break;
			case "5" : score=5  ; break;
			case "6" : score=6  ; break;
			case "7" : score=7  ; break;
			case "8" : score=8  ; break;
			case "9" : score=9  ; break;
			case "10": score=10 ; break;
			case "J" : score=10 ; break;
			case "Q" : score=10 ; break;
			case "K" : score=10 ; break;
			case "A" : 
				if(choice==1) {score=1; }
				if(choice==11){score=11;}
				choice=0;
				break;
		}
	}

	// 1. 카드 뿌리기
	public void getYourCards() {
		// 카드 지급할 때마나 count하기; count = Deck의 인덱스
		// 카드가 Dealer배열과 User배열에 입력될 때마다 count증가
		// A에 대한 판단은 사용자 입력(Scanner) 받기.
		// conversion 메서드 대입시켜서, 카드가 주어짐과 동시에 점수 환산/계산해서 같이 전시

		Card[] Dealer = new Card[12]; // 가장 작은 수로만 연달아 받는다 해도, 11장 받으면 21임.
		Card[] User = new Card[12];   // 1+1+1+1 +2+2+2+2 +3+3+3 = 21
		
		for(int i=0; i<=51; i++) {
			System.out.print(cd.Deck[i].pattern);
			System.out.print("-");
			System.out.println(cd.Deck[i].number);
		
		System.out.println("[Dealer] =>  ");
		System.out.println("  [User] =>  ");
		}
	}
	
} // end - class onTheTable

// ---------- Play Here ---------- // Main Class
public class BlackjackADV{
	public static void main(String[] args){
		cardDeck cd = new cardDeck();
		cd.cardsInTheBox();
		cd.shuffle();

			
	}
} // end - public class BlackjackADV

