package Lotto;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lotto_txt_IO.*;

class case_divide{
	Scanner scan= new Scanner(System.in);
	case_divide(char input){
		if(input=='N'){
		 write renew= new write();
		 //System.out.println("주소값을 입력하세요");
		  //String address=scan.nextLine();
		  renew.check_form("C:\\Users\\joo\\workspace\\lotto2.txt");
		}
		LottoAnalyzer data= new LottoAnalyzer();
		switch(input){
		
		case '0': data.printNumsOrderByDesc();
				  break;
				  //당첨번호 확인
		case '1':int temp[]=new int[6];
				for(int user_inputN=0;user_inputN<6;user_inputN++){
					temp[user_inputN]=scan.nextInt();
				}  
				 System.out.println(data.checkLottoNums(temp));
		         break;
		case '2':System.out.print("당첨회차확인:");
                 int what_round=scan.nextInt();
                 data.printNumsBySeq(what_round);
                 break;
		case '3': System.out.print("숫자에 대한 확률:");
				  int what_percentage=scan.nextInt();
        	      System.out.println(data.printProbForNum(what_percentage));
                  break;
		case '4': System.out.println("이번의 로또번호");
				  data.generateLottoNum();
			      break;
		case '5': data.printHistogram();
			      break;
		case 'Q': System.out.println("종료합니다");
				  break;
		case 'C': System.out.println("당첨 번호 확인");
					data.check_mynumber();
					break;
		default : break;
		}
	}
}

//데이터 가공처리
class data_forming {
	static Scanner scan= new Scanner(System.in);
	int lottoNums[] ;
	static int recent_round;
	static   String address;
	//들어온 문자열 숫자 데이터를 숫자배열로  가공처리
	static{
		System.out.println("주소값을 입력하세요");
		 //address=scan.nextLine();
		address="C:\\Users\\joo\\workspace\\lotto2.txt";
		//최근 회차
		System.out.println("最近の回数を入力してください");
		recent_round=scan.nextInt();
	}
	data_forming(){
		 
		  read fprocess = new read(address);
			String temp="0";
			for(int i=recent_round;i>0;i--){
				 temp+=fprocess.getNextLine();
			     }
				temp=temp.replace("\r\n","\t0");
				String cutting_number[]=temp.split("\t");
		lottoNums = new int[recent_round*6];
		  for(int iCount = 0 ; iCount < recent_round*6 ; iCount++){
		        lottoNums[iCount]=Integer.valueOf(cutting_number[iCount]);
		    }
	}
	
}
class LottoAnalyzer extends data_forming {

	 int number_data[]=new int[45];
	 int numbering[]=new int[45];
	
	 LottoAnalyzer(){
		 for(int i=0;i< recent_round*6;i++){
   		  number_data[super.lottoNums[i]-1]++;
		 }
		 
	 }
	
	   
	 	//0.번 입력시 
	   // 1 ~ 46까지 숫자중 발생 빈도가 높은 순으로 정렬해 출력
	   void printNumsOrderByDesc() {
	      for(int i=0;i<45;i++){
	    	  numbering[i]=i+1;
	      }
	     for(int i=0;i<45;i++){
	    	 for(int j=i+1;j<45;j++){
	    		 if(number_data[i]<number_data[j]){
	    			 int temp2= numbering[j];
	    			 numbering[j]= numbering[i];
	    			 numbering[i]=temp2;
	    			 int temp=number_data[j];
	    			 number_data[j]=number_data[i];
	    			 number_data[i]=temp;
	    		 } 
	    	 }
	     }
	     for(int i=0;i< 45;i++){
	    	 System.out.println(numbering[i]+":"+number_data[i]+"회");
	     }
	   }
	   
	   //1. 당첨번호 확인
	   int checkLottoNums(int argNums[]) {
		    //내가 넣은 값에 대한 정리
		   for(int i=0;i<6;i++){
			   for(int j=i+1;j<6;j++)
			   if(argNums[i]>argNums[j]){
				   int temp=argNums[j];
				   argNums[j]=argNums[i];
				   argNums[i]=temp;
			   }
		   }
		   
		   for(int ch_num=0;ch_num<recent_round*6;ch_num+=6){
			   if(argNums[0]==super.lottoNums[ch_num]){
				   int count=1;
			   for(int i=1;i<6;i++){
				   if(argNums[i]==super.lottoNums[ch_num+i]){
					   count++;
				   }
			    }
			   if(count==6){
				   return (ch_num/count)+1;
				   
			   	}
			   }
		   }
	      return -1;
	      
	   }
	   //2. 특정회차 당첨번호 출력
	   void printNumsBySeq(int argSeq) {
	     //738회차를 물어보면 0 1 2 3 4 5배열의 숫자를 꺼낸다.
		   System.out.print(argSeq+"회차번호:");
	     for(int i=(argSeq-1)*6;i<6*argSeq;i++){
	    	 System.out.print(super.lottoNums[i]+"\t");
	     }
	     System.out.println("");
	   }
	   
	   // 3.특정 숫자에 대한 발생 확률값 출력
	  float printProbForNum(int argNum) {	 
	        	 float temp=number_data[argNum-1]/(float)(recent_round); 
	        	return temp;
	   }
	   
	   // 로또번호 생성하기
	   void generateLottoNum() {
		   System.out.println("몇 장 /원하는 확률?");
		   int want=scan.nextInt();
		   double want_percent=scan.nextInt();
		   Set Lotto=new HashSet();
		   for(int chk=0;chk<want;chk++){
			   for(int i=0; Lotto.size()<6;i++){
				   int nm=(int)(Math.random()*45)*1;
			   }
			   LinkedList list=new LinkedList(Lotto);
			   int make_num[]=new int[6];
		   float percentage=1.0f;
		   for(int q=0;q<6;q++){
			   percentage*=printProbForNum(make_num[q]);
		   }
		   double p=percentage*10e5;
		   if(p<want_percent){
			chk--;   
		   }
		   else{
		   //숫자 정렬
		   for(int i=0;i<6;i++){
			   for(int j=i+1;j<6;j++){
				   if(make_num[i]>make_num[j]){
				   int temp=make_num[j];
				   make_num[j]=make_num[i];
				   make_num[i]=temp;
				   }
			   }
		   }
		   for(int k=0;k<6;k++){
		   System.out.print(make_num[k]+"\t");
		   }
		   System.out.println("");
		   System.out.print("당첨확률");
		   System.out.println(percentage);
		   		}
		   }
	   }
	   // 5.발생 빈도를 기준으로 히스토그램 출력
	   void printHistogram() {

	      for(int i=116;i>=72;i-=2){
	    	  System.out.print(i+"\t");
	    	  for(int j=-1;j<45;j++){
	    		  
	    		if(j>-1){
	    		  if(i<=number_data[j]){
	    			  System.out.print("*");
	    		  }
	    		  else{
	    			  System.out.print(" ");
	    		  }
	    		}
	    	  }
	    	  System.out.println("");
	      }
	     System.out.println("-----------------------------------------");
	      System.out.println("");
	      for(int j=0;j<46;j++){
	    	  if(j==0){
	    		  System.out.print("\t");
	    	  }
	    	  System.out.print(j+" ");
	      }
	      System.out.println("");
	   }
	   void check_mynumber(){
		   int num=0;
		   System.out.println("몇 장을 입력하시겟습니까?");
		   num=scan.nextInt();
		   int number[][]= new int[num][6];
		for(int i=0;i<num;i++){
			for(int j=0;j<6;j++){
				number[i][j]=scan.nextInt();
			}
		}
		
		for(int i=0;i<num;i++){
			int count=0;
			for(int j=0;j<6;j++){
				for(int k=0;k<6;k++){
			if(number[i][j]==super.lottoNums[(recent_round-1)*6+k]){
				count++;
					}
				}
			if(j==5){
				switch(count){
				case 6: System.out.println("1등입니다.");
						break;
				case 5:	System.out.println("3등입니다.");
						break;
				case 4: System.out.println("4등입니다.");
						break;
				case 3: System.out.println("5등입니다.");
						break;
				default:System.out.println("꽝입니다...");
						break;
					}
				}
			}
		
		}
	   }
}

public class Lotto {
	public Lotto(){
		Scanner scan= new Scanner(System.in);
		char user_input;
		do{
		System.out.println("0)頻度");
		System.out.println("1)当たり番号確認");
		System.out.println("2)特定回の当たり番号");
		System.out.println("3)特定の数字に対する確率 ");
		System.out.println("4)ランダム番号お呼び");
		System.out.println("5)ヒストグラム");
		System.out.println("N)更新");
		System.out.println("C)私の番号");
		System.out.println("Q)終了");
		user_input=scan.nextLine().charAt(0);
		 new case_divide(user_input);
		}while(user_input!='Q');  
		
	}
	
}
