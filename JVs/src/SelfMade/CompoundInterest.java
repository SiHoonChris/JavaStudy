package SelfMade;

public class CompoundInterest {
	public static void main(String[] args) {
		int Savings = 10000000;
		float Interest = 0.02F;
		int Year = 0;
		
		int S = Savings;
		float I = Interest;
		
		for(int n=Year; n<=10; n = n+1) {
			System.out.print(n+"년 : ");
			System.out.println((int)(S*Math.pow((1+I),n))+"원");
		}
	}
}

/* 출력결과
0년 : 10000000원
1년 : 10199999원
2년 : 10403999원
3년 : 10612079원
4년 : 10824320원
5년 : 11040806원
6년 : 11261622원
7년 : 11486855원
8년 : 11716592원
9년 : 11950923원
10년 : 12189941원 */
