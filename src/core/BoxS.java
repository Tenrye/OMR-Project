package core;

public class BoxS {
    private int lx,ly,rx,ry;


    public BoxS(int lx, int ly, int rx, int ry){
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.ry = ry;
    }
    public int getlX(){
        return lx;
    }
    public int getrX(){
        return rx;
    }
    public int getlY(){
        return ly;
    }
    public int getrY(){
        return ry;
    }
    public void setlX(int lx){
        this.lx = lx;
    }
    public void setrX(int rx){
        this.rx = rx;
    }
    public void setlY(int ly) {
        this.ly = ly;
    }
    public void setrY(int ry) {
        this.ry = ry;
    }
    public String toString(){
        //returns the x and y values into numbers instead it shows the location
        return "(" + lx + ", " + ly +") + (" + rx + ", " + ry +")";
    }
    public double distanceTo(BoxS otherPoint){
        int dx = this.getlX() - otherPoint.getrX();
        int dy = this.getlY() - otherPoint.getrY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public void addlX(int num){
        this.lx = this.lx + num;
    }
    public void addlY(int num){
        this.ly = this.ly + num;
    }
    public void addrX(int num){
        this.rx = this.rx + num;
    }
    public void addrY(int num){
        this.ry = this.ry + num;
    }

    public void setXnY(int num, int num2, int num3, int num4){
        this.lx = num;
        this.ly = num2;
        this.rx = num3;
        this.ry = num4;
    }


}

