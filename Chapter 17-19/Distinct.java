/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 19, task 3
 *  Description: distinct elements in ArrayList
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Distinct {
  public static void main(String[] args) {
    // Integer test
    Integer[] integers = {1,2,3,3,4,3,5,3,6,4,7,4,2,5,7,42,54,2,5,7,8,9,4,3,5,7,4,2,6,8,4,5,9,88,8,8,0,-1,10,-1};
    ArrayList<Integer> intList = new ArrayList(Arrays.asList(integers));
    System.out.println("Original int list:" + intList.toString());
    System.out.println("NoDup int list:" + removeDuplicates(intList).toString());
    // Double test
    Double[] doubles = {1.5,1.5,0.2,1.2,8.2,7.5,1.1,22.2,2.2,9.6,8.8,77.7,7.7,2.2};
    ArrayList<Double> doubleList = new ArrayList(Arrays.asList(doubles));
    System.out.println("Original double list:" + doubleList.toString());
    System.out.println("NoDup double list:" + removeDuplicates(doubleList).toString());
    // String test
    String[] strings = {"test", "test1", "test2", "test3","test4","test1", "test2", "test", "test6", "test4","test"};
    ArrayList<String> stringList = new ArrayList(Arrays.asList(strings));
    System.out.println("Original string list:" + stringList.toString());
    System.out.println("NoDup double list:" + removeDuplicates(stringList).toString());
  }

  public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
    ArrayList<E> noDups = new ArrayList<>();

    for(int i=0; i<list.size(); i++) {
      boolean unique = true;
      for(int j=i+1; j< list.size() && unique==true; j++){
        if(list.get(i).equals(list.get(j)))
          unique = false;
      }
      if(unique == true)
        noDups.add(list.get(i));
    }
    return noDups;
  }
}
