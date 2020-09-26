package Structures;

public class pair {
    String x;
    int y;

    // Constructor
    public pair(String x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public String getLeft(){
        return x;
    }
    public int getRight(){
        return y;
    }
    public void changeLeft(String x) {
        this.x = x;
    }
    public void changeRight(int y){
        this.y = y;
    }
}
