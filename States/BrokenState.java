package States;

/**
 * Broken state ->State Dp
 */
public class BrokenState implements RacerState{
    /**
     * Override for the RacerState inteface
     * @param name
     */
    @Override
    public void printState(String name) {
        System.out.println(name+" has encountered a mishap and is broken.");
    }
}
