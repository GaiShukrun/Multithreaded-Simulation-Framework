/**
 * @autor Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */package game.arenas.exceptions;


/**
 *an exception for the type of the racer , if its a wrong racer that wants to enter an Arena
 * that doesn't fit (ex. land racer to Areial racer )
 */
public class RacerTypeException extends Exception {
    /**
     * @param  msg;
     */
    public RacerTypeException(String msg){
        super(msg);
    }
}
