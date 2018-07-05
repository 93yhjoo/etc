package lotto_txt_IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class restoretoXML {
	static 	FileReader fread;

	public static void main(String args[]){
		
		Properties  prop=new Properties();
	
		String file="C:\\Users\\joo\\workspace\\lotto2.txt";
		try {
			fread=new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			String num;
			
			for(int i=1;i<758;i++){
				
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
