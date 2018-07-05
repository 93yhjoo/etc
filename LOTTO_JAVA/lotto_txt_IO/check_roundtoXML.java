package lotto_txt_IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class check_roundtoXML {
	public static void main(String args[]){
		Properties pro=new Properties();
		
		try {
			pro.loadFromXML(new FileInputStream("lotto.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=1;i<=758;i++){
			String s=Integer.toString(i);
		System.out.println(pro.getProperty(s));
		}
	}
}
