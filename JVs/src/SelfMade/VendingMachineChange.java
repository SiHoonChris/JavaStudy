package SelfMade;

public class VendingMachineChange{

	public static void main(String[] args){
		// 음료수 가격(price), 지불(pay)
		// 잔돈(change)은 동전으로만 출력(500, 100, 50, 10)
		// 잔돈 반환 시, 500원은 최대 10개, 100원은 최대 30개. 나머지는 제한 없음
		int price= 2200, pay=4000;  
		int change=pay-price;
		int w500=0, w100=0, w50=0, w10=0;
		System.out.println("가격 : "+price+", 지불 : "+pay+"원");
		
		if(pay>10000) {
			System.out.println("1만원 이하로 넣으세요");
			System.exit(0);
		} else {
			if(pay%10!=0) {
				System.out.println("최소 단위가 10원인데???");
				System.exit(0);
			}
		}
		
		if(price > pay) {
			change=-change;
			System.out.println("잔액 부족 : "+change+"원");
		} else {	
			System.out.println("거스름돈 : "+change+"원");
			
			if(change/500 < 8) {
				w500=change/500;
				change-=w500*500;
			} else{
				w500=8;
				change-=w500*500;
			}
			if(change/100 < 30) {
				w100=change/100;
				change-=w100*100;
			} else{
				w100=30;
				change-=w100*100;
			}
			w50=change/50;
			w10=(change%50)/10;
			
			System.out.printf("500원/100원/50원/10원 : %d/%d/%d/%d", w500, w100, w50, w10);
		}	
	}
}

/*  출력 예제
[1]
  가격 : 1000, 지불 : 10001원
  1만원 이하로 넣으세요
[2]
  가격 : 1000, 지불 : 10000원
  거스름돈 : 9000원
  500원/100원/50원/10원 : 8/30/40/0
[3]
  가격 : 1000, 지불 : 9999원
  최소 단위가 10원인데???
[4]
  가격 : 1000, 지불 : 9990원
  거스름돈 : 8990원
  500원/100원/50원/10원 : 8/30/39/4
[5]
  가격 : 2200, 지불 : 4000원
  거스름돈 : 1800원
  500원/100원/50원/10원 : 3/3/0/0
*/