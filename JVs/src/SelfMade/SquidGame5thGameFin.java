package SelfMade;
// 넷플릭스 시리즈, <오징어게임> 중, 5번째 게임 : 징검다리 건너기
import java.util.Scanner;
public class SquidGame5thGameFin
{
	public static void main(String[] args)
	{
		int[][] Bridge = new int[18][2];
		Scanner yn = new Scanner(System.in);
		Scanner choice = new Scanner(System.in);
		int num=0;
		int go=0;
		int YesNo = 0;
		
		// 게임 세팅
		for(int i=0; i<Bridge.length; i++) {
			int n = (int)(Math.random()*2);
			for(int j=0; j<Bridge[0].length; j++) {
				if(n==0) Bridge[i][0]=1;
				else 	 Bridge[i][1]=1;
			}
		}
//		(정답 미리보기)
//		System.out.println("<ANSWER>");
//		for(int i=0; i<Bridge.length; i++) {
//			for(int j=0; j<Bridge[0].length; j++) {
//				System.out.print(Bridge[i][j]);
//			}
//			System.out.printf("   [%d]",i+1);
//			System.out.println();
//		}

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
		System.out.println("Please, Make your Decision ; Right or Left");
		System.out.println("For Right, press 'R' or 'r'");
		System.out.println("For Left, press 'L' or 'l'");
		System.out.println("If you press other keys, it means you abandon the game");
		System.out.println("Then, Good Luck.\n");
		System.out.println("< ↓ Start Here ↓ >\n");
		System.out.println("     L  R");
		for(int i=1; i<=18; i++) System.out.println("     ■  ■ ["+i+"]");
		System.out.println("\n< -- End Here -- >");
		System.out.println("\nRight of Left");
		
		// (진행) 만약 주어진 행에서 선택한 열이 1(깨지는 유리)이면 게임 종료, 0(안깨지는 유리)이면 계속
		while(true) {
			System.out.println("["+(num+1)+"/18]");
			System.out.print("=> ");
			String Choice = choice.next();
			if(Choice.equals("L")||Choice.equals("l")) go=0;
			else if(Choice.equals("R")||Choice.equals("r")) go=1;
			else { System.out.println("\nThe participant has voluntarily decided the death."); break; }
			
			if(Bridge[num][go]==1) {
				System.out.println("\nThe participant failed and fell down to the bottom.");
				break;
			}
			if(Bridge[num][go]==0) num++;{				
				if(num==18) {
					System.out.println("\nCongratulations.");
					break;	
				}
			} 
		}
		
		// 정답 공개
		System.out.println("\n----------------\n <ANSWER>\n ");
		System.out.println("  L   R");
		for(int i=0; i<Bridge.length; i++) {
			for(int j=0; j<Bridge[0].length; j++) {
				if(Bridge[i][j]==0) Bridge[i][j]='■';
				else				Bridge[i][j]='X';
				System.out.print("  "+(char)Bridge[i][j]+" ");
			}
			System.out.printf(" [%d]",i+1);
			System.out.println();
		}
		yn.close();
		choice.close();
	}
}