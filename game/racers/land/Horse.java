/**
 *@author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.land;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer;
/**
 * a Horse class that describe a Horse , sub class of Racer and have
 * an interface of LandRacer .
 */
public class Horse extends Racer implements LandRacer {
    private static final String CLASS_NAME ="Horse";
    private static final double DEFAULT_MAX_SPEED =50;
    private static final double DEFAULT_ACCELERATION =3;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.BLACK;

    private EnumContainer.Breed breed = EnumContainer.Breed.THOROUGHBRED;
    private String name = "Horse#"+super.getSerialNumber();
   public Horse(String name, double maxSpeed, double acceleration, EnumContainer.Color color){
          super(name, maxSpeed, acceleration, color);
    }
    /**
     * Horse def Con , activates the super class def Con
     */
    public Horse(){
        super();
        super.setAcceleration(DEFAULT_ACCELERATION);
        super.setMaxSpeed(DEFAULT_MAX_SPEED);
        super.setColor(DEFAULT_color);
        super.setName(CLASS_NAME);

    }
    /**
     * describes the Racer data
     * @return
     */
    @Override
    public String describeSpecific() {
        return super.describeSpecific()+", Breed: " +this.getBreed();
    }

    public EnumContainer.Breed getBreed() {
        return breed;
    }

    public void setBreed(EnumContainer.Breed breed) {
        this.breed = breed;
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
