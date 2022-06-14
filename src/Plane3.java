public class Plane3 extends Plane {

  public Plane3(Board board) {
    super(board);
  }

  @Override
  boolean checkLeft(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      try {
        Cell cellTest1 = board.getCell(x - i, y + i);
        Cell cellTest2 = board.getCell(x - i, y - i);
        if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane)
          return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = 0; i < 4; i++) {
      try {
        Cell cellTest = board.getCell(x - i, y);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = board.getCell(x - 4, y + i);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  @Override
  boolean checkRight(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      try {
        Cell cellTest1 = board.getCell(x + i, y + i);
        Cell cellTest2 = board.getCell(x + i, y - i);
        if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane)
          return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = 0; i < 4; i++) {
      try {
        Cell cellTest = board.getCell(x + i, y);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = board.getCell(x + 4, y + i);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  @Override
  boolean checkUp(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      try {
        Cell cellTest1 = board.getCell(x + i, y + i);
        Cell cellTest2 = board.getCell(x - i, y + i);
        if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane)
          return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = 0; i < 4; i++) {
      try {
        Cell cellTest = board.getCell(x, y + i);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = board.getCell(x + i, y + 4);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  @Override
  boolean checkDown(Cell head) {
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      try {
        Cell cellTest1 = board.getCell(x + i, y - i);
        Cell cellTest2 = board.getCell(x - i, y - i);
        if (cellTest1.isHead || cellTest1.isPlane || cellTest2.isHead || cellTest2.isPlane)
          return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = 0; i < 4; i++) {
      try {
        Cell cellTest = board.getCell(x, y - i);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    for (int i = -1; i <= 1; i++) {
      try {
        Cell cellTest = board.getCell(x + i, y - 4);
        if (cellTest.isHead || cellTest.isPlane) return false;
      } catch (IndexOutOfBoundsException e) {
        return false;
      }
    }
    return true;
  }

  @Override
  void setPlaneLeft(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      board.getCell(x - i, y + i).setPlane();
      board.getCell(x - i, y - i).setPlane();
    }
    for (int i = 1; i < 4; i++) {
      board.getCell(x - i, y).setPlane();
    }
    for (int i = -1; i <= 1; i++) {
      board.getCell(x - 4, y + i).setPlane();
    }
  }

  @Override
  void setPlaneRight(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      board.getCell(x + i, y + i).setPlane();
      board.getCell(x + i, y - i).setPlane();
    }
    for (int i = 1; i < 4; i++) {
      board.getCell(x + i, y).setPlane();
    }
    for (int i = -1; i <= 1; i++) {
      board.getCell(x + 4, y + i).setPlane();
    }
  }

  @Override
  void setPlaneUp(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      board.getCell(x + i, y + i).setPlane();
      board.getCell(x - i, y + i).setPlane();
    }
    for (int i = 1; i < 4; i++) {
      board.getCell(x, y + i).setPlane();
    }
    for (int i = -1; i <= 1; i++) {
      board.getCell(x + i, y + 4).setPlane();
    }
  }

  @Override
  void setPlaneDown(Cell head) {
    head.setHead();
    int x = head.x;
    int y = head.y;
    for (int i = 1; i <= 2; i++) {
      board.getCell(x + i, y - i).setPlane();
      board.getCell(x - i, y - i).setPlane();
    }
    for (int i = 1; i < 4; i++) {
      board.getCell(x, y - i).setPlane();
    }
    for (int i = -1; i <= 1; i++) {
      board.getCell(x + i, y - 4).setPlane();
    }
  }

  @Override
  int getSize() {
    return 11;
  }

  @Override
  public String toString() {
    return "Plane3";
  }
}
