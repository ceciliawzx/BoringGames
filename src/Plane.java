import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Plane {

  protected Board board;

  public Plane(Board board) {
    this.board = board;
  }

  public boolean checkHead(Cell head) {
    return !(head.isHead || head.isPlane);
  }

  abstract boolean checkLeft(Cell head);

  abstract boolean checkRight(Cell head);

  abstract boolean checkUp(Cell head);

  abstract boolean checkDown(Cell head);

  abstract void setPlaneLeft(Cell head);

  abstract void setPlaneRight(Cell head);

  abstract void setPlaneUp(Cell head);

  abstract void setPlaneDown(Cell head);

  abstract int getSize();

  public boolean generateOnePlane(int x, int y) {
    Cell cellTest = board.getCell(x, y);
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
          case "left":
            setPlaneLeft(cellTest);
            break;
          case "right":
            setPlaneRight(cellTest);
            break;
          case "up":
            setPlaneUp(cellTest);
            break;
          case "down":
            setPlaneDown(cellTest);
            break;
        }
        board.addHead(cellTest);
        board.addPlane(this);
        return true;
      }
    }
    return false;
  }
}
