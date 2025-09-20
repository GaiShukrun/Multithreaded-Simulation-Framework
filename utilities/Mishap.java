package utilities;

/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */import java.text.DecimalFormat;

public class Mishap {
    private boolean fixable;
    private  double reductionFactor;
    private  int turnsToFix;
    public Mishap(boolean fixable,int turnsToFix,double reductionFactor){
        this.fixable = fixable;
        this.reductionFactor = reductionFactor;
        this.turnsToFix = turnsToFix;

    }
    public boolean isFixable() {
        return fixable;
    }

    /***
     * this method reducing the numof turnstoFix .
     */
    public void nextTurn() {
        if (fixable && turnsToFix > 0) {
            turnsToFix = turnsToFix -1;
        }
    }

    public double getReductionFactor() {
        return reductionFactor;
    }

    public int getTurnsToFix() {
        return turnsToFix;
    }

    public void setFixable(boolean fixable) {
        this.fixable = fixable;
    }

    /**
     * converting the mishap to string
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("(%b, %d, %s)", fixable, turnsToFix, df.format(reductionFactor));
    }

    public void setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public void setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
    }
}
