
/**
 *@author  Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package game.racers;

import States.ActiveState;
import States.BrokenState;
import States.RacerState;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;
import utilities.EnumContainer;
import game.arenas.Arena;

/**
 * and Racer abstract class -> we dont use racer instances .
 * decribe a racer .
 * all the setters and getter is  from IntelliJ outo system.
 */
public abstract class Racer implements Cloneable {
    private static int lastSerialNumber = 0;
    private  int serialNumber ;
    private String name ;
    private Point currentLocation,finish;
    private double maxSpeed,acceleration,currentSpeed,failureProbability;
    private EnumContainer.Color color;
    private Mishap mishap   ;
    private RacerState state;
    private int Position;

    public RacerState getState(){
        return this.state;
    }
    public void setState(RacerState state) {
        this.state = state;
        this.state.printState(name);

    }
    public int getPosition(){
        return Position;
    }
    public void  setPosition(int p){
        this.Position =p;
    }

    private Arena arena;
    public Racer(String name , double maxSpeed,double acceleration,EnumContainer.Color color ){
        this.name = name ;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.color = color;
        this.currentLocation = new Point(0,0);
        this.serialNumber = ++lastSerialNumber;

    }
    public Racer(){

        lastSerialNumber+=1;
        this.serialNumber = lastSerialNumber;

    }

    public Object clone(){
        Object clone = null;
        try{
            clone = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * A method that get activated when entering an Arena .
     *
     * @param arena
     * @param start
     * @param finish
     */
    public  void initRace(Arena arena,Point start,Point finish){
        this.arena = arena;
        this.currentLocation = start;
        this.finish=finish;
    }

    /**
     * this method is moving the Racers.
     * we there is mishap we checking if the mishap is fixable&&the turns to fix it == 0;
     * if yes -> the mishap is null , thus there is none . then we activate the SpeedCalc Method,that calculating the updating speed we only acc,and fric
     * if no-> we calc the speed with the reduction fuctor ,acc anf fric .
     * if the is no mishap -> we activate the Fate mthod that return y/n upon rand ..
     * if we got yes, we generating a new mishap and going threw the first line of this explanation .
     * if not , we using again the CalcSpeed method
     *
     *
     * @param friction
     * @return
     */
    public Point move(double friction) {
        // Check for mishap
        if (mishap != null) {
            if (mishap.getTurnsToFix() == 0 && mishap.isFixable()) {
                // Mishap has been fixed, reset mishap to null
                mishap = null;
                return SpeedCalc(friction);
            }

            // Apply reduction factor to current speed
            currentSpeed += acceleration * mishap.getReductionFactor()*friction;
            currentSpeed = Math.min(currentSpeed,maxSpeed);
            currentLocation.setX(currentSpeed + currentLocation.getX());
            // Reduce turns to fix by 1
            mishap.nextTurn();


            return currentLocation;
            // Print message about the mishap

        }
        else if(mishap == null){
           if(Fate.breakDown()){
               mishap = Fate.generateMishap();

               if (mishap.getTurnsToFix() == 0 && mishap.isFixable()) {
                   // Mishap has been fixed, reset mishap to null
                   mishap = null;
                   return SpeedCalc(friction);
               }

               System.out.println(getRacerName() + " has a mishap!" + mishap.toString() + ".");
               // Apply reduction factor to current speed
               currentSpeed += acceleration * mishap.getReductionFactor()*friction;
               currentSpeed = Math.min(currentSpeed,maxSpeed);
                currentLocation.setX(currentSpeed + currentLocation.getX());

               // Reduce turns to fix by 1
               mishap.nextTurn();
                return currentLocation;
           }
           else{

               return SpeedCalc(friction);

           }

        }

       return  currentLocation;
    }

    /**
     * desctibe the data of the racer
     * @return
     */
    public String describeSpecific() {
        return "Serial Number: " + this.serialNumber + ", Max Speed: " + this.maxSpeed +
                ", Acceleration: " + this.acceleration + ", Color: " + this.color;
    }

    /**
     * abstract method the implemented in the subclasses
     * @return
     */
    public abstract String className();

    /**
     * introduce the racer
     */

    public void introduce() {
        System.out.println("["+this.className()+"] " + "name: " + this.name +", " + describeSpecific());
    }

    public boolean hasMishap() {
        return this.mishap != null;
    }
    /**
     * abstract method the implemented in the subclasses
     * @return
     */
    public abstract String getName();
public String getRacerName(){
    return name;
}
    public EnumContainer.Color getColor() {
        return color;
    }

    /**
     * if the current speed is lower the the maxSpeed , we updating the current speed+ current location  ,
     * if its get higher by any chance , we getting the min of the max,and the currentSpeed, thus we do not
     * getting higher than the max speed .
     * @param friction
     * @return
     */
    public Point SpeedCalc(double friction){
        if(currentSpeed < maxSpeed) {
            currentSpeed += acceleration * friction;
            currentLocation.setX(currentLocation.getX()+currentSpeed);
            return currentLocation;
        }
        else{
            currentSpeed = Math.min(currentSpeed,maxSpeed);
            currentLocation.setX(currentLocation.getX()+currentSpeed);
            return currentLocation;
        }
    }


    public Arena getArena() {
        return arena;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getFailureProbability() {
        return failureProbability;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public Mishap getMishap() {
        return mishap;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public Point getFinish() {
        return finish;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setColor(EnumContainer.Color color) {
        this.color = color;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMishap(Mishap mishap) {
        this.mishap = mishap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

}
