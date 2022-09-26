package SelfMade;

public class TaxCalculator {     // 종합소득세
	
	public static void main(String[] args) {
		
		double netIncome = Math.random()*2000000000+1, tax=0;
		// 소득 범위 가정 : 1원 이상 ~ 20억원 이하(20억1원 미만)
		char R=' ';
		String criteria="";

		if(netIncome <= 12000000) {
			tax=netIncome*0.06;
			R=1;
		} else if(12000000 < netIncome && netIncome <= 46000000) {
			tax=netIncome*0.15-1080000;
			R=2;
		} else if(46000000 < netIncome && netIncome <= 88000000) {
			tax=netIncome*0.24-5220000;
			R=3;
		} else if(88000000 < netIncome && netIncome <= 150000000) {
			tax=netIncome*0.35-14900000;
			R=4;
		} else if(150000000 < netIncome && netIncome <= 300000000) {
			tax=netIncome*0.38-19400000;
			R=5;
		} else if(300000000 < netIncome && netIncome <= 500000000) {
			tax=netIncome*0.4-25400000;
			R=6;
		} else if(500000000 < netIncome && netIncome <= 1000000000) {
			tax=netIncome*0.42-35400000;
			R=7;
		} else {
			tax=netIncome*0.45-65400000;
		}
		
		switch(R) {
		case 1:
			criteria="1,200만원 이하";
			break;
		case 2:
			criteria="1,200만원 초과 ~ 4,600만원 이하";
			break;
		case 3:
			criteria="4,600만원 초과 ~ 8,800만원 이하";
			break;
		case 4:
			criteria="8,800만원 초과 ~ 1.5억원 이하";
			break;
		case 5:
			criteria="1.5억원 초과 ~ 3억원 이하";
			break;
		case 6:
			criteria="3억원 초과 ~ 5억원 이하";
			break;
		case 7:
			criteria="5억원 초과 ~ 10억원 이하";
			break;
		default:
			criteria="10억원 초과";
		}
		System.out.printf("연 소득 : %,.0f원", netIncome);
		System.out.println();
		System.out.println("종합소득세 과세구간 : "+criteria);
		System.out.printf("%,.0f원", tax);
		System.out.println();
		System.out.printf("(%d원)", (int)tax);
		
	}
}
//오늘 배운거 활용하느라 일부러 길게 작성함!
/* (출력 예시)
연 소득 : 794,829,600원
종합소득세 과세구간 : 5억원 초과 ~ 10억원 이하
298,428,432원
(298428431원) */