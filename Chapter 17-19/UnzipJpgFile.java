/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 17, task 1
 *  Description: ZipJpg
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class UnzipJpgFile {
  public static void main(String[] args) {
    if(args.length == 1) {
      ObjectInputStream zipped = null;
      File file = null;
      try {
        file = new File(args[0]);
         zipped = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
         System.out.println(file.length());
         int fileCount = zipped.readInt();
         for(int i=0; i<fileCount; i++){
           String name = zipped.readUTF();
           int size = zipped.readInt();
           byte[] jpgFile = new byte[size];
           for(int j=0; j<size; j++) {
             jpgFile[j] = (byte)zipped.read();
           }
           restoreJpg(jpgFile, name);
           System.out.println(name + " " + jpgFile.length);
         }
      }
      catch(IOException e) {
        System.out.println("Was unable to read zipped file");
      }
      finally {
        try {
          zipped.close();
        }
        catch(IOException ex) {
          ex.printStackTrace();
        }
      }
    }
    else {
      System.out.println("Please specify file");
    }
  }

  private static void restoreJpg(byte[] jpgFile, String fileName) {
    BufferedOutputStream jpg = null;
    try {
      jpg = new BufferedOutputStream(new FileOutputStream(fileName));
      jpg.write(jpgFile);
      jpg.flush();
      jpg.close();
    }
    catch(IOException e) {
      System.out.println("Failed to unzip " + fileName);
    }
  }
}
