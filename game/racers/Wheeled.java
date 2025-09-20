/**
 *@author  Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers;

/**
 * becouse Wheeled has apperences as field in other classes , its is a regular class .
 */

public  class Wheeled  {
    private int numOfWheels;
   public Wheeled(int numOfWheels){
        this.numOfWheels = numOfWheels;

    }
    public Wheeled(){this.numOfWheels =0;}
    public String describeSpecific(){
        return "NumberOfWheels: " +this.numOfWheels ;

    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    /**
     * sets numof Wheels
     * @param numOfWheels
     */
    public void setNumOfWheels(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }
}
