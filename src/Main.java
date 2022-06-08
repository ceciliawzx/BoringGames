import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

  private final List<List<Cell>> Cells = new ArrayList<>();
  private final int size;
  private final List<Cell> heads = new ArrayList<>();

  public Main(int size) {
    this.size = size;
  }

  public void initializeCells() {
    for (int i = 0; i < size; i++) {
      Cells.add(new ArrayList<>());
      for (int j = 0; j < size; j++) {
        int x = j + 1;
        int y = 10 - i;
        Cell newCell = new Cell(x, y);
        Cells.get(i).add(newCell);
      }
    }
    generatePlanes();
  }

  private void clickAllCells() {
    for (int x = 1; x <= size; x++) {
      for (int y = 1; y <= size; y++) {
        Click(x, y);
      }
    }
  }

  public void printFinal() {
    clickAllCells();
    printCells();
  }

  public void printCells() {
    for (int i = 0; i < size; i++) {
      if ((size - i) >= 10) {
        System.out.print((size - i) + " ");
      } else System.out.print((size - i) + "  ");
      for (int j = 0; j < size; j++) {
        Cell cell = Cells.get(i).get(j);
        if (cell.isClicked) {
          cell.printReal();
        } else cell.print();
      }
      System.out.println();
    }
    System.out.print("   ");
    for (int i = 1; i <= size; i++) {
      if (i >= 10) {
        System.out.print(" " + i + "");
      } else System.out.print(" " + i + " ");
    }
    System.out.println();
  }

  private boolean checkLeft(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = getCell(x - 1, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest = getCell(x - 2, y);
      if (cellTest.isHead || cellTest.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = getCell(x -3, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  private boolean checkRight(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = getCell(x + 1, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest = getCell(x + 2, y);
      if (cellTest.isHead || cellTest.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = getCell(x + 3, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  private boolean checkUp(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = getCell(x + i, y + 1);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest = getCell(x, y + 2);
      if (cellTest.isHead || cellTest.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = getCell(x + i, y + 3);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  private boolean checkDown(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = getCell(x + i, y - 1);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest = getCell(x, y - 2);
      if (cellTest.isHead || cellTest.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest =getCell(x + i, y - 3);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  private boolean checkHead(Cell head) {
    return !(head.isHead || head.isPlane);
  }

  private void setPlaneLeft(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
        Cell cell = getCell(x - 1, y + i);
        cell.setPlane();
      }
    Cell cellMiddle = getCell(x - 2, y);
    cellMiddle.setPlane();
    for (int i = -1; i <= 1; i++) {
        Cell cell = getCell(x - 3, y + i);
        cell.setPlane();
    }
  }

  private void setPlaneRight(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      Cell cell = getCell(x + 1, y + i);
      cell.setPlane();
    }
    Cell cellMiddle = getCell(x + 2, y);
    cellMiddle.setPlane();
    for (int i = -1; i <= 1; i++) {
      Cell cell = getCell(x + 3, y + i);
      cell.setPlane();
    }
  }

  private void setPlaneUp(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      Cell cell = getCell(x + i, y + 1);
      cell.setPlane();
    }
    Cell cellMiddle = getCell(x, y + 2);
    cellMiddle.setPlane();
    for (int i = -1; i <= 1; i++) {
      Cell cell = getCell(x + i, y + 3);
      cell.setPlane();
    }
  }

  private void setPlaneDown(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = -2; i <= 2; i++) {
      Cell cell = getCell(x + i, y - 1);
      cell.setPlane();
    }
    Cell cellMiddle = getCell(x, y - 2);
    cellMiddle.setPlane();
    for (int i = -1; i <= 1; i++) {
      Cell cell =getCell(x + i, y - 3);
      cell.setPlane();
    }
  }

  private boolean generateOnePlane(int x, int y) {
        Cell cellTest = getCell(x, y);
        if (checkHead(cellTest)) {
          List<String> list = new ArrayList<>();
          if (checkLeft(cellTest)) {
            list.add("left");
          }
          if (checkRight(cellTest)) {
            list.add("right");
          }
          if (checkUp(cellTest)) {
            list.add("up");
          }
          if (checkDown(cellTest)) {
            list.add("down");
          }
          if (list.size() == 0) {
            return false;
          } else {
            String direction;
            if (list.size() == 1) {
              direction = list.get(0);
            } else {
              int random = new Random().nextInt(list.size() - 1);
              direction = list.get(random);
            }
            switch (direction) {
              case "left": setPlaneLeft(cellTest); break;
              case "right": setPlaneRight(cellTest); break;
              case "up": setPlaneUp(cellTest); break;
              case "down": setPlaneDown(cellTest); break;
            }
            heads.add(cellTest);
            return true;
          }
        }
    return false;
  }

  private void generatePlanes() {
    while (heads.size() != 3) {
      int x = (new Random().nextInt(size)) + 1;
      int y = (new Random().nextInt(size)) + 1;
      generateOnePlane(x, y);
    }
  }

  private int XConvertToIndex(int x, int y) {
    return size - y;
  }

  private int YConvertToIndex(int x, int y) {
    return x - 1;
  }

  public void Click(int x, int y) {
    int i = size - y;
    int j = x - 1;
    Cells.get(i).get(j).setClicked();
  }

  private Cell getCell(int x, int y) {
    return Cells.get(XConvertToIndex(x, y)).get(YConvertToIndex(x, y));
  }

  public boolean headFound(int x, int y) {
    Cell cell = getCell(x, y);
    return cell.isHead;
  }

  public boolean end() {
    for (Cell head : heads) {
      if (!head.isClicked) {
        return false;
      }
    }
    return true;
  }

}
