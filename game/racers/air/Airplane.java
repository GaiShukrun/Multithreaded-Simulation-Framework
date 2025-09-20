/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.air;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;

/**
 * an Airplane class that describe an Airplane , sub class of Racer and have
 * an interface of AerialRacer .
 */
public class Airplane extends Racer implements AerialRacer {
    private static final String CLASS_NAME ="Airplane";
    private static final double DEFAULT_MAX_SPEED =885;
    private static final double DEFAULT_ACCELERATION =100;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLACK;
    static  final int DEFAULT_WHEELS =3;
    private Wheeled wheeled = new Wheeled();
    private String name = "Airplane#"+super.getSerialNumber();

    /**
     * an Def Constractor that first use super clas def Con . .
     *
     */
   public  Airplane(){ super();
       super.setAcceleration(DEFAULT_ACCELERATION);
       super.setMaxSpeed(DEFAULT_MAX_SPEED);
       super.setColor(DEFAULT_color);
       super.setName(CLASS_NAME);
       this.wheeled.setNumOfWheels(DEFAULT_WHEELS);}
    public Airplane(String name,double maxSpeed , double acceleration , EnumContainer.Color color ,int numOfWheels){
        super(name, maxSpeed, acceleration, color);
        this.wheeled.setNumOfWheels(numOfWheels);
    }

    /**
     * describes the Racer data
     * @return
     */
    @Override
    public String describeSpecific() {
        return super.describeSpecific()+", Number of Wheels:"+this.getDefaultWheels();
    }

    /**
     * an override for the abstarct method in the super class
     *
     * @return Class_Name
     */
    @Override
    public String className() {
        return  CLASS_NAME;
    }

    /**
     * returns the defWheels , override of the abstract method in the super class
     * @return
     */
    public static int getDefaultWheels() {
        return DEFAULT_WHEELS;
    }

    /**
     * return name of the serialnumber and the name class
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * return the arena that the racer is in
     * @return
     */
    @Override
    public Arena getArena() {
        return super.getArena();
    }
}
