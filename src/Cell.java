public class Cell {
    final int x, y;
    String cha;
    boolean isHead;
    boolean isPlane;
    boolean isClicked;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.cha = "   ";
    }

    public void setHead() {
        this.isHead = true;
        cha = " o ";
    }

    public void setPlane() {
        this.isPlane = true;
        cha = " + ";
    }

    public void printReal() {
        System.out.print(cha);
    }

    public void setClicked() {
        this.isClicked = true;
    }

    public void setUnclicked() {
        this.isClicked = false;
    }

    public void print() {
        System.out.print("[ ]");
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", cha='" + cha + '\'' +
                ", isHead=" + isHead +
                ", isPlane=" + isPlane +
                '}';
    }
}
