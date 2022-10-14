package SelfMade;
// 코인노래방이나 야구배팅장 가면 있는 동전교환기

class VendingMachine{  // 기계 내 잔돈 보유량
	static final int[] CoinUnit = {500, 100};
	static int[] CoinNumber = new int[2];
	int fiveHundredWon = CoinNumber[0];
	int oneHundredWon = CoinNumber[1];
	
	VendingMachine(){
		for(int i=0; i<CoinNumber.length; i++) {
			CoinNumber[i] = 10;
		}
	}
	VendingMachine(int fiveHundredWon, int oneHundredWon){
		CoinNumber[0]=fiveHundredWon;
		CoinNumber[1]=oneHundredWon;
		this.fiveHundredWon = fiveHundredWon;
		this.oneHundredWon = oneHundredWon;
	}
}

class Change{
	static int[] CoinReturn = new int[2];
	VendingMachine v; 
	int last;
	
	Change(int input){
		if(input/v.CoinUnit[0] > v.CoinNumber[0]) {
			input -= v.CoinUnit[0]*v.CoinNumber[0];
			CoinReturn[0] = v.CoinNumber[0];
			v.CoinNumber[0] = 0;
		} else {
			CoinReturn[0] = input/v.CoinUnit[0];
			v.CoinNumber[0] -= CoinReturn[0];
			input %= v.CoinUnit[0];
 		}
		if(input/v.CoinUnit[1] > v.CoinNumber[1]) {
			input -= v.CoinUnit[1]*v.CoinNumber[1];
			CoinReturn[1] = v.CoinNumber[1];
			v.CoinNumber[1] = 0;
		} else {
			CoinReturn[1] = input/v.CoinUnit[1];
			v.CoinNumber[1] -= CoinReturn[1];
			input %= v.CoinUnit[1];
 		}
		last=input;
	}
}

public class VendingMachineChange{
	
	static { System.out.println("100원짜리 동전 나오면 사장님한테 연락ㄱㄱ (500원 동전 수량 부족한거임)."); }
	static { System.out.println("거스르고 남은 돈은 좋은데 쓰겠습니다.\n"); }

	public static void main(String[] args){
		VendingMachine v = new VendingMachine(8, 10);  // 기계 내 500원짜리 10개, 100원짜리 8개
		System.out.printf("500원 %d개, 100원 %d개 보유 (교환 전)\n", v.CoinNumber[0], v.CoinNumber[1]);
		
		int input = 8000;
		System.out.println(input+"원 (투입)");
		
		Change c = new Change(input); 				   // 만원 넣음
		System.out.printf("%d원 %d개, %d원 %d개 지급 (교환)\n", v.CoinUnit[0], c.CoinReturn[0], v.CoinUnit[1], c.CoinReturn[1]);
		System.out.printf("500원 %d개, 100원 %d개 보유 (교환 후)\n", v.CoinNumber[0], v.CoinNumber[1]);
		System.out.printf("꿀꺽냠냠 : %d원\n", c.last);
	}
}