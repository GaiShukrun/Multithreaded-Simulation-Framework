/**
 * @author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.land;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;
/**
 * an Bicycle class that describe an Bicycle , sub class of Racer and have
 * an interface of LandRacer .
 */
public class Bicycle extends Racer implements LandRacer {
    static final String CLASS_NAME = "Bicycle";
    static final double DEFAULT_MAX_SPEED =270;
    static final double DEFAULT_ACCELERATION=10;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.GREEN;
    private EnumContainer.BicycleType type = EnumContainer.BicycleType.MOUNTAIN;
    private String name = "Bicycle#"+super.getSerialNumber();
    static  final int DEFAULT_WHEELS =2;
    private Wheeled wheeled = new Wheeled();
   public  Bicycle(String name,double maxSpeed,double acceleration ,EnumContainer.Color color,int numOfWheels){
        super(name, maxSpeed, acceleration, color);
        this.wheeled.setNumOfWheels(numOfWheels);
    }

    /**
     * Bicycle def Con , activates the super class def Con
     */
    public  Bicycle(){ super();
        super.setAcceleration(DEFAULT_ACCELERATION);
        super.setMaxSpeed(DEFAULT_MAX_SPEED);
        super.setColor(DEFAULT_color);
        super.setName(CLASS_NAME);
        this.wheeled.setNumOfWheels(DEFAULT_WHEELS);
   }
    /**
     * describes the Racer data
     * @return
     */

    @Override
    public String describeSpecific() {
        return super.describeSpecific()+", Number of Wheels:"+this.getDefaultWheels()+" Bicycle Type:"+this.getType();
    }
    /**
     *
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

    public EnumContainer.BicycleType getType() {
        return type;
    }
    /**
     * return the arena that the racer is in
     * @return
     */
    @Override
    public Arena getArena() {
        return super.getArena();
    }

    public void setType(EnumContainer.BicycleType type) {
        this.type = type;
    }
}

