/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 17, task 2
 *  Description: Movie
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class MovieFile {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Movie[] movies = new Movie[3];

    System.out.println("1 for create file, 2 for read file: ");
    int choice = scanner.nextInt();
    if(choice == 1) {
      for(int i = 0; i<movies.length; i++) {
        scanner.nextLine();
        System.out.print("Movie name: ");
        movies[i] = new Movie(scanner.nextLine());
        System.out.println("list actors (# to end): ");

        String name = scanner.nextLine();
        ArrayList<String> names = new ArrayList<>();
        while(!name.equals("#")) {
          names.add(name);
          name = scanner.nextLine();
        }
        movies[i].setActors(names);

        System.out.println("Rating: ");
        movies[i].setRating(scanner.nextDouble());
      }

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Movie_List.dat"))) {
        for(int i = 0; i<movies.length; i++) {
          out.writeObject(movies[i]);
        }
        out.flush();
      }
      catch(IOException e) {
        System.out.println("Could not write file");
      }
    }
    else if(choice == 2) {
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Movie_List.dat"))) {
        for(int i = 0; i<movies.length; i++) {
          movies[i] = (Movie)in.readObject();
        }
      }
      catch(IOException | ClassNotFoundException e) {
        System.out.println("Could not write file");
      }

      for(int i = 0; i<movies.length; i++) {
        System.out.println("Movie " + i + ":");
        System.out.println("   Name: " + movies[i].getName());
        System.out.println("   Actors: " + movies[i].getActors().toString());
        System.out.println("   Rating: " + movies[i].getRating());
        System.out.println();
      }
    }
  }
}
