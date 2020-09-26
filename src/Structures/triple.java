package Structures;

public class triple {
    String x;
    int y;
    int z;

    // Constructor
    public triple(String x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public String getLeft(){
        return x;
    }
    public int getMiddle(){
        return y;
    }
    public int getRight(){
        return z;
    }
    public void changeLeft(String x) {
        this.x = x;
    }
    public void changeMiddle(int y){
        this.y = y;
    }
    public void changeRight(int z){
        this.z = z;
    }
}
