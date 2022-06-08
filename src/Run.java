import java.io.IOException;
import java.util.Scanner;

public class Run {
  public static void main(String[] args) throws IOException {
      Main main = new Main(10);
      main.initializeCells();
      int count = 0;
      while (!main.end()) {
          main.printCells();
          Scanner sc = new Scanner(System.in);
          System.out.println("Please input a coordinate, use space as delimeter.");
          String input = sc.nextLine();
          int x = Integer.parseInt(input.split(" ")[0]);
          int y = Integer.parseInt(input.split(" ")[1]);
          main.Click(x, y);
          if (main.headFound(x, y)) {
                System.out.println("A plane found!\n");
          }
          count++;
          if (main.end()) {
            System.out.println("You have found all the planes in " + count +
                    " tries! Congratulations!\n");
            main.printFinal();
          }
      }
  }
}
