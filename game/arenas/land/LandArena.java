/**
 *@author  Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.arenas.land;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.land.LandRacer;

/**
 *LandArena class that extends the abstract Arena class .
 *  * most of the setter/getters are from IntelliJ auto complete system .
 */
public class LandArena extends Arena {
    /**
     * enums declerations .
     */
    public enum Coverage {
        SAND, GRASS, MUD
    }

    public enum LandSurface {
        FLAT, MOUNTAIN
    }




    public static final double DEFAULT_FRICTION = 0.5;
    public static final int DEFAULT_MAX_RACERS = 8;
    public static final int DEFAULT_LENGTH = 800;
    private Coverage coverage;
    private LandSurface surface;


    private final String D_Name = "LandArena";
    /**
     *an Def Constractor that initialized the super class Arena with its def fields  .
     */
    public LandArena() {
        super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);
    }

    public LandArena(double length, int maxRacers) {
        super(length,maxRacers,DEFAULT_FRICTION);
        this.coverage = Coverage.GRASS;
        this.surface = LandSurface.FLAT;

    }
    /**
     *an override of the abstract method in the Arena super-class .
     * returns the name of the class . LandArena .
     */
    @Override
    public String getName() {
        return D_Name;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setSurface(LandSurface surface) {
        this.surface = surface;
    }

    public LandSurface getSurface() {
        return surface;
    }
    public String toString() {
        return super.toString();
    }
    /**
     * @param racer
     * an override of the abstract method in the Arena super-class .
     * bollean method theat return true if the racer that want to enter the Arena is indeed an LandRacer .
     */
    @Override
    public boolean IsLegalRacer(Racer racer) {
        if(racer instanceof LandRacer){
            return true;
        }
        return false;
    }

}
