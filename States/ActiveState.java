package States;

import game.racers.Racer;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Active State -> State Dp
 */
public class ActiveState implements RacerState{
    /**
     * Override for the RacerState inteface
     * @param name
     */
    @Override
    public void printState(String name) {
        System.out.println(name+" is active and racing.");
    }



}
