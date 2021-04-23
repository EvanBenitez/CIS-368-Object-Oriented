/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, Question 10.25
 *  Description: split function that includes delimiters in output
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Cha10_25 {
  public static void main(String[] args) {
    Scanner strGetter = new Scanner(System.in);

    System.out.println("Enter a string:");
    String testString = strGetter.nextLine();

    System.out.println("Enter a delimiter:");
    String del = strGetter.nextLine();

    String [] tokens = split(testString, del);

    //display tokens
    System.out.print("[");
    for(int i = 0; i < tokens.length; i++) {
      System.out.print(tokens[i]);
      if(i < tokens.length-1)
        System.out.print(", ");
    }
    System.out.println("]");
  }


  public static String[] split(String s, String regex) {
    // check if string is empty
    if(s.length() == 0) {
      return new String[0];
    }

    //check if regex is empty String
    if(regex.length() == 0) {
      String[] a = new String[1];
      a[0] = s;
      return a;
    }

    // create ArrayList
    ArrayList<String> tokens = new ArrayList<>();

    // decide if regex is string or regular expression.
    if (regex.charAt(0) == '[' && regex.charAt(regex.length()-1) == ']') {

    	String[] sTokens = s.split(regex); // get the tokens without the delimiters
      char[] del = new char[regex.length()-2]; //delimiters
      int index = 0; // keep place of location in original string

      // get array of delimiters
      for(int i=1; i<regex.length()-1; i++){ //skip the brackets
        del[i-1] = regex.charAt(i);
      }

      //add tokens and deliiters to tokesn ArrayList
      for(int i=0; i<sTokens.length; i++) {
        tokens.add(sTokens[i]);
        index += sTokens[i].length();

        // Check string bound
        if(s.length() > index) {
          //find next delimiter in string
          for(int j=0; j<del.length; j++) {
            if(s.charAt(index) == del[j]){
              tokens.add(String.valueOf(del[j]));
              index++;
              break;
            }
          }
          // Note that the offical split funtion does not return empty
          // string for the output at the end of the string if then end
          // of the string consiste of delimiters.
        }
      }
      // get trailing delimiters
      for(int z = index; z<s.length(); z++) {
        tokens.add(s.substring(z,z+1));
      }

    }
    else {
      // get delimiter offset
      int len = regex.length()-1;

      //move through the String
      for (int i = 0; i<s.length(); i++) {
        //starting index
        int end = s.indexOf(regex, i);
        if(end > -1) {
          tokens.add(s.substring(i, end));
          tokens.add(regex);
          i = end+len;
        }
        else {
          // get last token
          tokens.add(s.substring(i));
          break; // no more matches
        }
      }
    }

    //return String array
    String[] a = new String[tokens.size()];
    tokens.toArray(a);
    return a;
  }
}
