/**
 * @author Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package game.arenas.exceptions;


/**
 *an class for the exception RacerLimit, that gives an exception if the amount of
 * ActiveRacers in Arena is full .
 */
public class RacerLimitException extends Exception {
    public RacerLimitException(String msg){
        super(msg);
    }
}
