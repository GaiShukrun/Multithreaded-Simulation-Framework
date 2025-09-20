/**
 *@author  Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.land;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;
/**
 * an Car class that describe a Car , sub class of Racer and have
 * an interface of LandRacer .
 */
public class Car extends Racer implements LandRacer {
    private static final String CLASS_NAME ="Car";
    private static final double DEFAULT_MAX_SPEED =400;
    private static final double DEFAULT_ACCELERATION =20;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    static  final int DEFAULT_WHEELS =4;
    private Wheeled wheeled = new Wheeled();
    private String name = "Car#"+ super.getSerialNumber();
    static  final  EnumContainer.Engine engine = EnumContainer.Engine.FOURSTROKE;
    /**
     * Car def Con , activates the super class def Con
     */
   public Car(){
       super();
       super.setAcceleration(DEFAULT_ACCELERATION);
       super.setMaxSpeed(DEFAULT_MAX_SPEED);
       super.setColor(DEFAULT_color);
       super.setName(name);
       this.wheeled.setNumOfWheels(DEFAULT_WHEELS);

   }
    public Car(String name,double maxSpeed,double acceleration,EnumContainer.Color color ,int numOfWheels){
        super(name, maxSpeed, acceleration, color);
        this.wheeled.setNumOfWheels(numOfWheels);

    }

    @Override
    public void setName(String name) {

        super.setName("Car#"+name);
    }

    /**
     * describes the Racer data
     * @return
     */
    @Override
    public String describeSpecific() {
        return super.describeSpecific()+", Number of Wheels:"+DEFAULT_WHEELS+", Engine Type:"+this.getEngine();
    }
    /**
     * return name of the serialnumber and the name class
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    public static EnumContainer.Engine getEngine() {
        return engine;
    }
    /**
     *
     *
     * @return Class_Name
     */
    public  String className() {
        return CLASS_NAME;
    }

    /**
     * return the arena that the racer is in
     * @return
     */
    @Override
    public Arena getArena() {
        return super.getArena();
    }

    public void setWheeled(Wheeled wheeled) {
        this.wheeled = wheeled;
    }

}
