package SelfMade; // 수업 시간 중에 만든 야구게임, 객체 개념 활용하여 다시 작성 

import java.util.Scanner;
import java.util.Arrays;

// ----------- 1. 컴퓨터 문제 생성 ---------- //
class ComputerQuestion{
	public static int com[] = new int[3];
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
class UserAnswer {
	public static Scanner sc = new Scanner(System.in);
	public int user[] = new int[3];

	public void throwBall(){
		System.out.print("첫 번째 공? : ");
		user[0]=sc.nextInt();
		System.out.print("두 번째 공? : ");
		user[1]=sc.nextInt();
		System.out.print("세 번째 공? : ");
		user[2]=sc.nextInt();
		System.out.println("공격 => "+Arrays.toString(user));
	}
}

//----------- 3. 게임 진행 ----------- //
class LetsGame{	
	UserAnswer ua = new UserAnswer();
	int tryCount=0;
	boolean EndGame = true;
	
	public void onGame() {		
		while(EndGame) {			
			int strike=0; int ball=0;
			tryCount++;
			
			ua.throwBall();
			
			for(int i=0; i<ua.user.length; i++)
			{   if(ua.user[i]==ComputerQuestion.com[i]) strike++;
			    else                                    ball++;   }
			System.out.printf("결과 => %d볼, %d스트라이크\n\n", ball, strike);
			
			if(tryCount==3) stopGame();	
			if(strike==3) {
				System.out.println("[ 게임 종료! WIN.-시도횟수:"+tryCount+" ]");
				EndGame=false;
			}
		}
	}

	private void stopGame() {
		System.out.print("포기? - 예:1, 아니오:2 \n=> ");
		int yesOrNo = UserAnswer.sc.nextInt();
		
		switch(yesOrNo) {
		case 1: System.out.println("[ 게임 종료! LOSE.-시도횟수:"+tryCount+" ]");
	  	 	    System.out.println("정답 : "+Arrays.toString(ComputerQuestion.com));
	  	 	    EndGame=false;
	  	 	    break;
		case 2: return;
		default: System.out.println("숫자 확인!");
		}
	}
	
}


//----------- 4. 게임 실행 ----------- //
public class NumberBaseball{
	public static void main(String[] args) {
		ComputerQuestion CQ = new ComputerQuestion();
		LetsGame G = new LetsGame();

		System.out.println("============야구 게임 시작============");
		System.out.println("1~9 사이의 정수를 입력하세요");
		System.out.println(Arrays.toString(CQ.com));  // 게임 시작할 때는 주석으로 처리
		
		G.onGame();
	}
}