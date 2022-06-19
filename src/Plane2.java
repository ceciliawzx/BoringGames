public class Plane2 extends Plane {

  public Plane2(Board board) {
    super(board);
  }

  @Override
  public boolean checkLeft(Cell head) {
    int x = head.x;
    int y = head.y;
    try {
      Cell cellTest1 = board.getCell(x - 1, y);
      Cell cellTest2 = board.getCell(x - 3, y);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = board.getCell(x - 2, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest1 = board.getCell(x - 4, y + 1);
      Cell cellTest2 = board.getCell(x - 4, y - 1);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean checkRight(Cell head) {
    int x = head.x;
    int y = head.y;
    try {
      Cell cellTest1 = board.getCell(x + 1, y);
      Cell cellTest2 = board.getCell(x + 3, y);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = board.getCell(x + 2, y + i);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest1 = board.getCell(x + 4, y + 1);
      Cell cellTest2 = board.getCell(x + 4, y - 1);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean checkUp(Cell head) {
    int x = head.x;
    int y = head.y;
    try {
      Cell cellTest1 = board.getCell(x, y + 1);
      Cell cellTest2 = board.getCell(x, y + 3);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = board.getCell(x + i, y + 2);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest1 = board.getCell(x - 1, y + 4);
      Cell cellTest2 = board.getCell(x + 1, y + 4);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean checkDown(Cell head) {
    int x = head.x;
    int y = head.y;
    try {
      Cell cellTest1 = board.getCell(x, y - 1);
      Cell cellTest2 = board.getCell(x, y - 3);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    for (int i = -2; i <= 2; i++) {
      try {
        Cell cellTest = board.getCell(x + i, y - 2);
        if (cellTest.isHead || cellTest.isPlane) {
          return false;
        }
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    try {
      Cell cellTest1 = board.getCell(x - 1, y - 4);
      Cell cellTest2 = board.getCell(x + 1, y - 4);
      if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane) {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  @Override
  public void setPlaneLeft(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    board.getCell(x - 1, y).setPlane();
    board.getCell(x - 3, y).setPlane();
    for (int i = -2; i <= 2; i++) {
      board.getCell(x - 2, y + i).setPlane();
    }
    board.getCell(x - 4, y + 1).setPlane();
    board.getCell(x - 4, y - 1).setPlane();
  }

  @Override
  public void setPlaneRight(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    board.getCell(x + 1, y).setPlane();
    board.getCell(x + 3, y).setPlane();
    for (int i = -2; i <= 2; i++) {
      board.getCell(x + 2, y + i).setPlane();
    }
    board.getCell(x + 4, y + 1).setPlane();
    board.getCell(x + 4, y - 1).setPlane();
  }

  @Override
  public void setPlaneUp(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    board.getCell(x, y + 1).setPlane();
    board.getCell(x, y + 3).setPlane();
    for (int i = -2; i <= 2; i++) {
      board.getCell(x + i, y + 2).setPlane();
    }
    board.getCell(x + 1, y + 4).setPlane();
    board.getCell(x - 1, y + 4).setPlane();
  }

  @Override
  public void setPlaneDown(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    board.getCell(x, y - 1).setPlane();
    board.getCell(x, y - 3).setPlane();
    for (int i = -2; i <= 2; i++) {
      board.getCell(x + i, y - 2).setPlane();
    }
    board.getCell(x + 1, y - 4).setPlane();
    board.getCell(x - 1, y - 4).setPlane();
  }

  @Override
  int getSize() {
    return 10;
  }

  @Override
  void printPlane() {
    System.out.println("""
                 *
                 *
           *  *  *  *  *
                 *
              *     *
            """);
  }

  @Override
  PlaneType getType() {
    return PlaneType.PLANE2;
  }

  @Override
  public String toString() {
    return "Plane2";
  }
}
