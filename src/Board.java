import java.util.*;

public class Board {

  private final List<List<Cell>> Cells;
  private final int area;
  private final int width;
  private final int height;
  private final List<Cell> heads;
  private final List<Plane> planes;

  // initialize the board
  public Board(int height, int width) {
    this.Cells = new ArrayList<>();
    this.height = height;
    this.width = width;
    this.area = height * width;
    this.heads = new ArrayList<>();
    this.planes = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      Cells.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        int x = j + 1;
        int y = height - i;
        Cell newCell = new Cell(x, y);
        Cells.get(i).add(newCell);
      }
    }
  }

  public int getArea() {
    return area;
  }

  public List<Plane> getPlanes() {
    return planes;
  }

  public List<Cell> getHeads() {
    return heads;
  }

  public void addPlane(Plane plane) {
    this.planes.add(plane);
  }

  private void clickAllCells() {
    for (int x = 1; x <= height; x++) {
      for (int y = 1; y <= width; y++) {
        Click(x, y);
      }
    }
  }

  private void clearAllCells() {
    for (int x = 1; x <= height; x++) {
      for (int y = 1; y <= width; y++) {
        Unclick(x, y);
      }
    }
  }

  public void Click(int x, int y) {
    int i = height - y;
    int j = x - 1;
    Cells.get(i).get(j).setClicked();
  }

  public void Unclick(int x, int y) {
    int i = height - y;
    int j = x - 1;
    Cells.get(i).get(j).setUnclicked();
  }

  public Cell getCell(int x, int y) {
    return Cells.get(XConvertToIndex(x, y)).get(YConvertToIndex(x, y));
  }

  // convert coordinates to index
  private int XConvertToIndex(int x, int y) {
    return height - y;
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
    // y-coordinates
    for (int i = 0; i < height; i++) {
      if ((height - i) >= 10) {
        System.out.print((height - i) + " ");
      } else System.out.print((height - i) + "  ");
      for (int j = 0; j < width; j++) {
        Cell cell = Cells.get(i).get(j);
        if (cell.isClicked) {
          cell.printReal();
        } else cell.print();
      }
      System.out.println();
    }
    // x-coordinates
    System.out.print("   ");
    for (int j = 1; j <= width; j++) {
      if (j >= 10) {
        System.out.print(" " + j + "");
      } else System.out.print(" " + j + " ");
    }
    System.out.println();
  }

  // end if all the heads are found
  public boolean end() {
    for (Cell head: heads) {
      if (!head.isClicked) {
        return false;
      }
    }
    return true;
  }

  private boolean generatePlanesSame(int num, Plane plane) {
    Plane1 plane1 = new Plane1(this);
    if ((plane.getClass() == plane1.getClass()) && area < num * 20) {
      return false;
    } else if (!(plane.getClass() == plane1.getClass()) && area < num * 25) {
      return false;
    }
    int count = 0;
    while (heads.size() != num) {
      count++;
      if (count > area * 200) {
        clearAllCells();
        return generatePlanesSame(num, plane);
      }
      int x = (new Random().nextInt(width)) + 1;
      int y = (new Random().nextInt(height)) + 1;
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
          if (area < num * 20) return false;
          numType = 2;
          root = 1;
          break;
        }
      case "2 & 3":
        {
          if (area < num * 25) return false;
          numType = 2;
          root = 2;
          break;
        }
      case "Random":
        {
          int chooseType = (new Random().nextInt(3));
          String newType = types[chooseType];
//          System.out.println(newType);
          return generatePlanesSpecific(num, newType);
        }
      case "1 & 2 & 3":
      default:
        {
          if (area < num * 20) return false;
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
      int x = (new Random().nextInt(width)) + 1;
      int y = (new Random().nextInt(height)) + 1;
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
      if (count > area * 200) {
        clearAllCells();
        return generatePlanesSpecific(num, type);
      }
    }
    return true;
  }

  public boolean initialize(int planesNum, String planesType) {
    if (area < planesNum * 20) {
      return false;
    } else return generatePlanesSpecific(planesNum, planesType);
  }

  public void printPlaneTypes() {
    Map<Plane, Integer> planeTypes = new HashMap<>();
    for (Plane plane: this.planes) {
      if (planeTypes.containsKey(plane)) {
        planeTypes.put(plane, planeTypes.get(plane)+1);
      } else planeTypes.put(plane, 1);
    }
    for (Plane plane : planeTypes.keySet()) {
      StringBuffer res = new StringBuffer();
      System.out.println(res.append(plane).append(": ").append(planeTypes.get(plane)));
      plane.printPlane();
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
