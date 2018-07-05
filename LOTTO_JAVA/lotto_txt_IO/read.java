package lotto_txt_IO;
import java.io.FileReader;
import java.io.IOException;



public class read {
	   FileReader       fread;
	   public read(String argFileName) {
	      try {
	         fread   = new FileReader(argFileName);
	      } catch (IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
	   public String getNextLine() {
	      String  readLine = "";
	      int    readChar = 0;
	      try {
	         while(readChar != -1 && readChar != 10) {//엔터키 10(아스키)
	            readChar = fread.read();
	            readLine += (char)readChar;
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return readLine;     
	   }

}
