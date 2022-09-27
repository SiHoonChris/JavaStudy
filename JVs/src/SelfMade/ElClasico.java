package SelfMade;

public class ElClasico {
// Camp Nou에서만 경기한다 가정(원정 다득점: 1,2차전 득점 총합 동점 시, 레알 우승)
	public static void main(String[] args) {
		
		System.out.println("EL CLASICO : Real Madrid vs. FC Barcelona (Camp Nou)");
		System.out.println("");
		
		int RMD=0, FCB=0, endR=0, endB=0;
		int cnt=0;
		String rst="", rst_draw="";
		
		while(true) {
			RMD=(int)(Math.random()*5);
			FCB=(int)(Math.random()*5);
			
			int R=0, B=0;
			if(RMD>FCB) { //경기 결과
				R+=RMD;
				endR+=RMD;
				B+=FCB;
				endB+=FCB;
				rst="Real Madrid ";
			} else if(RMD<FCB) {
				R+=RMD;
				endR+=RMD;
				B+=FCB;
				endB+=FCB;
				rst="FC Barcelona";
			} else {
				R+=0;
				endR+=RMD;
				B+=0;
				endB+=FCB;
				rst_draw="무";
			} //경기 결과
			
			++cnt;
			
			if(RMD==FCB) { //경기 결과 기록
				System.out.printf("(%d차전 결과) %s :             (%d:%d)", cnt, rst_draw, R, B);
				System.out.println();
			} else {
				System.out.printf("(%d차전 결과) 승 : %s(%d:%d)", cnt, rst, R, B);
				System.out.println();
			} //경기 결과 기록
			
			if(cnt==2) { //최종 결과 
				if(endR==endB) {
					System.out.printf("     (우승)   : Real Madrid (%d:%d)", endR, endB);
				} else if(endR>endB) {
					System.out.printf("     (우승)   : Real Madrid (%d:%d)", endR, endB);
				} else {
					System.out.printf("     (우승)   : FC Barcelona (%d:%d)", endR, endB);
				}
				break;
			} //최종 결과
			
		} //while(true)
	} //public static void main(String[] args)
} //public class ElClasico