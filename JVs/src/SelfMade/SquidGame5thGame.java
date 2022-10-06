package SelfMade;
// 넷플릭스 시리즈, <오징어게임> 중, 5번째 게임 : 징검다리 건너기
import java.util.Scanner;
public class SquidGame5thGame
{
	public static void main(String[] args)
	{
		int[][] Bridge = new int[18][2];
		Scanner yn = new Scanner(System.in);
		int YesNo = 0;
		
		// 게임 세팅
		for(int i=0; i<Bridge.length; i++) {
			int n = (int)(Math.random()*2);
			for(int j=0; j<Bridge[0].length; j++) {
				if(n==0) Bridge[i][0]=1;
				else 	 Bridge[i][1]=1;
			}
		}
		
		//게임 동의?
		do {
			System.out.println("===========");
			System.out.println("   ○△□ ?"  );
			System.out.println("  1. YES"  );
			System.out.println("  2. No "  );
			System.out.println("===========");
			System.out.print("1 or 2 ? => ");
			YesNo = yn.nextInt();
			if(YesNo==1) {
				System.out.println("Welcome.\n ");
				break;
			} else if(YesNo==2) {
				System.out.print("We sincerely respect your decision.");
				System.exit(0);
			} 
			else System.out.println("Excuse me?");
		} while(true);
		
		// 게임 진행
		System.out.println("< Start Here >");
		for(int i=0; i<=18; i++) System.out.println("   □  □ ["+i+"]");
		// do-while 반복문 활용, 사용자입력으로 0 또는 1 입력 받기
		// 0은 안깨지는 유리, 1은 깨지는 유리
		// 각 row에 대해 R또는L 선택(열 번호 : R=1, L=0), 만약 주어진 행에서 선택한 열이 1이면 게임 종료, 0이면 계속
		// 내일 이어서 계속(2022.10.06., 16:51)
		
		
		// 정답 공개
		System.out.println("<  정답  >\n ");
		for(int i=0; i<Bridge.length; i++) {
			for(int j=0; j<Bridge[0].length; j++) {
				System.out.print(Bridge[i][j]);
			}
			System.out.printf("   [%d]",i+1);
			System.out.println();
		}
		
	}
}