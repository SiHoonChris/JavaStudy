package SelfMade;

import java.util.Arrays;
import java.util.Scanner;

public class CigaretteStore {

	public static void main(String[] args) {
		String[] ciga;
		String order;
		Scanner o = new Scanner(System.in);
		Boolean have = false;
		
		ciga=new String[] {"말보로레드", "에세", "에세체인지", "카멜", "보헴시가", "프렌치블랙", "프렌치요고", "람보르기니", "거북선", "라일락"};
		System.out.println("진열대 => "+Arrays.toString(ciga));
		System.out.println("뭐 드릴까??");
		order=o.next();
		
		for(int i=0; i<ciga.length; i++) {
			if(order.equals(ciga[i])) {
				have = true;
				break;
			}
		} //order가 ciga배열 안에 있으면 false는 true로 전환, 없으면 그대로 false
		
		if(have) System.out.println("가져");
		else System.out.println("눈이 안좋나?");  //if(!have)
		
	}
}
//보통 자바에서 equals를 사용하여 문자열이 동일한지 확인합니다.
//다른 언어와 다르게 ==로 문자열이 같은지 확인하지 않습니다.
//==는 object가 동일한지를 체크하기 때문에 object가 갖고 있는 문자열이 동일하다는 것을 보장하지 않기 때문입니다.
//또한 compare 메소드를 이용하여 문자열을 비교할 수 있습니다.
//https://codechacha.com/ko/java-string-compare/

//진열대 => [말보로레드, 에세, 에세체인지, 카멜, 보헴시가, 프렌치블랙, 프렌치요고, 람보르기니, 거북선, 라일락]
//뭐 드릴까??
//블랙데빌
//눈이 안좋나?
