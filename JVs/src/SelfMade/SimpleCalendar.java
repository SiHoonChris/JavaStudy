package SelfMade;

public class SimpleCalendar{
// 1월 31 / 2월 28 / 3월 31 / 4월 30 / 5월 31 / 6월 30 / 7월 31
// 8월 31 / 9월 30 / 10월 31 / 11월 30 / 12월 31
	public static void main(String[] args){
		int year=2022;
		// 1~7월
		for(int month=1; month<=7; month++) {
			if(month%2 == 0) {
				if(month==2) {
					System.out.printf(" < %d월 >\n", month);
					for(int date=1; date<=28; date++) {
						System.out.println(year+"-"+month+"-"+date);
					}
					System.out.println();
				} else {
					System.out.printf(" < %d월 >\n", month);
					for(int date=1; date<=30; date++) {
						System.out.println(year+"-"+month+"-"+date);
					}
					System.out.println();
				}
			} else {
				System.out.printf(" < %d월 >\n", month);
				for(int date=1; date<=31; date++) {
					System.out.println(year+"-"+month+"-"+date);
				}
				System.out.println();
			}
		}
		// 8~12월
		for(int month=8; month<=12; month++) {
			if(month%2==0) {
				System.out.printf(" < %d월 >\n", month);
				for(int date=1; date<=31; date++) {
					System.out.println(year+"-"+month+"-"+date);
				}
				System.out.println();
			} else {
				System.out.printf(" < %d월 >\n", month);
				for(int date=1; date<=30; date++) {
					System.out.println(year+"-"+month+"-"+date);
				}
				System.out.println();
			}
		}
		
	}
}