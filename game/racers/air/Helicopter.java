/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.air;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer;
/**
 * an Helicopter class that describe an Helicopter , sub class of Racer and have
 * an interface of AerialRacer .
 */
public class Helicopter extends Racer implements AerialRacer {
    private static final String CLASS_NAME ="Helicopter";
    private static final double DEFAULT_MAX_SPEED =400;
    private static final double DEFAULT_ACCELERATION =50;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLUE;

    private String name = "Helicopter#"+super.getSerialNumber();
    /**
     * an Def Constractor that first use super clas def Con . .
     *
     */
    public Helicopter(){ super();
        super.setAcceleration(DEFAULT_ACCELERATION);
        super.setMaxSpeed(DEFAULT_MAX_SPEED);
        super.setColor(DEFAULT_color);
        super.setName(CLASS_NAME);
       }
    public Helicopter(String name,double maxSpeed,double acceleration,EnumContainer.Color color){
        super(name, maxSpeed, acceleration, color);
    }
    /**
     * an override for the abstarct method in the super class
     *
     * @return Class_Name
     */
    @Override
    public String className() {
        return CLASS_NAME;
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
     * describes the Racer data
     * @return
     */
    @Override
    public String describeSpecific() {
        return super.describeSpecific();
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

