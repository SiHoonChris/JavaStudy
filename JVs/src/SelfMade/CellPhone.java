package SelfMade;
import java.util.Scanner;
import java.util.Arrays;
//----------------------- class PhoneBook ------------------------//
class PhoneBook{
	String[][] Book = new String[3][2];      // 연락처(이름, 전화번호) 
	String name;                             // 이름
	String number;                           // 전화번호
	boolean call;                            // 발신
	boolean receive;                         // 수신
    
	// 연락처 저장
	void save(String name, String number) {
		try {			
			Scanner sc = new Scanner(System.in);
			System.out.print("이름 : ");
			name = sc.next();
			System.out.print("연락처('-'포함) : ");
			number = sc.next();
			
			Exception e = new Exception("연락처 재확인 바랍니다(저장 실패)");
			if(number.length()!=13) throw e;
			
			int cnt=0;
			for(int i=0; i<Book.length; i++) {
				cnt++;
				if(cnt>Book.length) {						
					System.out.println("저장 공간 부족(저장 실패)");					
					break;
				} else {
					if(Book[i][0]==null && Book[i][1]==null) {
						Book[i][0]=name;
						Book[i][1]=number;
						System.out.println("저장이 완료되었습니다.");
						System.out.printf("남은 공간 : %d/%d\n", Book.length-cnt, Book.length);					
						break;
					}
				}
			} 
		} catch(Exception e) { System.out.println(e.getMessage()); }
	}
	// 이름으로 연락처 검색(Scanner)
	void search() {}
	void delete() {}                            // 연락처 삭제
	void call()    { call = !call; }            // 전화 걸기
	void receive() { receive = !receive; }      // 전화 받기
}
//----------------------- class PhoneBook ------------------------//

//-------------------- public class CellPhone --------------------//
public class CellPhone {
	public static void main(String[] args) {
		PhoneBook p = new PhoneBook();
		p.save("name", "number");
		p.save("name", "number");
		p.save("name", "number");
		p.save("name", "number");
		p.save("name", "number");
		
		System.out.println(Arrays.toString(p.Book[0]));
		System.out.println(Arrays.toString(p.Book[1]));
		System.out.println(Arrays.toString(p.Book[2]));
		System.out.println(Arrays.toString(p.Book[3]));
	}
}
//-------------------- public class CellPhone --------------------//