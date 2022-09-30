package SelfMade;

public class CompoundInterestScenario {

	public static void main(String[] args) {
		// Q: 매년 'Saving'씩 저축, 연 수익률 'Yield'로 복리 적용, 'Goal'까지 도달하는데 'n'년 걸림?
		// 첫 해부터 수익 발생 가정
		int n=0;
		char p='%';
		java.util.Scanner H = new java.util.Scanner(System.in);
		java.util.Scanner S = new java.util.Scanner(System.in);
		java.util.Scanner Y = new java.util.Scanner(System.in);
		java.util.Scanner G = new java.util.Scanner(System.in);
		
		System.out.print("현재 보유금 : ");
		double Hold = H.nextDouble();
		System.out.print("희망 월 저축 : ");
		double Saving = S.nextDouble();
		Saving *= 12;
		System.out.print("예상 연 수익률('%' 사용 금지) : ");
		double Yield = Y.nextDouble();
		Yield=1+Yield/100;
		System.out.print("희망 금액: ");
		double Goal = G.nextDouble();
		
		System.out.println("========================");
		System.out.printf("현재 보유금 : %,.0f원 \n", Hold);
		System.out.printf("저축(연 환산) : %,.0f원 \n", Saving);
		System.out.printf("예상 연 수익률 : %.2f%s \n", (Yield-1)*100, p);
		System.out.printf("희망 금액: %,.0f원 \n", Goal);		
		System.out.println();
		System.out.println("<시나리오>");
		
		do {
			Hold=(Hold+Saving)*Yield;
			n++;
			System.out.print(n+"년차 : ");
			System.out.printf("%,.0f원 \n",Hold);
			if(Goal<=Hold) {
				break;
			}
		} while(true);
		System.out.println("========================");
		System.out.println(n+"년 예상");
		H.close();
		S.close();
		Y.close();
		G.close();
	}
}

/* 출력 예시
현재 보유금 : 100000000
희망 월 저축 : 2000000
예상 연 수익률('%' 사용 금지) : 20
희망 금액: 10000000000
========================
현재 보유금 : 100,000,000원 
저축(연 환산) : 24,000,000원 
예상 연 수익률 : 20.00% 
희망 금액: 10,000,000,000원 

<시나리오>
1년차 : 148,800,000원 
2년차 : 207,360,000원 
3년차 : 277,632,000원 
4년차 : 361,958,400원 
5년차 : 463,150,080원 
6년차 : 584,580,096원 
7년차 : 730,296,115원 
8년차 : 905,155,338원 
9년차 : 1,114,986,406원 
10년차 : 1,366,783,687원 
11년차 : 1,668,940,424원 
12년차 : 2,031,528,509원 
13년차 : 2,466,634,211원 
14년차 : 2,988,761,053원 
15년차 : 3,615,313,264원 
16년차 : 4,367,175,917원 
17년차 : 5,269,411,100원 
18년차 : 6,352,093,321원 
19년차 : 7,651,311,985원 
20년차 : 9,210,374,382원 
21년차 : 11,081,249,258원 
========================
21년 예상
*/



