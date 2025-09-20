package Hm3;

/**
 * Failed state -> State Dp
 */
public class FailedState implements  RacerState{
    /**
     * Override for the RacerState inteface
     * @param name
     */
    @Override
    public void printState(String name) {
        System.out.println(name+" Failed.");
    }
}
