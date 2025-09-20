/**
 * @author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package game.racers.naval;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer;
/**
 * an SpeedBoat class that describe a SpeedBoat , sub class of Racer and have
 * an interface of NavalRacer .
 */
public class SpeedBoat extends Racer implements NavalRacer {
    static final String CLASS_NAME = "SpeedBoat";
    static final double DEFAULT_MAX_SPEED =170;
    static final double DEFAULT_ACCELERATION=5;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    private EnumContainer.BoatType type = EnumContainer.BoatType.SKULLING;
    private EnumContainer.Team team = EnumContainer.Team.DOUBLE;
    private String name = "SpeedBoat#"+super.getSerialNumber();
    public SpeedBoat(String name,double maxSpeed,double acceleration,EnumContainer.Color color){
        super(name, maxSpeed, acceleration, color);
    }
    /**
     * SpeedBoat def Con , activates the super class def Con
     */
    public  SpeedBoat(){ super();
        super.setAcceleration(DEFAULT_ACCELERATION);
        super.setMaxSpeed(DEFAULT_MAX_SPEED);
        super.setColor(DEFAULT_color);
        super.setName(CLASS_NAME);
       }

    /**
     * describes the Racer data
     * @return
     */    public String describeSpecific() {
        return super.describeSpecific()+", Type: "+this.getType()+", Team: "+this.getTeam();
    }
    public EnumContainer.BoatType getType() {
        return type;
    }

    public EnumContainer.Team getTeam() {
        return team;
    }

    public void setTeam(EnumContainer.Team team) {
        this.team = team;
    }

    public void setType(EnumContainer.BoatType type) {
        this.type = type;
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
