package SelfMade;
import java.util.Scanner;
import java.util.Arrays;
//-----------------------------------------------------//
//                   class PhoneBook
//-----------------------------------------------------//
class PhoneBook{
	// 연락처 [이름, 전화번호]
	static String[][] Book = new String[3][2];
	String name;                    // 이름
	String number;                  // 전화번호
	boolean call;                   // 발신
	boolean receive;                // 수신
	boolean operate=true;           // 프로그램 작동
	boolean full;                   // 잔여 저장 공간 0
	
	// 연락처 저장
	void save() {
		int cnt=0;
		while(operate) {
			if(cnt==Book.length) {
				System.out.println("저장 공간 부족");
				full=true;
				break;
			}
			if(full) operate = !operate;
			
			Scanner sc = new Scanner(System.in);
			System.out.print("이름 :  ");
			name = sc.next();
			System.out.print("전화번호 :  ");
			number = sc.next();
		
			for(int i=0 ; i<Book.length ; i++) {
				if(Book[i][0]==null&&Book[i][1]==null) {					
					Book[i][0]=name;
					Book[i][1]=number;
					System.out.printf("저장 성공(%d/%d)\n", cnt, Book.length);
					cnt++;
					break;
				}
			}
	
			System.out.println("계속?(1.계속, 2.종료)");
			System.out.print("=>  ");
			int answer = sc.nextInt();		
			if(answer==2) operate = !operate;
		}	
	}
	
	
	// 이름으로 연락처 검색(Scanner)
	void search() {}
	void delete() {}                          // 연락처 삭제
	void call()    { call = !call; }          // 전화 걸기
	void receive() { receive = !receive; }    // 전화 받기
}

//-----------------------------------------------------//
//               public class CellPhone
//-----------------------------------------------------//
public class CellPhone {
	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		pb.save();
	}
}