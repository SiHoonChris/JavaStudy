package SelfMade;
// Q: 매년 'Saving'씩 저축, 연 수익률 'Yield'로 복리 적용, 'Goal'까지 도달하는데 'n'년 걸림?
// 첫 해부터 수익 발생 가정

import java.util.Scanner;
//---------------------------------------------------------------------//
// class Calculator
//---------------------------------------------------------------------//
class Calculator{
	// 인스턴스 변수
	double Hold, Saving, Yield, Goal;
	char p='%';
	int n=0;
	
	// 클래스 초기화 블록
	static { System.out.println("<자산 증가 시나리오>"); }  
	// 인스턴스 초기화 블록
	{ System.out.println("1)보유금액, 2)월 저축금액, 3)기대(예상)연 수익률, 4)목표(희망) 금액 입력"); }
	{ System.out.println("※주의! 3)기대(예상)연 수익률 입력 시, '%'기호 빼고 입력 - 예) 10% => 10"); }
	{ Scanner sc = new Scanner(System.in);
	  System.out.print("\n1)보유금액 :  ");
	  Hold = sc.nextDouble();
	  System.out.print("2)월 저축금액 :  ");
	  Saving = sc.nextDouble();
	  System.out.print("3)기대(예상)연 수익률 :  ");
	  Yield = sc.nextDouble();
	  System.out.print("4)목표(희망) 금액 입력 :  ");
	  Goal = sc.nextDouble();
	  sc.close();
	}
	
	// 인스턴스 메서드(return 없는)
	void typeDowns() {
		System.out.println("============================\n");
		System.out.printf("현재 보유금액 : %,.0f원\n", Hold);
		System.out.printf("연 저축 예정 : %,.0f원\n", Saving*12);
		System.out.printf("기대 연 수익률 : %.2f%s\n", Yield, p);
		System.out.printf("희망 금액: %,.0f원\n", Goal);
	}
	
	// 인스턴스 메서드(return 있는)
	int Calculation() {
		System.out.println("\n<계산 결과>");
		Saving *= 12;
		Yield=(100+Yield)/100;
		do {
			Hold=(Hold+Saving)*Yield;
			n++;
			System.out.print(n+"년차 : ");
			System.out.printf("%,.0f원 \n",Hold);
		} while(Goal>Hold);
		return n;
	}
}
//---------------------------------------------------------------------//
// public class CompoundInterestScenario
//---------------------------------------------------------------------//
public class CompoundInterestScenario {
	public static void main(String[] args) {
		Calculator cal = new Calculator();  // 인스턴스 메서드 사용 위해 객체 생성
		cal.typeDowns();
		int year = cal.Calculation();  // return값 year변수로 대입
		System.out.println("\n=> "+year+"년 예상");
		System.out.println("============================");
	}
}

