import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plane1 extends Plane {

    public Plane1(Board board) {
        super(board);
    }

    @Override
    public boolean checkLeft(Cell head) {
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            try {
                Cell cellTest = board.getCell(x - 1, y + i);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        try {
            Cell cellTest = board.getCell(x - 2, y);
            if (cellTest.isHead || cellTest.isPlane) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            try {
                Cell cellTest = board.getCell(x - 3, y + i);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkRight(Cell head) {
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            try {
                Cell cellTest = board.getCell(x + 1, y + i);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        try {
            Cell cellTest = board.getCell(x + 2, y);
            if (cellTest.isHead || cellTest.isPlane) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            try {
                Cell cellTest = board.getCell(x + 3, y + i);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkUp(Cell head) {
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            try {
                Cell cellTest = board.getCell(x + i, y + 1);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        try {
            Cell cellTest = board.getCell(x, y + 2);
            if (cellTest.isHead || cellTest.isPlane) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            try {
                Cell cellTest = board.getCell(x + i, y + 3);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkDown(Cell head) {
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            try {
                Cell cellTest = board.getCell(x + i, y - 1);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        try {
            Cell cellTest = board.getCell(x, y - 2);
            if (cellTest.isHead || cellTest.isPlane) {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            try {
                Cell cellTest =board.getCell(x + i, y - 3);
                if (cellTest.isHead || cellTest.isPlane) {
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setPlaneLeft(Cell head) {
        head.setHead();
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            Cell cell = board.getCell(x - 1, y + i);
            cell.setPlane();
        }
        Cell cellMiddle = board.getCell(x - 2, y);
        cellMiddle.setPlane();
        for (int i = -1; i <= 1; i++) {
            Cell cell = board.getCell(x - 3, y + i);
            cell.setPlane();
        }
    }

    @Override
    public void setPlaneRight(Cell head) {
        head.setHead();
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            Cell cell = board.getCell(x + 1, y + i);
            cell.setPlane();
        }
        Cell cellMiddle = board.getCell(x + 2, y);
        cellMiddle.setPlane();
        for (int i = -1; i <= 1; i++) {
            Cell cell = board.getCell(x + 3, y + i);
            cell.setPlane();
        }
    }

    @Override
    public void setPlaneUp(Cell head) {
        head.setHead();
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            Cell cell = board.getCell(x + i, y + 1);
            cell.setPlane();
        }
        Cell cellMiddle = board.getCell(x, y + 2);
        cellMiddle.setPlane();
        for (int i = -1; i <= 1; i++) {
            Cell cell = board.getCell(x + i, y + 3);
            cell.setPlane();
        }
    }

    @Override
    public void setPlaneDown(Cell head) {
        head.setHead();
        int x = head.x;
        int y = head.y;
        for (int i = -2; i <= 2; i++) {
            Cell cell = board.getCell(x + i, y - 1);
            cell.setPlane();
        }
        Cell cellMiddle = board.getCell(x, y - 2);
        cellMiddle.setPlane();
        for (int i = -1; i <= 1; i++) {
            Cell cell =board.getCell(x + i, y - 3);
            cell.setPlane();
        }
    }

    @Override
    int getSize() {
        return 10;
    }

    @Override
    void printPlane() {
    System.out.println("""
                 *
           *  *  *  *  *
                 *
              *  *  *
            """);
    }

    @Override
    PlaneType getType() {
        return PlaneType.PLANE1;
    }

    @Override
    public String toString() {
        return "Plane1";
    }
}
