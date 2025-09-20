/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package game.arenas.naval;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer;
/**
 *NavalArena class that extends the abstract Arena class .
 *  * most of the setter/getters are from IntelliJ auto complete system .
 */
public class NavalArena extends Arena {
    private final String D_Name = "NavalArena";
    /**
     * enums declerations .
     */
    public enum Water {
        SALTED,
        SWEET
    }

    public enum WaterSurface {
        FLAT,
        WAVY
    }

    public enum Body {
        SEA,
        LAKE,
        RIVER,
        OCEAN
    }

    private final static double DEFAULT_FRICTION = 0.7;
    private final static int DEFAULT_MAX_RACERS = 5;
    private final static int DEFAULT_LENGTH = 1000;

    private Water water;
    private WaterSurface surface;
    private Body body;
    /**
     *an Def Constractor that initialized the super class Arena with its def fields  .
     */
    public NavalArena() {
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);
    }

    public NavalArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
        this.water = Water.SWEET;
        this.surface = WaterSurface.FLAT;
        this.body = Body.LAKE;
    }
    /**
     *an override of the abstract method in the Arena super-class .
     * returns the name of the class . LandArena .
     */
    @Override
    public String getName() {
        return D_Name;
    }
    /**
     * @param racer
     * an override of the abstract method in the Arena super-class .
     * bollean method theat return true if the racer that want to enter the Arena is indeed an NavalRacer .
     */
    @Override
    public boolean IsLegalRacer(Racer racer) {
        if(racer instanceof NavalRacer){
            return true;
        }
        return false;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Water getWater() {
        return this.water;
    }

    public void setSurface(WaterSurface surface) {
        this.surface = surface;
    }

    public WaterSurface getSurface() {
        return this.surface;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }
}
