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
	//���� ���� �ľ�
	public void check_form(String address_txt){
		int count=0;
		System.out.println("�����ϼ���");
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
                    //txt -> xml���� ����
                    MakeToXML(address_txt);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
   }
		else{
		System.out.println("�Է� ����� �ٸ��ϴ�.");
		check_form(address_txt);
   }
}

	public void MakeToXML(String address_txt) {
		System.out.println("��ȸ�� ���� ����ڽ��ϱ�?");
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
			 System.out.println("���� �߻�");	
			}
			try {
				prop.storeToXML(new FileOutputStream("lotto.xml"), "lotto");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
}
