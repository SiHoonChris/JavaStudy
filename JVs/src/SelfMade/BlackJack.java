package SelfMade;

public class BlackJack {

	public static void main(String[] args) {
		// 카드 문양 무시  /  A는 11로만 적용  /  K, Q, J는 10으로
		// 딜러는 카드 두 장만 받음 (추가 카드 X)  /  참여자는 총합이 21을 넘지 않을(미만) 시 자동으로 카드 한 장 추가 
		// 동점 시, 딜러 승  /  둘 다 버스트 시, 딜러 승
		System.out.println("< BlackJack >");
		
		int dealerCard1=(int)(Math.random()*11+1);
		int dealerCard2=(int)(Math.random()*11+1);
		int yourCard1=(int)(Math.random()*11+1);
		int yourCard2=(int)(Math.random()*11+1);
		int yourCard3=(int)(Math.random()*11+1);
		int dealerSum=dealerCard1+dealerCard2;
		int yourSum=yourCard1+yourCard2;
		
		
		System.out.printf("   Dealer :  %d , %d (%d)", dealerCard1, dealerCard2, dealerSum);
		System.out.println();
		System.out.printf("   You :  %d, %d, %d (%d)", yourCard1, yourCard2, yourSum>=21?0:yourCard3, 
				yourSum>=21?yourSum:yourSum+yourCard3); //****
		System.out.println();
		
		
		yourSum = yourSum>=21?yourSum:yourSum+yourCard3;
		if(dealerSum>=yourSum){
			if(dealerSum>21) {
				System.out.println("=> You WIN!");
			} else {
				System.out.println("=> Dealer WIN!");
			}
		} else {
			if(yourSum>21) {
				System.out.println("=> Dealer WIN!");
			} else {
				System.out.println("=> You WIN!");
			}
		}	
	}
}

/* 출력 예제
< BlackJack >
Dealer :  9 , 10 (19)
You :  11, 10, 0 (21)
=> You WIN!

< BlackJack >
   Dealer :  9 , 5 (14)
   You :  4, 4, 11 (19)
=> You WIN!
*/
