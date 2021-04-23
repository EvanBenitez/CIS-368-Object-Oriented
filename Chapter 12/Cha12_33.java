import java.util.Scanner;
import java.util.ArrayList;

public class Cha12_33 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a word: ");
    String word = input.nextLine();
    System.out.print("Enter a URL: ");
    String url = input.nextLine();
    crawler(word, url); // Traverse the Web from the a starting url
  }

  public static void crawler(String word, String startingURL) {
    ArrayList<String> listOfPendingURLs = new ArrayList<>();
    ArrayList<String> listOfTraversedURLs = new ArrayList<>();
    int count = 0; // number of site found that contain word

    listOfPendingURLs.add(startingURL);
    while (!listOfPendingURLs.isEmpty() &&
        listOfTraversedURLs.size() <= 10000 && count <= 10) {
      String urlString = listOfPendingURLs.remove(0);
      if (!listOfTraversedURLs.contains(urlString)) {
        listOfTraversedURLs.add(urlString);
        //System.out.println("Crawl " + urlString);

        for (String s: getSubURLs(word, urlString)) {
          // print url of word
          if(s.equals("T")){
            System.out.println("found @ " + urlString);
            count++;
          }
          if (!s.equals("T") && !listOfTraversedURLs.contains(s))
            listOfPendingURLs.add(s);

        }
      }
    }
  }

  public static ArrayList<String> getSubURLs(String word, String urlString) {
    ArrayList<String> list = new ArrayList<>();
    boolean present = false;

    try {
      java.net.URL url = new java.net.URL(urlString);
      Scanner input = new Scanner(url.openStream());
      int current = 0;
      while (input.hasNext()) {
        String line = input.nextLine();
        current = line.indexOf("http:", current);
        //check for word match
        if(line.contains(word)){
          present = true;
        }
        while (current > 0) {
          int endIndex = line.indexOf("\"", current);
          int alt = line.indexOf("\'", current); // check for single quat option
          if(alt != -1)
            endIndex = endIndex < alt ? endIndex : alt;
          if (endIndex > 0) { // Ensure that a correct URL is found
            list.add(line.substring(current, endIndex));
            current = line.indexOf("http:", endIndex);
          }
          else
            current = -1;
        }
      }
    }
    catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }

      // add a flag at the end of the array to indicate if word was found
      if(present)
        list.add("T");

      return list;

  }
}
