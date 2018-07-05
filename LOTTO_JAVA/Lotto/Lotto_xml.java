package Lotto;

import java.util.Scanner;

public class Lotto_xml {
	static void usr_choice(char choice){
		switch(choice){
		case '1':
		}
		
	}
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		char usr;
		System.out.println("읽을 파일 주소(xml)");
		String address=sc.nextLine();
		do{
			System.out.println("1) 특정 회차 당첨번호 확인");
			usr=sc.nextLine().charAt(0);
		}while(usr!='Q');
	}
}
