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
		System.out.println("���� ���� �ּ�(xml)");
		String address=sc.nextLine();
		do{
			System.out.println("1) Ư�� ȸ�� ��÷��ȣ Ȯ��");
			usr=sc.nextLine().charAt(0);
		}while(usr!='Q');
	}
}
