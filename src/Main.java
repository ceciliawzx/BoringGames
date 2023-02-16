import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {

    boolean stop1 = false;
    Scanner sc = new Scanner(System.in);
    int width = 0;
    int height = 0;
    int number = 0;
    String planeType = "";

    // read width and height
    while (!stop1) {
      System.out.println("Please input width of the board:");
      String widthInput = sc.nextLine();
      try {
        width = Integer.parseInt(widthInput);
        if (width < 5) {
          System.out.println("Not a valid width (5 <= width < 100). Please try again.");
        } else stop1 = true;
      } catch (Exception e) {
        System.out.println("Not a number, please try again.");
      }
    }
    stop1 = false;

    while (!stop1) {
      System.out.println("Please input height of the board:");
      String heightInput = sc.nextLine();
      try {
        height = Integer.parseInt(heightInput);
        if (height < 5) {
          System.out.println("Not a valid height (5 <= height < 100). Please try again.");
        } else stop1 = true;
      } catch (Exception e) {
        System.out.println("Not a number, please try again.");
      }
    }
    stop1 = false;

    // read number of planes
    while (!stop1) {
      System.out.println("Please input number of planes:");
      String numberString = sc.nextLine();
      try {
        number = Integer.parseInt(numberString);
        if (number < 1) {
          System.out.println("Not a valid number. Please try again.");
        } else stop1 = true;
      } catch (Exception e) {
        System.out.println("Not a number. Please try again.");
      }
    }

    // read planesType
    stop1 = false;
    while (!stop1) {
      System.out.println(
              """
                      Please choose types of planes:
                      1. All type 1
                      2. All type 2
                      3. All type 3
                      4. 1 & 2
                      5. 2 & 3
                      6. 1 & 3
                      7. 1 & 2 & 3
                      8. Random """);
      String typesString = sc.nextLine();
      try {
        int types = Integer.parseInt(typesString);
        if (types < 1 || types > 8) {
          System.out.println("Not a valid type. Please try again.");
        } else {
          switch (types) {
            case 1:
              planeType = "All same 1";
              break;
            case 2:
              planeType = "All same 2";
              break;
            case 3:
              planeType = "All same 3";
              break;
            case 4:
              planeType = "1 & 2";
              break;
            case 5:
              planeType = "2 & 3";
              break;
            case 6:
              planeType = "1 & 3";
              break;
            case 7:
              planeType = "1 & 2 & 3";
              break;
            case 8:
              planeType = "Random";
              break;
          }
          stop1 = true;
        }
      } catch (Exception e) {
        System.out.println("Not a number. Please try again.");
      }
    }

    stop1 = false;
    Board board = null;
    while (!stop1) {
      board = new Board(height, width);
      boolean initializeSuccess = board.initialize(number, planeType);
      if (!initializeSuccess) {
        System.out.println("Fail to initialize, please try again.");
        main(args);
        return;
      } else stop1 = true;
    }
    board.printPlaneTypes();
//    board.printFinal();

    int count = 0;
    while (!board.end()) {
      board.printCells();
      boolean stop2 = false;
      while (!stop2) {
        System.out.println("Please input a coordinate, use space as delimeter.");
        String input = sc.nextLine();
        try {
          int x = Integer.parseInt(input.split(" ")[0]);
          int y = Integer.parseInt(input.split(" ")[1]);
          if (board.getCell(x, y).isClicked) {
            System.out.println("Already clicked. Please try again");
          } else {
            board.Click(x, y);
            if (board.headFound(x, y)) {
              String res = "";
              for (int i = 0; i < board.getHeads().size(); i++) {
                if (board.getHeads().get(i).equals(new Cell(x, y))) {
                  res = board.getPlanes().get(i).toString();
                }
              }
              System.out.println("*********A " + res + " found*********\n");
            }
            stop2 = true;
          }
        } catch (Exception e) {
          System.out.println("Wrong input or input out of bound. Please try again.");
        }
      }
      count++;
      if (board.end()) {
        board.printCells();
        System.out.println(
            "*****You have found all the planes in " + count + " tries! Congratulations!*****\n");
        board.printFinal();
      }
    }
  }
}
