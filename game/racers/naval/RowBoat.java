/**
 *@author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.racers.naval;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer;
/**
 * an Rowboat class that describe a Rowboat , sub class of Racer and have
 * an interface of NavalRacer .
 */
public class RowBoat extends Racer implements NavalRacer {
    protected  final String CLASS_NAME = "RowBoat";
    static final double DEFAULT_MAX_SPEED =75;
    static final double DEFAULT_ACCELERATION=10;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    private EnumContainer.BoatType type = EnumContainer.BoatType.SKULLING;
    private EnumContainer.Team team = EnumContainer.Team.DOUBLE;
    private String name = "RowBoat#"+super.getSerialNumber();
   public RowBoat(String name,double maxSpeed,double acceleration,EnumContainer.Color color){
        super(name, maxSpeed, acceleration, color);
    }
    /**
     * Rowboat def Con , activates the super class def Con
     */
   public RowBoat(){ super();
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
        return super.describeSpecific()+", Type: "+this.getType()+", Team: "+this.getTeam();
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
