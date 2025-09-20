package States;

/**
 * Finished state  ->state Dp
 */
public class FinishedState implements RacerState{
    /**
     * Override for the RacerState inteface
     * @param name
     */
    @Override
    public void printState(String name) {
        System.out.println(name+" has finished the race.");
    }
}
