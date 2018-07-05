package main_module;
import Lotto.Lotto;
import java.util.Scanner;

public class md {
	public static void main(String args[]){
		boolean check=false;
		do{
			
		System.out.println("年金ロト, ロト の中で選択して入力ください");
		Scanner scan= new Scanner(System.in);
		String choose=scan.nextLine();
		if(choose.contains("年金ロト")){
			check=true;
			
		}
		else if(choose.contains("ロト")){
			check=true;
			Lotto game1=new Lotto();
			
		}
		else{
			System.out.println("もう一度入力してください");
		}
		}while(check!=true);
	}
}
