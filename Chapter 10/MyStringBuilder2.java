/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, Question 10.28
 *  Description: StringBuilder2
*/

public class MyStringBuilder2 {
  private char[] myString;
  private int size; // size of string
  private final int inc = 30;

  public MyStringBuilder2() {
    myString = new char[inc];
    size = 0;
  }

  public MyStringBuilder2(char[] chars) {
    myString = new char[chars.length + inc];
    size = chars.length;

    for(int i=0; i<chars.length; i++) {
      myString[i] = chars[i];
    }
  }

  public MyStringBuilder2(String s) {
    myString = new char[s.length() + inc];
    size = s.length();

    for(int i=0; i<s.length(); i++) {
      myString[i] = s.charAt(i);
    }
  }

  public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
    char[] newMyString = new char[size + s.size];

    // Getting first part of String
    for(int i=0; i<offset; i++) {
      newMyString[i] = myString[i];
    }

    // insert new String
    for(int i=offset; i<offset+s.size; i++) {
      newMyString[i] = s.myString[i-offset];
    }

    // get rest of main String
    for(int i=offset+s.size; i<s.size+size; i++) {
      newMyString[i] = myString[i-s.size];
    }

    return new MyStringBuilder2(newMyString);
  }

  public MyStringBuilder2 reverse() {
    char[] rev = new char[size];

    //get character in myString in reverse order
    for(int i=size-1; i>=0; i--) {
      rev[rev.length - i -1] = myString[i];
    }

    // create a new MyStringBuilder2
    return new MyStringBuilder2(rev);
  }

  public MyStringBuilder2 substring(int begin) {
    char[] sub = new char[size - begin]; // size of substring

    // get substring
    for(int i=0; i<size-begin; i++ ) {
      sub[i] = myString[i+begin];
    }

    return new MyStringBuilder2(sub);
  }

  public MyStringBuilder2 toUpperCase() {
    char[] upper = new char[size];

    //check and convert all letter to upper case
    for(int i = 0; i < size; i++) {
      if( myString[i] >= 'a' && myString[i] <= 'z') {
        upper[i] = (char)(myString[i] - 32); // 32 is the difference in ascii code between upper and lower case letter
      }
      else {
        upper[i] = myString[i]; //not a lower case character
      }
    }

    return new MyStringBuilder2(upper);
  }

  @Override
  public String toString() {
    return new String(myString, 0, size);
  }

  // These methods are for increasing the char[] when the size is  too small
  // These methods were not needed, so they were not implemented
  private void increaseCapacity(String s){

  }

  private void increaseCapacity(char[] chars){

  }

  public static void main(String[] args) {
    //test reverse()
    MyStringBuilder2 test = new MyStringBuilder2("1234");
    System.out.println(test.reverse().toString() + " length: " + test.reverse().toString().length());

    //test toUpper()
    MyStringBuilder2 test1 = new MyStringBuilder2("abcABCabcdefg453 ^#(@ abbeDS)");
    System.out.println(test1.toString() + " original");
    System.out.println(test1.toUpperCase().toString() + " Upper");

    // test substring()
    MyStringBuilder2 test2 = new MyStringBuilder2("Get string starting here! (index 20)");
    System.out.println(test2.toString() + " original");
    System.out.println(test2.substring(20).toString() + " --Substring--");

    // test insert()
    MyStringBuilder2 test3 = new MyStringBuilder2("Insert string here --><-- (index 22)");
    System.out.println(test3.toString());
    System.out.println(test3.insert(22, test).toString());

    MyStringBuilder2 test4 = new MyStringBuilder2("Insert string here --><-- (blank insert)");
    System.out.println(test4.toString());
    System.out.println(test4.insert(22, new MyStringBuilder2()).toString());

  }

}
