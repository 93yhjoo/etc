package lotto_txt_IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class write {
	//address_txt==>"C:\\Users\\joo\\workspace\\lotto2.txt"
	Scanner scan= new Scanner(System.in);
	//갱신 형식 파악
	public void check_form(String address_txt){
		int count=0;
		System.out.println("갱신하세요");
		String input=scan.nextLine();
		char arr[]=new char[input.length()];
		for(int i=0;i<input.length();i++){
			arr[i]=input.charAt(i);
			if(arr[i]=='\t'){count++;}
		}
		if(count==5){
		BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(address_txt, true));
            writer.write(input);
            
            writer.newLine();
           
        } 
        catch (IOException e){
            e.printStackTrace();
        } 
        finally {
          if (writer != null) {
                try {
                    writer.close();
                    //txt -> xml파일 생성
                    MakeToXML(address_txt);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
   }
		else{
		System.out.println("입력 양식이 다릅니다.");
		check_form(address_txt);
   }
}

	public void MakeToXML(String address_txt) {
		System.out.println("몇회차 까지 만들겠습니까?");
		int last_round=scan.nextInt();
	  FileReader fread = null;		
			Properties  prop=new Properties();
		
			
			try {
				fread=new FileReader(address_txt);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try{
				String num;
				
				for(int i=1;i<=last_round;i++){
					
					num=Integer.toString(i);
					String  readLine = "";
				    int    readChar = 0;
					try {
						while(readChar!=-1&&readChar !=10){
						readChar=fread.read();
						readLine+=(char)readChar;
						prop.setProperty(num,readLine);
						}
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}catch(Exception e){
			 System.out.println("오류 발생");	
			}
			try {
				prop.storeToXML(new FileOutputStream("lotto.xml"), "lotto");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
}
