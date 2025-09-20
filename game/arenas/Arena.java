/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.arenas;

import GuiHM2.MyObserver;
import Hm3.ActiveState;
import Hm3.BrokenState;
import Hm3.FinishedState;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * an super-class Arena , abstract one . theres is not instances of it .
 */
public abstract class Arena implements MyObservable {

    private Vector<MyObserver> observers = new Vector<>();
    protected  ArrayList<Racer> activeRacers = new ArrayList<>();
    protected  ArrayList<Racer>  completedRacers= new ArrayList<>();
    protected final double  FRICTION;
    protected final int MAX_RACER;
    final static int MIN_Y_GAP =10 ;
    protected double length;
    List<Racer> PositionRacers = new ArrayList<>();
    /**
     * Constractor .
     * @param length
     * @param maxRacers
     * @param friction
     */
    public Arena(double length , int maxRacers,double friction){
        this.length = length;
        this.MAX_RACER = maxRacers;
        this.FRICTION = friction;
    }
    public   Arena (){
    FRICTION=0;
    MAX_RACER=10;
    }

    public ArrayList<Racer> updateRacersPositions(Racer r, ArrayList<Racer> p){
        p.sort(Comparator.comparingDouble(racer -> racer.getCurrentLocation().getX()));
        int k=1;
        for(int i =0 ; i<getActiveRacers().size();i++){
            getActiveRacers().get(i).setPosition(k);
            k++;
        }
       return p;
    }

    /**
     * for each of the activeRacer that exist in the Array , we activate initRace
     * method of the racer , initializing it with this(this instance of Arena )
     * starting point and finish point .
     *
     */

    public void initRace(){
        double y =0;


        for (int i = 0; i < this.activeRacers.size(); i++) {
            activeRacers.get(i).initRace(this,new Point(0,y),new Point(this.length,0));
            y += MIN_Y_GAP ;
            System.out.println(1);

        }
    }
    /**
     * an abstract methods that is written in the sub classes .
     * @param racer
     * @return
     */
    public abstract boolean IsLegalRacer(Racer racer);
    /**
     * this method checks if the racer is indeed can compete in the given arena , if not , throws
     * exception ,and if ActiveRacers Array is not full (if full throws exception )
     * if everythin is fine , we add the racer into the Array
     * @param newRacer
     * @throws RacerLimitException
     * @throws RacerTypeException
     */
    public  void addRacer(Racer newRacer) throws RacerLimitException ,RacerTypeException{
        if (!IsLegalRacer(newRacer)) {
            throw new RacerTypeException("Invalid Racer of type "+newRacer.className()+" for "+ this.getName());
        }
        if ((this.MAX_RACER == this.activeRacers.size()) && this.activeRacers.size()!=0) {
            throw new RacerLimitException("Arena is full! ("+ this.activeRacers.size() +" active Racers exists!). racer #"+
                    newRacer.getSerialNumber()+" not added");

          }
        this.activeRacers.add(newRacer);
    }

    /**
     * abstract methos that is implemented in the sub-classes
     *
     * @return
     */
    public abstract String getName();
    public  boolean hasActiveRacers(){

        if(this.activeRacers.isEmpty()) {
            return false;
        }
        else{
            return true;
        }

    }

    /**
     * to every Racer in the Array , we activate the move method, sending this Arena Friction that we
     * recived from the sub-classes .
     * after that we check if Racer i finished the Racer ,
     * if its is, we sending him to the crossFinishLine method
     *
     */
    public void playTurn(){

        for (int i =0; i < this.activeRacers.size(); i++) {
            activeRacers.get(i).move(this.FRICTION);

            if(activeRacers.get(i).getCurrentLocation().getX() > this.length){

                crossFinishLine(activeRacers.get(i));
            }
        }

    }

    /**
     * a method that recived a racer , after he finished the race
     * we checking if the racer is inside the array .
     * if it is , we removing it from the ActiveRacers Array , and adding it to CompletedRacer Array .
     * @param racer
     */
  public   void crossFinishLine(Racer racer){
    if(activeRacers.contains(racer)  ){
                activeRacers.remove(racer);
                completedRacers.add(racer);
            }
  }
    public double getFRICTION() {
        return FRICTION;
    }

    public double getLength() {
        return length;
    }
    /**
     * for each Racer i in completedRacer Array , we printing its data
     */
    public void showResults() {
        if (completedRacers.size() == 0) {
            System.out.println("No racers have completed the race yet.");
        } else {
            System.out.println("Race results:");
            for (int i = 0; i < completedRacers.size(); i++) {
                Racer racer = completedRacers.get(i);
                System.out.println("#"+(i+1)+"  ->  name: "+racer.getRacerName()+", "+ racer.describeSpecific());

            }
        }
    }
    public double getFinishingTime(Racer racer) {
        double distance = racer.getCurrentLocation().distance(racer.getFinish());
        double time = distance / racer.getCurrentSpeed();
        return time;
    }
    /**
     *
     * @return
     */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }

    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }

    public void setActiveRacers(ArrayList<Racer> activeRacers) {
        this.activeRacers = activeRacers;
    }

    public void setCompletedRacers(ArrayList<Racer> completedRacers) {
        this.completedRacers = completedRacers;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getMAX_RACER() {
        return MAX_RACER;
    }

    @Override
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object data) {
        for (MyObserver observer : observers) {
            observer.update(data);
        }
    }

}
 interface MyObservable {
    void addObserver(MyObserver observer);
    void removeObserver(MyObserver observer);
    void notifyObservers(Object data);
}

