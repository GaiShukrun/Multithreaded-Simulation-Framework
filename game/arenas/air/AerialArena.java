/**
 *@author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package game.arenas.air;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer;
/**
 * Aerial Arena class the extends the abstract Arena class .
 * most of the setter/getters are from IntelliJ auto complete system .
 */
public class AerialArena extends Arena {
    private final static double DEFAULT_FRICTION=0.4 ;
    private  final static int DEFAULT_MAX_RACERS =6;
    private final static int DEFAULT_LENGTH=1500;
    private EnumContainer.Vision vision = EnumContainer.Vision.SUNNY;
    private EnumContainer.Weather weather = EnumContainer.Weather.DRY;
    private  EnumContainer.Height height = EnumContainer.Height.HIGH;
    private  EnumContainer.Wind wind= EnumContainer.Wind.HIGH;
   public AerialArena(double length,int maxRacers){
       super(length,maxRacers,DEFAULT_FRICTION);
   }
   public final String D_Name = "AerialArena";

    /**
     * Def constractor that initialize the super class .
     */
   public AerialArena(){

       super(DEFAULT_LENGTH,DEFAULT_MAX_RACERS,DEFAULT_FRICTION);

   }
    /**
     *an override of the abstract method in the Arena super-class .
     * returns the name of the class . AerielArena .
     */
    @Override
    public String getName() {
        return D_Name;
    }

    public String toString() {
        return super.toString();
    }
    @Override
    /**
     * @param racer
     *an override of the abstract method in the Arena super-class .
     * bollean method theat return true if the racer that want to enter the Arena is indeed an AerialRacer .
     */
    public boolean IsLegalRacer(Racer racer) {
        if(racer instanceof AerialRacer){
            return true;
        }
        return false;
    }


    public void setWind(EnumContainer.Wind wind) {
        this.wind = wind;
    }

    public void setHeight(EnumContainer.Height height) {
        this.height = height;
    }

    public void setVision(EnumContainer.Vision vision) {
        this.vision = vision;
    }

    public void setWeather(EnumContainer.Weather weather) {
        this.weather = weather;
    }

    public EnumContainer.Height getHeight() {
        return height;
    }

    public EnumContainer.Vision getVision() {
        return vision;
    }

    public EnumContainer.Weather getWeather() {
        return weather;
    }

    public EnumContainer.Wind getWind() {
        return wind;
    }



}
