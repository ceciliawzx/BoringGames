import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

  private final List<List<Cell>> Cells;
  private final int size;
  private final List<Cell> heads;
  private final List<Plane> planes;

  // initialize the board
  public Board(int size) {
    this.Cells = new ArrayList<>();
    this.size = size;
    this.heads = new ArrayList<>();
    this.planes = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Cells.add(new ArrayList<>());
      for (int j = 0; j < size; j++) {
        int x = j + 1;
        int y = size - i;
        Cell newCell = new Cell(x, y);
        Cells.get(i).add(newCell);
      }
    }
  }

  public int getSize() {
    return size;
  }

  public void addPlane(Plane plane) {
    this.planes.add(plane);
  }

  private void clickAllCells() {
    for (int x = 1; x <= size; x++) {
      for (int y = 1; y <= size; y++) {
        Click(x, y);
      }
    }
  }

  private void clearAllCells() {
    for (int x = 1; x <= size; x++) {
      for (int y = 1; y <= size; y++) {
        Unclick(x, y);
      }
    }
  }

  public void Click(int x, int y) {
    int i = size - y;
    int j = x - 1;
    Cells.get(i).get(j).setClicked();
  }

  public void Unclick(int x, int y) {
    int i = size - y;
    int j = x - 1;
    Cells.get(i).get(j).setUnclicked();
  }

  public Cell getCell(int x, int y) {
    return Cells.get(XConvertToIndex(x, y)).get(YConvertToIndex(x, y));
  }

  // convert coordinates to index
  private int XConvertToIndex(int x, int y) {
    return size - y;
  }

  private int YConvertToIndex(int x, int y) {
    return x - 1;
  }

  public boolean headFound(int x, int y) {
    Cell cell = getCell(x, y);
    return cell.isHead;
  }

  public void addHead(Cell head) {
    this.heads.add(head);
  }

  // after all the heads are found, print the real board
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

  // end if all the heads are found
  public boolean end() {
    for (Cell head : heads) {
      if (!head.isClicked) {
        return false;
      }
    }
    return true;
  }

  private boolean generatePlanesSame(int num, Plane plane) {
    Plane1 plane1 = new Plane1(this);
    if ((plane.getClass() == plane1.getClass()) && size * size < num * 20) {
      return false;
    } else if (!(plane.getClass() == plane1.getClass()) && size * size < num * 25) {
      return false;
    }
    int count = 0;
    while (heads.size() != num) {
      count++;
      if (count > (this.size * this.size * 200)) {
        clearAllCells();
        return generatePlanesSame(num, plane);
      }
      int x = (new Random().nextInt(size)) + 1;
      int y = (new Random().nextInt(size)) + 1;
      plane.generateOnePlane(x, y);
    }
    return true;
  }

  private boolean generatePlanesSpecific(int num, String type) {
    int numType;
    int root;
    String[] types = {"1 & 2", "2 & 3", "1 & 2 & 3"};
    Plane plane1 = new Plane1(this);
    Plane plane2 = new Plane2(this);
    Plane plane3 = new Plane3(this);

    switch (type) {
      case "All same 1":
        return generatePlanesSame(num, plane1);
      case "All same 2":
        return generatePlanesSame(num, plane2);
      case "All same 3":
        return generatePlanesSame(num, plane3);
      case "1 & 2":
        {
          if (size * size < num * 20) return false;
          numType = 2;
          root = 1;
          break;
        }
      case "2 & 3":
        {
          if (size * size < num * 25) return false;
          numType = 2;
          root = 2;
          break;
        }
      case "Random":
        {
          int chooseType = (new Random().nextInt(3));
          String newType = types[chooseType];
          System.out.println(newType);
          return generatePlanesSpecific(num, newType);
        }
      case "1 & 2 & 3":
      default:
        {
          if (size * size < num * 20) return false;
          numType = 3;
          root = 1;
        }
    }

    int count = 0;
    while (heads.size() != num) {
      int random;
      if (type.equals("1 & 3")) {
        int oneOrThree = (new Random().nextInt(2));
        if (oneOrThree == 1) {
          random = 1;
        } else random = 3;
      } else random = (new Random().nextInt(numType)) + root;
      int x = (new Random().nextInt(size)) + 1;
      int y = (new Random().nextInt(size)) + 1;
      switch (random) {
        case 1:
          plane1.generateOnePlane(x, y);
          break;
        case 2:
          plane2.generateOnePlane(x, y);
          break;
        case 3:
          plane3.generateOnePlane(x, y);
          break;
      }
      count++;
      if (count > (this.size * this.size * 200)) {
        clearAllCells();
        return generatePlanesSpecific(num, type);
      }
    }
    return true;
  }

  public boolean initialize(int planesNum, String planesType) {
    if (size * size < planesNum * 20) {
      return false;
    } else return generatePlanesSpecific(planesNum, planesType);
  }

  public void printPlaneTypes() {
    StringBuilder res = new StringBuilder();
    res.append("Plane types are:\n");
    for (Plane plane: this.planes) {
      res.append(plane.toString()).append(' ');
    }
    System.out.println(res);
  }
}
