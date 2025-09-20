/**
 *@author  Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package utilities;

public  class  Point  {
    static final int MAX_X = 1000000;
    static final int MIN_X =0;
    static final int MAX_Y = 800;
    static final int MIN_Y =0;
    private double x,y ;
    public  Point(double x,double y){
        this.x= x;
        this.y = y;
    }
    public Point(){
        this.x=MIN_X;
        this.y =MIN_Y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(Point p){
       this.x = p.getX();
       this.y = p.getY();


    }

    /**
     * returing the distance between this, and other  point
     * @param other
     * @return
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
