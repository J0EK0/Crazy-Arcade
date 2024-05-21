package model.gameobject;

public abstract class SuperObject {
    private int x;
    private int y;
    private int width;
    private int height;

    public SuperObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getx() { return x; }
    public int gety() { return y; }
    public int geth() { return height;}
    public int getw() { return width;}
}
