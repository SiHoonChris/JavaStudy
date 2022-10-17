package SelfMade; // 수업 시간 중에 만든 야구게임, 객체 개념 활용하여 다시 작성 

import java.util.Scanner;
import java.util.Arrays;

// ----------- 1. 컴퓨터 문제 생성 ---------- //
class ComputerQuestion{
	static int com[] = new int[3];
	boolean randomBall=true;
	int i=0;
	
	ComputerQuestion(){		
		while(randomBall) {		
			i++;
			com[0]=(int)(Math.random()*9+1);
			com[1]=(int)(Math.random()*9+1);
			com[2]=(int)(Math.random()*9+1);
		
			if(com[0]!=com[1] && com[1]!=com[2] && com[2]!=com[0]) randomBall=false;
		}	
	}
}

// ----------- 2. 사용자 답안 ----------- //
class UserAnswer { int user[] = new int[3]; }

//----------- 3. 게임 진행 ----------- //
public class NumberBaseball{
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		int tryCount = 0; // 몇 번만에 맞추었는지 저장할 변수
		boolean EndGame = true;

		System.out.println("============야구 게임 시작============");
		System.out.println("1~9 사이의 정수를 입력하세요");

		ComputerQuestion CQ = new ComputerQuestion();
		UserAnswer UA = new UserAnswer();
		System.out.println(Arrays.toString(CQ.com));  // 게임 시작할 때는 주석으로 처리
	
		while(EndGame) {
			int strike=0, ball=0;
			tryCount++;
			
			System.out.print("첫 번째 공? : ");
			UA.user[0]=sc.nextInt();
			System.out.print("두 번째 공? : ");
			UA.user[1]=sc.nextInt();
			System.out.print("세 번째 공? : ");
			UA.user[2]=sc.nextInt();

			for(int i=0; i<UA.user.length; i++) {
				if(UA.user[i]==CQ.com[i]) strike++;
				else                      ball++;
			}
			System.out.printf("%d 볼, %d 스트라이크\n\n", ball, strike);
			
			if(strike==3) {
				System.out.println("게임 종료! WIN.-시도횟수:"+tryCount);
				EndGame=false;
			}
			
		}
	}
}